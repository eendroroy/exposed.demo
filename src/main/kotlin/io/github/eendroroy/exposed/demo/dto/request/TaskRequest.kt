package io.github.eendroroy.exposed.demo.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import io.github.eendroroy.exposed.demo.config.Patterns.DATE_TIME_SHORT_12_VALUE
import java.time.LocalDateTime

data class TaskRequest(
    var title: String,
    var description: String,
    @JsonFormat(pattern = DATE_TIME_SHORT_12_VALUE) var dueAt: LocalDateTime,
)
