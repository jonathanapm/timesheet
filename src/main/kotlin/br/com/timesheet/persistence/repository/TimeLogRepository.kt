package br.com.timesheet.persistence.repository

import br.com.timesheet.persistence.entities.TimeLogEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TimeLogRepository : JpaRepository<Long, TimeLogEntity>