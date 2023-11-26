package io.github.eendroroy.exposed.demo.controller.api.v1

import io.github.eendroroy.exposed.demo.controller.api.BaseApiController
import io.github.eendroroy.exposed.demo.dto.request.TaskRequest
import io.github.eendroroy.exposed.demo.dto.response.BaseResponse
import io.github.eendroroy.exposed.demo.dto.response.TaskResponse
import io.github.eendroroy.exposed.demo.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/task"])
class TaskController(private val taskService: TaskService) : BaseApiController() {
    @PostMapping("/create")
    fun createTask(@RequestBody request: TaskRequest): ResponseEntity<BaseResponse<TaskResponse>> {

        val task = taskService.create(currentUser(), request)

        return ResponseEntity.ok(
            BaseResponse(
                data = TaskResponse(
                    title = task.title,
                    description = task.description,
                    dueAt = task.dueAt,
                    completedDate = task.completedAt,
                ),
                success = true,
                errorMessages = emptyList()
            )
        )
    }
}
