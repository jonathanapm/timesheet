package br.com.timesheet.persistence.entities

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "TIME_LOG")
@Entity(name = "TimeLog")
data class TimeLogEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "registration_date_time", nullable = false)
    val registrationDateTime: LocalDateTime = LocalDateTime.now(),

    //@ManyToOne
    //val employee: EmployeeEntity
)