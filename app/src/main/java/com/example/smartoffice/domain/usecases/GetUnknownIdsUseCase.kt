package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.Repository
import com.example.smartoffice.domain.models.UnknownId

class GetUnknownIdsUseCase(val repository: Repository) {

    suspend operator fun invoke(onUnknownIdsLoaded: (List<UnknownId>) -> Unit) {
        repository.getUnknownIds(onUnknownIdsLoaded)
    }
}