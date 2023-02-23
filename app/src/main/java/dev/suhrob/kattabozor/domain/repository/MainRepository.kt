package dev.suhrob.kattabozor.domain.repository

import dev.suhrob.kattabozor.domain.common.Resource
import dev.suhrob.kattabozor.domain.model.MainModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getOffers(): Flow<Resource<MainModel>>
}