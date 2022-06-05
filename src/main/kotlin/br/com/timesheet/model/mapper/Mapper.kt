package br.com.timesheet.model.mapper

interface Mapper<T, U> {

    fun map(t: T): U
}