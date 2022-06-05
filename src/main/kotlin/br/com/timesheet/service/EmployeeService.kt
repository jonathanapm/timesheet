package br.com.timesheet.service

import br.com.timesheet.controller.exception.EmployeeAlreadyRegisteredException
import br.com.timesheet.controller.exception.EmployeeNotFound
import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.model.mapper.Mapper
import br.com.timesheet.model.mapper.ToEmployeeDTOMapper
import br.com.timesheet.model.mapper.ToEmployeeMapper
import br.com.timesheet.persistence.entities.Employee
import br.com.timesheet.persistence.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val toEmployeeMapper: ToEmployeeMapper,
    private val toEmployeeDTOMapper: ToEmployeeDTOMapper
) {

    /**
     * Salva um funcionário na base de dados
     * @param employeeDTO dados do novo funcionário
     * @return dados do funcionário salvo
     */
    fun saveEmployee(employeeDTO: EmployeeDTO): EmployeeDTO {
        try {
            employeeRepository.findByDocument(employeeDTO.document).map {
                throw EmployeeAlreadyRegisteredException("Funcionário já existe para o documento ${employeeDTO.document}")
            }
            return employeeRepository.save(toEmployeeMapper.map(employeeDTO))
                .let(toEmployeeDTOMapper::map)
        } catch (e: RuntimeException) {
            throw RuntimeException("Erro ao salvar funcionário", e)
        }
    }

    /**q
     * Deleta um funcionário da base
     * @param employeeId identificar do funcionário
     */
    fun deleteEmployee(employeeId: Long): Unit =
        try {
            employeeRepository.findById(employeeId)
                .map(employeeRepository::delete)
                .orElseThrow(::EmployeeNotFound)
        } catch (e: RuntimeException) {
            throw RuntimeException("Erro ao deletar funcionário", e)
        }

    /**
     * Busca um funcionário na base
     * @param employeeId identificador do funcionário
     * @return dados do funcionário encontrado
     */
    fun findEmployee(employeeId: Long): EmployeeDTO =
        try {
            employeeRepository.findById(employeeId).map(toEmployeeDTOMapper::map).orElseThrow(::EmployeeNotFound)
        } catch (e: RuntimeException) {
            throw RuntimeException("Erro ao buscar funcionário por id", e)
        }

    /**
     * Busca um funcionário na base
     * @param document documento do funcionário
     * @return dados do funcionário encontrado
     */
    fun findEmployee(document: String): EmployeeDTO =
        employeeRepository.findByDocument(document).map(toEmployeeDTOMapper::map).orElseThrow(::EmployeeNotFound)
}