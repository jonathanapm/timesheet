package br.com.timesheet.controller.advice

import br.com.timesheet.controller.exception.EmployeeAlreadyRegisteredException
import br.com.timesheet.controller.exception.EmployeeNotFound
import br.com.timesheet.controller.exception.LimitedAttemptsException
import br.com.timesheet.controller.response.ErrorMessageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.format.DateTimeParseException

/**
 * Classe de contrato de fluxos de erros
 */
@RestControllerAdvice
class ControllerAdviceRequest {

    /**
     * Usuário não foi encontrado na base de dados
     */
    @ExceptionHandler(value = [EmployeeNotFound::class])
    fun handleEmployeeNotFound(e: EmployeeNotFound): ResponseEntity<ErrorMessageResponse> =
        ErrorMessageResponse(HttpStatus.NOT_FOUND, e.msg).let {
            ResponseEntity(it, it.status)
        }

    /**
     * Limite de registro de horas atingido para o dia
     */
    @ExceptionHandler(value = [LimitedAttemptsException::class])
    fun handleLimitedAttempts(e: LimitedAttemptsException): ResponseEntity<ErrorMessageResponse> =
        ErrorMessageResponse(HttpStatus.BAD_REQUEST, e.message).let {
            ResponseEntity(it, it.status)
        }

    /**
     * Tratativa de dados inválidos
     */
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentNotValid(e: MethodArgumentNotValidException): ResponseEntity<ErrorMessageResponse>  {
        val defaultError = e.bindingResult.allErrors[0]
        ErrorMessageResponse(HttpStatus.BAD_REQUEST, defaultError.defaultMessage).let {
            return ResponseEntity(it, it.status)
        }
    }

    /**
     * Tratativa de datas inválidas
     */
    @ExceptionHandler(value = [DateTimeParseException::class])
    fun handleDateTimeParse(e: DateTimeParseException): ResponseEntity<ErrorMessageResponse>  =
        ErrorMessageResponse(HttpStatus.BAD_REQUEST, "Informe uma data válida yyyy-MM-dd").let {
            return ResponseEntity(it, it.status)
        }

    /**
     * Funcionário existe na base de dados
     */
    @ExceptionHandler(value = [EmployeeAlreadyRegisteredException::class])
    fun handleEmployeeAlreadyRegistered(e: EmployeeAlreadyRegisteredException): ResponseEntity<ErrorMessageResponse>  =
        ErrorMessageResponse(HttpStatus.BAD_REQUEST, e.message).let {
            return ResponseEntity(it, it.status)
        }

    /**
     * Falha na autenticação
     */
    @ExceptionHandler(value = [AuthenticationException::class])
    fun handleAuthentication(e: AuthenticationException): ResponseEntity<ErrorMessageResponse>  =
        ErrorMessageResponse(HttpStatus.BAD_REQUEST, e.message).let {
            return ResponseEntity(it, it.status)
        }
}