package com.ogzkesk.settings

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ogzkesk.core.R
import com.ogzkesk.core.ext.toAction
import com.ogzkesk.core.ext.toEmail
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.setup.navigator
import com.ogzkesk.core.ui.theme.MBlue
import com.ogzkesk.core.ui.theme.mBodySmallLight
import com.ogzkesk.core.util.Constants.APP_LINK
import com.ogzkesk.core.util.Constants.APP_VERSION
import com.ogzkesk.core.util.Constants.PRIVACY_LINK
import com.ogzkesk.core.util.Constants.TERMS_OF_USE_LINK
import com.ogzkesk.settings.content.*
import com.ogzkesk.settings.util.SheetContent
import com.ogzkesk.settings.util.generalRowList
import com.ogzkesk.settings.util.legalRowList
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addSettings(){
    composable(Screen.Settings.route){
        Settings()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Settings(viewModel: SettingsViewModel = hiltViewModel()) {

    val navigator = navigator
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var sheetState by remember { mutableStateOf(SheetContent.THEME) }
    val themePref by viewModel.themePref.collectAsStateWithLifecycle()
    val languagePref by viewModel.languagePref.collectAsStateWithLifecycle()
    val sheetOpenState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = false
    )


    ModalBottomSheetLayout(
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetContentColor = MaterialTheme.colorScheme.onSurface,
        sheetState = sheetOpenState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetElevation = 24.dp,
        sheetContent = {
            when (sheetState) {
                SheetContent.THEME -> {
                    ThemeSheetContent(
                        themePref = themePref,
                        onItemClick = {
                            scope.launch {
                                sheetOpenState.hide()
                                viewModel.onThemePrefChange(it)
                            }
                        }
                    )
                }
                SheetContent.LANGUAGE -> {
                    LanguageSheetContent(
                        languagePref = languagePref,
                        onItemClick = {
                            scope.launch {
                                sheetOpenState.hide()
                                viewModel.onLanguagePrefChange(it)
                            }
                        }
                    )
                }
            }
        }
    ) {
        SettingsScreen(
            onBackClick = navigator::popBackStack,
            onProItemClick = navigator::navigate,
            onHelpClick = context::toEmail,
            onTermsClick = context::toAction,
            onPrivacyClick = context::toAction,
            onItemClick = {
                when (it) {
                    R.string.rate_us -> context.toAction(APP_LINK.toUri())
                    R.string.share_app -> shareApp(context,APP_LINK)
                    R.string.language -> {
                        sheetState = SheetContent.LANGUAGE
                        scope.launch {
                            sheetOpenState.show()
                        }
                    }
                    R.string.change_theme -> {
                        sheetState = SheetContent.THEME
                        scope.launch {
                            sheetOpenState.show()
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun SettingsScreen(
    onItemClick: (titleRes: Int) -> Unit,
    onProItemClick: (String) -> Unit,
    onHelpClick: () -> Unit,
    onTermsClick: (Uri) -> Unit,
    onPrivacyClick: (Uri) -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            SettingsTopBar(onBackClick)
        }
    ) { padd ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = padd.calculateTopPadding(),
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        ){
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.subscription),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                UpgradeSection(onProClick = onProItemClick)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.theme),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                ThemeSection(onClick = onItemClick)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item(key = 3) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.general),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            itemsIndexed(generalRowList) { i, rowItem ->
                GeneralRowSection(
                    rowItem = rowItem,
                    index = i,
                    onClick = onItemClick
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.help),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                HelpSection(onHelpClick = onHelpClick)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.legal),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            itemsIndexed(legalRowList) { i, rowItem ->
                LegalSection(
                    index = i,
                    rowItem = rowItem,
                    onLegalClick = {
                        when (rowItem.titleRes) {
                            R.string.terms_of_use -> onTermsClick(TERMS_OF_USE_LINK.toUri())
                            R.string.privacy_policy -> onPrivacyClick(PRIVACY_LINK.toUri())
                        }
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = APP_VERSION,
                        style = mBodySmallLight,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
