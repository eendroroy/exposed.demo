package io.github.eendroroy.exposed.demo.controller.api.v1

import io.github.eendroroy.exposed.demo.controller.api.BaseApiController
import io.github.eendroroy.exposed.demo.dto.request.RegistrationRequest
import io.github.eendroroy.exposed.demo.dto.response.BaseResponse
import io.github.eendroroy.exposed.demo.dto.response.MessageResponse
import io.github.eendroroy.exposed.demo.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/user"])
class UserController(
    private val userService: UserService,
) : BaseApiController() {
    @PostMapping("/register")
    fun register(@RequestBody request: RegistrationRequest): ResponseEntity<BaseResponse<MessageResponse>> {
        try {
            userService.register(request)
        } catch (ex: Exception) {
            LoggerFactory.getLogger(this::class.java).error(ex.localizedMessage, ex)
        }
        // TODO handle failed/invalid cases
        return ResponseEntity.ok(
            BaseResponse(
                data = MessageResponse(
                    message = "Registration Successful",
                    details = "Registration Successful"
                ),
                success = true,
                errorMessages = emptyList()
            )
        )
    }
}
