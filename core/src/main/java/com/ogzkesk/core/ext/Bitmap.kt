package com.ogzkesk.core.ext

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStream

suspend fun String.toBitmap(context: Context): Bitmap? {

    return try {
        val loading = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(this)
            .build()

        val result = (loading.execute(request) as SuccessResult)
        result.drawable.toBitmap()

    } catch (e: IOException) {
        null
    } catch (e: java.lang.Exception) {
        null
    }
}

suspend fun Bitmap.addWatermark(@DrawableRes stickerRes : Int, context: Context) : Bitmap {
    return withContext(Dispatchers.IO){
        val mutableBitmap = Bitmap.createBitmap(this@addWatermark).copy(
            Bitmap.Config.ARGB_8888,true
        )
        val watermarkBitmap = BitmapFactory.decodeResource(context.resources, stickerRes)
        val mutableWaterBitmap = Bitmap.createScaledBitmap(
            watermarkBitmap,100,50,false
        ).copy(Bitmap.Config.ARGB_8888,true)

        val canvas = Canvas(mutableBitmap)
        val left = when {
            mutableBitmap.width < mutableBitmap.height -> {
                mutableBitmap.width - mutableBitmap.width / 4
            }
            mutableBitmap.width == mutableBitmap.height -> {
                mutableBitmap.width - mutableBitmap.width / 4
            }
            mutableBitmap.width > mutableBitmap.height -> {
                mutableBitmap.width - mutableBitmap.width / 5
            }
            else -> {
                mutableBitmap.width - mutableBitmap.width / 4
            }
        }

        val top = when {
            mutableBitmap.width < mutableBitmap.height -> {
                mutableBitmap.height - mutableBitmap.height / 9
            }
            mutableBitmap.width == mutableBitmap.height -> {
                mutableBitmap.height - mutableBitmap.height / 6
            }
            mutableBitmap.width > mutableBitmap.height -> {
                mutableBitmap.height - mutableBitmap.height / 6
            }
            else -> {
                mutableBitmap.height - mutableBitmap.height / 9
            }
        }

        canvas.drawBitmap(mutableWaterBitmap,left.toFloat(),top.toFloat(),null)

        return@withContext mutableBitmap
    }
}

suspend fun downloadBitmap(context: Context, bitmap: Bitmap): Uri? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        insertImageBitmap(context, bitmap)
    else
        insertImageBitmapLegacy(context, bitmap)
}

@RequiresApi(Build.VERSION_CODES.Q)
suspend fun insertImageBitmap(context: Context, bmp: Bitmap): Uri? {
    return try {
        withContext(Dispatchers.IO) {

            val filename = "Eon-ai-${System.currentTimeMillis()}.jpg"
            var fos: OutputStream?
            var imageUri: Uri?
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }

            val contentResolver = context.contentResolver

            contentResolver.also { resolver ->
                imageUri =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }

            fos?.use { bmp.compress(Bitmap.CompressFormat.JPEG, 100, it) }

            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            imageUri?.let { contentResolver.update(it, contentValues, null, null) }

            return@withContext imageUri

        }

    } catch (e: Exception) {
        null
    }
}

suspend fun insertImageBitmapLegacy(context: Context, bitmap: Bitmap): Uri? {
    return try {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                101
            )
            return null
        } else {
            withContext(Dispatchers.IO) {

                val bytes = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                val path = MediaStore.Images.Media.insertImage(
                    context.contentResolver,
                    bitmap,
                    "Eon-ai-${System.currentTimeMillis()}.jpg",
                    null
                )
                return@withContext Uri.parse(path)
            }
        }

    } catch (e: Exception) {
        null
    }
}

suspend fun shareImage(bmp: Bitmap, context: Context) {

        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            insertImageBitmap(context, bmp)
        } else {
            insertImageBitmapLegacy(context, bmp)
        }

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.putExtra(Intent.EXTRA_TEXT, "Extra Text")
        context.startActivity(Intent.createChooser(intent, "Share with"))
}