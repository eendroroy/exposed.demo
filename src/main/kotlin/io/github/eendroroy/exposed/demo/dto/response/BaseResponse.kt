package io.github.eendroroy.exposed.demo.dto.response

data class BaseResponse<T>(
    var data: T?,
    var success: Boolean,
    var errorMessages: List<MessageResponse>,
)
