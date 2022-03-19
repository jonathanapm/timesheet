package br.com.timesheet.persistence.entities

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "TIME_LOG")
@Entity(name = "TimeLog")
data class TimeLog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "registration_date_time", nullable = false)
    val registrationDateTime: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "employeeId")
    val employee: Employee
)