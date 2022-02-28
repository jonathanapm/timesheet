package br.com.timesheet.controller.advice

import br.com.timesheet.controller.exception.ApplicationError
import br.com.timesheet.controller.exception.EmployeeNotFound
import br.com.timesheet.controller.exception.LimitedAttemptsException
import br.com.timesheet.controller.response.ErrorMessageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Classe de contrato de fluxos de erros
 */
@ControllerAdvice
class ControllerAdviceRequest {

    /**
     * Ocorreu um erro interno
     */
    @ExceptionHandler(value = [ApplicationError::class])
    fun handleApplicationError(e: ApplicationError): ResponseEntity<ErrorMessageResponse> =
        ErrorMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.message).let {
            ResponseEntity(it, it.status)
        }

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
        ErrorMessageResponse(HttpStatus.FORBIDDEN, e.message).let {
            ResponseEntity(it, it.status)
        }
}