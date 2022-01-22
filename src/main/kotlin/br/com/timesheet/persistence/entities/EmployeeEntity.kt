package br.com.timesheet.persistence.entities

import br.com.timesheet.persistence.enum.PhoneType
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Table(name = "EMPLOYEE")
@Entity(name = "Employee")
data class EmployeeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String = "",

    @Column(name = "birth_date", nullable = false)
    val birthDate: LocalDateTime ,

    @Column(nullable = false)
    val office: String,

    @Column(name = "complete_name")
    val completeName: String? = null,

    val phone: String? = null,

    @Column(name = "phone_type")
    @Enumerated(value = EnumType.STRING)
    val phoneType: PhoneType? = null,

    @Column(name = "create_date", nullable = false)
    val createDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_date", nullable = false)
    val updateDate: LocalDateTime = LocalDateTime.now()

    //@OneToMany(mappedBy = "employee_id")
    //val registers: List<TimeLogEntity> = listOf()
)