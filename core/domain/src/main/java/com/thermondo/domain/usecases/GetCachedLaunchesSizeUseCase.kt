package com.thermondo.domain.usecases

import com.thermondo.data.repository.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachedLaunchesSizeUseCase @Inject constructor(
    private val launchesRepository: LaunchesRepository
) {
    operator fun invoke(): Flow<Int> {
        return launchesRepository.getCachedLaunchesCount()
    }
}