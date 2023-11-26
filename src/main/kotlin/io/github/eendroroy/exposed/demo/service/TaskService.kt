package io.github.eendroroy.exposed.demo.service

import io.github.eendroroy.exposed.demo.dto.request.TaskRequest
import io.github.eendroroy.exposed.demo.persistence.task.Task
import io.github.eendroroy.exposed.demo.persistence.user.User

interface TaskService {
    fun create(user: User, request: TaskRequest): Task
}
