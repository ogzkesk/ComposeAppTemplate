package com.ogzkesk.data.mapper

import com.ogzkesk.data.local.entities.AppEntity
import com.ogzkesk.domain.model.AppModel
import javax.inject.Inject

class SampleMapper @Inject constructor() : Mapper<AppEntity,AppModel> {

    override suspend fun map(input: AppEntity): AppModel = AppModel(
        id = input.id
    )
}
