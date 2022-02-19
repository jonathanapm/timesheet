package br.com.timesheet.service

import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.model.util.Mapper
import br.com.timesheet.persistence.entities.EmployeeEntity
import br.com.timesheet.persistence.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeService {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    fun saveEmployee(employeeDTO: EmployeeDTO): EmployeeDTO =
        try {
            employeeRepository.save(Mapper.convert<EmployeeDTO, EmployeeEntity>(employeeDTO)).let(Mapper::convert)
        } catch (e: RuntimeException) {
            throw RuntimeException("Erro ao salvar novo funcionário", e)
        }

    fun deleteEmployee(employeeId: Long): Unit =
        try {
            employeeRepository.findById(employeeId).map(employeeRepository::delete).orElseThrow {
                throw RuntimeException("Usuário não encontrado")
            }
        } catch (e: RuntimeException) {
            throw RuntimeException("Erro ao remover funcinário")
        }

    fun findEmployeeById(employeeId: Long): EmployeeDTO =
        try {
            employeeRepository.findById(employeeId).map {
                Mapper.convert<EmployeeEntity, EmployeeDTO>(it)
            }.orElseThrow {
                throw RuntimeException("Usuário não encontrado")
            }
        } catch (e: RuntimeException) {
            throw RuntimeException("Erro na busca de funcionário", e)
        }

    fun findByDocument(document: String): EmployeeDTO =
        try {
            employeeRepository.findByDocument(document).map {
                Mapper.convert<EmployeeEntity, EmployeeDTO>(it)
            }.orElseThrow {
                throw RuntimeException("Usuário não encontrado")
            }
        }catch (e: RuntimeException) {
            throw RuntimeException("Erro na busca de funcionário por documento", e)
        }
}