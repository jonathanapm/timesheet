package br.com.timesheet.service

import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.model.util.Mapper
import br.com.timesheet.persistence.entities.EmployeeEntity
import br.com.timesheet.persistence.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeService {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    fun saveEmployee(employeeDTO: EmployeeDTO) {
        try {
            employeeRepository.save(Mapper.convert(employeeDTO))
        } catch (e: Exception) {
            println("Erro ao cadastrar usuário, error=$e")
        }
    }

    fun deleteEmployee(employeeId: Long) = employeeRepository.deleteById(employeeId)

    fun findEmployeeById(employeeId: Long): Optional<EmployeeEntity> = employeeRepository.findById(employeeId)

    fun getAllEmployees(): MutableList<EmployeeEntity> = employeeRepository.findAll()
}