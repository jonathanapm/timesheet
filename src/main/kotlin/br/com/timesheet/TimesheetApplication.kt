package br.com.timesheet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class TimesheetApplication

fun main(args: Array<String>) {
	runApplication<TimesheetApplication>(*args)
}
