package br.com.timesheet.controller.exception

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

/**
 * Expection para usuários não encontrados
 */
data class EmployeeNotFound(
    var msg: String = "Usuário não encontrado",
    var throwable: Throwable = NotFoundException(),
): RuntimeException(msg, throwable)