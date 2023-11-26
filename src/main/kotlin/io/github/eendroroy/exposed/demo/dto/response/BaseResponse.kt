package io.github.eendroroy.exposed.demo.dto.response

import io.github.eendroroy.exposed.demo.util.Jsonable

data class BaseResponse<T>(
    var data: T?,
    var success: Boolean,
    var errorMessages: List<MessageResponse>,
): Jsonable
