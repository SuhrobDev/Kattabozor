package dev.suhrob.kattabozor.data.repository

import dev.suhrob.kattabozor.data.common.ResponseHandler
import dev.suhrob.kattabozor.data.mapper.toModel
import dev.suhrob.kattabozor.data.remote.MainService
import dev.suhrob.kattabozor.domain.common.Resource
import dev.suhrob.kattabozor.domain.model.MainModel
import dev.suhrob.kattabozor.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainApiService: MainService
) : MainRepository, ResponseHandler() {

    override suspend fun getOffers(): Flow<Resource<MainModel>> = flow {
        try {

            val response = mainApiService.getOffers()
            if (response.code() == 200) {
                response.body()?.let {
                    emit(Resource.Success(it.toModel()))
                }
            } else if (response.code() == 404) {
                emit(Resource.Error("Error"))
            } else {
                emit(Resource.Error("Error"))
            }

        } catch (e: IOException) {
            emit(Resource.Error("Error: $e"))
        } catch (e: Exception) {
            emit(Resource.Error("Error: $e"))
        }
    }

}