package br.com.timesheet.persistence.entities

import br.com.timesheet.persistence.enum.PhoneType
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "EMPLOYEE")
@Entity(name = "Employee")
data class Employee(
    @Id
    @Column(name = "employeeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String = "",

    @Column(name = "birth_date", nullable = false)
    val birthDate: LocalDate,

    @Column(nullable = false)
    val document: String,

    @Column(nullable = false)
    val office: String,

    @Column(name = "complete_name")
    val completeName: String? = null,

    val phone: String? = null,

    @Column(name = "phone_type")
    @Enumerated(value = EnumType.STRING)
    val phoneType: PhoneType = PhoneType.MOBILE,

    @Column(name = "create_date", nullable = false)
    val createDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val email: String,

    @Column(name = "login_security", nullable = false)
    val loginSecurity: String,

    @ManyToMany(fetch = FetchType.EAGER)
    val profiles: List<Profile>,

    @OneToMany(mappedBy = "employee")
    val timeLogList: List<TimeLog>
): UserDetails {

    override fun getAuthorities() = profiles

    override fun getPassword() =  loginSecurity

    override fun getUsername() = email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}