package br.com.timesheet.controller.exception

/**
 * Exception para erros internos
 */
data class ApplicationError(
    var msg: String = "Erro interno",
    var throwable: Throwable = InternalError()
): RuntimeException(msg, throwable)