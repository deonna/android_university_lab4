package com.codepath.recyclerviewlab.networking

interface CallbackResponse<T> {
    fun onSuccess(model: T)
    fun onFailure(error: Throwable?)
}