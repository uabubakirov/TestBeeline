package com.example.test.data.repositories

import com.example.test.data.remote.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow{
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        }catch (e: HttpException){
            emit(Resource.Error(data = null, message = e.localizedMessage ?: "ErrorOccurred"))
        }catch (io: IOException) {
            emit(Resource.Error(data = null, message = CHECKCONNECTION))
        }catch (e: Exception){
            emit(Resource.Error(data = null, message = e.localizedMessage ?: "ErrorOccurred"))
        }
    }
    protected fun <T> doLocalRequest(request: suspend () -> T) = flow{
        emit(Resource.Loading())
        emit(Resource.Empty())
        try {
            emit(Resource.Success(data = request()))
        }catch (e: Exception){
            emit(Resource.Error(data = null, message = e.localizedMessage ?: "ErrorOccurred"))
        }
    }
    companion object{
        const val CHECKCONNECTION = "Check internet connection"
    }
}
