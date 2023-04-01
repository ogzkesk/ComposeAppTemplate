package com.ogzkesk.data.di

import com.ogzkesk.data.local.entities.AppEntity
import com.ogzkesk.data.mapper.*
import com.ogzkesk.domain.model.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindSampleMapper(sampleMapper: SampleMapper):
            Mapper<AppEntity,AppModel>

}