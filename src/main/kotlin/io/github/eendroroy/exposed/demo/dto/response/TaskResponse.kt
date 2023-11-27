package io.github.eendroroy.exposed.demo.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.github.eendroroy.exposed.demo.config.Patterns
import java.time.LocalDateTime

data class TaskResponse(
    var id: Long,
    var title: String,
    var description: String,
    @JsonFormat(pattern = Patterns.DATE_TIME_SHORT_12_VALUE) var dueAt: LocalDateTime,
    @JsonFormat(pattern = Patterns.DATE_TIME_SHORT_12_VALUE) var completedDate: LocalDateTime?,
)
