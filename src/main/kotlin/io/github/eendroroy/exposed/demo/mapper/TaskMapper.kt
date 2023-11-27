package io.github.eendroroy.exposed.demo.mapper

import io.github.eendroroy.exposed.demo.dto.response.TaskResponse
import io.github.eendroroy.exposed.demo.persistence.task.Task

fun Task.toTaskResponse() = TaskResponse(
    this.id.value,
    this.title,
    this.description,
    this.dueAt,
    this.completedAt
)
