package br.com.timesheet.controller.response

import br.com.timesheet.model.dto.TimeLogDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.format.DateTimeFormatter

data class TimeLogResponse(
    @JsonProperty
    val employeeName: String,
    @JsonIgnore
    val timeLogList: List<TimeLogDTO> = listOf(),
    @JsonProperty("dados de registro")
    val listResponse: MutableList<String> = mutableListOf()
) {

    fun toJson(): TimeLogResponse {
        var count = 0
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").let {
            this.timeLogList.forEach { timeLogDTO ->
                if(count == 0 || count == 2) {
                    listResponse.add("Entrada as ${ it.format(timeLogDTO.registrationDateTime)}")
                } else {
                    listResponse.add("Saída as ${ it.format( timeLogDTO.registrationDateTime)}")
                }
                count++
            }
            return this
        }
    }

}