package io.github.eendroroy.exposed.demo.controller.api.v1

import io.github.eendroroy.exposed.demo.controller.api.BaseApiController
import io.github.eendroroy.exposed.demo.dto.request.RegistrationRequest
import io.github.eendroroy.exposed.demo.dto.request.TokenRequest
import io.github.eendroroy.exposed.demo.dto.response.BaseResponse
import io.github.eendroroy.exposed.demo.dto.response.TokenResponse
import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.persistence.user.Users
import io.github.eendroroy.exposed.demo.service.UserService
import io.github.eendroroy.exposed.demo.util.JwtTokenUtil
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/token"])
class TokenController(private val userService: UserService) : BaseApiController() {
    @PostMapping("/create")
    fun register(@RequestBody request: TokenRequest): ResponseEntity<BaseResponse<TokenResponse>> {
        // TODO validate user credentials
        val token = userService.createToken(request)

        return ResponseEntity.ok(
            BaseResponse(
                data = TokenResponse(token),
                success = true,
                errorMessages = emptyList()
            )
        )
    }
}
