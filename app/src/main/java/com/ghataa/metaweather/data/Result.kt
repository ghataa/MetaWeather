package com.ghataa.metaweather.data

/** Wrapper class for the result of the data sources.
 * This class supports only the following subtypes: [Success], [Error], and [Loading] */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()

    object Loading : Result<Nothing>()

    override fun toString(): String =
        when (this) {
            is Success<*> -> "Success[data: $data]"
            is Error -> "Error[exception: $exception]"
            Loading -> "Loading"
        }
}
