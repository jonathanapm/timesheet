package br.com.timesheet.auth

import br.com.timesheet.persistence.repository.EmployeeRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val employeeRepository: EmployeeRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        employeeRepository.findByEmail(username)
            .map { it }
            .orElseThrow { throw UsernameNotFoundException("Invalid data") }
}