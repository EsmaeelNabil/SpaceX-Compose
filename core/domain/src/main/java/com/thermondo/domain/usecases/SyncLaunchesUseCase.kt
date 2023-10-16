package com.thermondo.domain.usecases

import com.thermondo.data.repository.LaunchesRepository
import javax.inject.Inject

class SyncLaunchesUseCase @Inject constructor(
    private val launchesRepository: LaunchesRepository
) {
    suspend operator fun invoke() = launchesRepository.syncLaunches()
}