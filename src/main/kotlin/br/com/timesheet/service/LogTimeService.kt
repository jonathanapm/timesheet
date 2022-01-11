package br.com.timesheet.service

import br.com.timesheet.persistence.repository.TimeLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LogTimeService {

    @Autowired
    private lateinit var timeLogRepository: TimeLogRepository

    fun registerTime() {

    }

    fun resumeByFilter() {

    }

}