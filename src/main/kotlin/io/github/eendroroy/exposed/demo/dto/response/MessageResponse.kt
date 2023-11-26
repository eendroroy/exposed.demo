package io.github.eendroroy.exposed.demo.dto.response

import io.github.eendroroy.exposed.demo.util.Jsonable

data class MessageResponse(
    var message: String,
    var details: String,
): Jsonable
