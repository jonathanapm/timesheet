package br.com.timesheet.controller.exception

import org.springframework.data.crossstore.ChangeSetPersister

data class EmployeeAlreadyRegisteredException(
    var msg: String = "Usuário já existente na base",
): RuntimeException(msg)