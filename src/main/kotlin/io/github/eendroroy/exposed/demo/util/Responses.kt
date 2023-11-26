package io.github.eendroroy.exposed.demo.util

import io.github.eendroroy.exposed.demo.dto.response.BaseResponse
import io.github.eendroroy.exposed.demo.dto.response.MessageResponse

fun forbiddenResponse() = BaseResponse(
    data = null,
    success = false,
    errorMessages = listOf(
        MessageResponse(
            message = "Access denied",
            details = "Access denied",
        )
    ),
)

fun unauthorizedResponse(details: String?) = BaseResponse(
    data = null,
    success = false,
    errorMessages = listOf(
        MessageResponse(
            message = "Unauthorised",
            details = details ?: "Unauthorised",
        )
    ),
)
