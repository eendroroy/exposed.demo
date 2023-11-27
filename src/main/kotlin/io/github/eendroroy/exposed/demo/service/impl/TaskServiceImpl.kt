package io.github.eendroroy.exposed.demo.service.impl

import io.github.eendroroy.exposed.demo.dto.request.TaskRequest
import io.github.eendroroy.exposed.demo.persistence.task.Task
import io.github.eendroroy.exposed.demo.persistence.task.Tasks
import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.service.TaskService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskServiceImpl : TaskService {
    @Transactional
    override fun getAll(user: User): List<Task> {
        return Task.find { Tasks.user eq user.id }.toList()
    }

    @Transactional
    override fun create(user: User, request: TaskRequest): Task {
        return Task.new {
            this.title = request.title
            this.description = request.description
            this.dueAt = request.dueAt
            this.user = user
        }
    }

    @Transactional
    override fun update(taskId: Long, request: TaskRequest): Task {
        val task = Task.findById(taskId) ?: throw RuntimeException()

        task.title = request.title
        task.description = request.description
        task.dueAt = request.dueAt

        return task
    }
}
