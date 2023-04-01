package com.ogzkesk.domain.repository

interface AdRepository {

    suspend fun showAd(activity : Any) : Int?

}