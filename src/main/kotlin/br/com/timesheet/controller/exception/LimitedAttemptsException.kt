package br.com.timesheet.controller.exception

/**
 * Exception para limite de registro diário ultrapassado
 */
class LimitedAttemptsException(
    var msg: String = "Não é possível inserir novo registro. Banco de horas do dia completo"
) : RuntimeException(msg)