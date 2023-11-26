package io.github.eendroroy.exposed.demo.service.impl

import io.github.eendroroy.exposed.demo.dto.request.TaskRequest
import io.github.eendroroy.exposed.demo.persistence.task.Task
import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.service.TaskService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskServiceImpl : TaskService {
    @Transactional
    override fun create(user: User, request: TaskRequest): Task {
        return Task.new {
            this.title = request.title
            this.description = request.description
            this.dueAt = request.dueAt
            this.user = user
        }
    }
}
