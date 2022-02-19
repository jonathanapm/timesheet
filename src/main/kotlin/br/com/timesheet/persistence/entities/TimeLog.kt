package br.com.timesheet.persistence.entities

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "TIME_LOG")
@Entity(name = "TimeLog")
data class TimeLog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "registration_date_time", nullable = false)
    val registrationDateTime: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    val employee: Employee
)