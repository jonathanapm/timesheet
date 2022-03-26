package br.com.timesheet.controller

import br.com.timesheet.model.dto.LoginDTO
import br.com.timesheet.auth.AuthenticationTokenService
import br.com.timesheet.controller.response.AuthenticationResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val authenticationTokenService: AuthenticationTokenService
) {

    @PostMapping
    fun doLogin(@RequestBody @Valid login: LoginDTO): ResponseEntity<AuthenticationResponse> =
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(login.email, login.password))
            .let {
                return ResponseEntity.ok()
                    .body(AuthenticationResponse(token = authenticationTokenService.generatedToken(it)))
            }
}