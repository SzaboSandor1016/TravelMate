package com.example.features.savetrip.domain.usecases

import com.example.features.savetrip.domain.repository.SaveTripRepository

class GetNewContributorByUsernameUseCase(
    private val saveTripRepository: SaveTripRepository
) {

    suspend operator fun invoke(username: String) {

        saveTripRepository.getNewContributorByUsername(
            username = username
        )
    }
}