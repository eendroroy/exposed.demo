package io.github.eendroroy.exposed.demo.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.Serializable

interface Jsonable : Serializable

fun Jsonable.toJson(throws: Boolean = false) = process(this, throws)

private fun process(any: Any, throws: Boolean = false) = try {
    objectMapper.findAndRegisterModules().writeValueAsString(any)
} catch (e: JsonProcessingException) {
    if (throws) throw e else null
}

private val objectMapper = ObjectMapper().findAndRegisterModules()
