package io.github.eendroroy.exposed.demo.controller.api.v1

import io.github.eendroroy.exposed.demo.controller.api.BaseApiController
import io.github.eendroroy.exposed.demo.dto.request.TaskRequest
import io.github.eendroroy.exposed.demo.dto.response.BaseResponse
import io.github.eendroroy.exposed.demo.dto.response.MessageResponse
import io.github.eendroroy.exposed.demo.dto.response.TaskResponse
import io.github.eendroroy.exposed.demo.mapper.toTaskResponse
import io.github.eendroroy.exposed.demo.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/task"])
class TaskController(private val taskService: TaskService) : BaseApiController() {
    @GetMapping
    fun getAll(): ResponseEntity<BaseResponse<List<TaskResponse>>> {
        val tasks = taskService.getAll(currentUser())

        return ResponseEntity.ok(
            BaseResponse(
                data = tasks.map { it.toTaskResponse() },
                success = true,
                errorMessages = emptyList()
            )
        )
    }

    @PostMapping("/create")
    fun createTask(@RequestBody request: TaskRequest): ResponseEntity<BaseResponse<TaskResponse>> {
        val task = taskService.create(currentUser(), request)

        return ResponseEntity.ok(
            BaseResponse(
                data = task.toTaskResponse(),
                success = true,
                errorMessages = emptyList()
            )
        )
    }

    @PostMapping("/update/{id}")
    fun updateTask(
        @RequestBody request: TaskRequest,
        @PathVariable id: Long
    ): ResponseEntity<BaseResponse<TaskResponse>> {
        val task = taskService.update(id, request)

        return ResponseEntity.ok(
            BaseResponse(
                data = task.toTaskResponse(),
                success = true,
                errorMessages = emptyList()
            )
        )
    }

    @DeleteMapping("/delete/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<BaseResponse<MessageResponse>> {
        val deleted = taskService.delete(id)

        return ResponseEntity.ok(
            BaseResponse(
                data = MessageResponse(
                    "Task Deleted $deleted",
                    "Task Deleted $deleted",
                ),
                success = true,
                errorMessages = emptyList()
            )
        )
    }
}
