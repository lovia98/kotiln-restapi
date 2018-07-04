package com.kotline.api.model

enum class ResultCode(val code: String) {
    SUCCESS("2xx"),
    FORBUIDDEN("403"),
    NOTFOUND("404"),
    SERVERERROR("500")
}

data class ResponseFormat(
        val result: ResultCode,
        val data : Any? = null,
        val errorMessage: String = ""
)


