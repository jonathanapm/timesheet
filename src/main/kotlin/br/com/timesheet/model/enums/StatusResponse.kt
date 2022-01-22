package br.com.timesheet.model.enums

import org.springframework.http.HttpStatus

enum class StatusResponse(
    val message: String,
    val httpStatus: HttpStatus
) {
    CREATE_EMPLOYEE_SUCCESS("Usuário criado com sucesso", HttpStatus.CREATED)

}