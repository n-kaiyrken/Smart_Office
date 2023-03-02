package com.example.smartoffice.data.network

import com.example.smartoffice.data.network.model.HumidityResponseDTO
import com.example.smartoffice.data.network.model.TempResponseDTO
import com.example.smartoffice.domain.HumidityResponse
import com.example.smartoffice.domain.TempResponse

class Mapper {
    fun mapDTOtoTempResponse(tempResponse: TempResponseDTO): TempResponse{
        return TempResponse(
            tempResponse.createdAt,
            tempResponse.entryId,
            tempResponse.field
        )
    }

    fun mapDTOtoHumidityResponse(humidityResponse: HumidityResponseDTO): HumidityResponse {
        return HumidityResponse(
            humidityResponse.createdAt,
            humidityResponse.entryId,
            humidityResponse.field
        )
    }
}