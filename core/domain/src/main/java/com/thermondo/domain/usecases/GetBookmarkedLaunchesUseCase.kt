package com.thermondo.domain.usecases

import com.thermondo.data.model.asExternalModel
import com.thermondo.data.repository.BookmarksRepository
import com.thermondo.data.repository.LaunchesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetBookmarkedLaunchesUseCase @Inject constructor(
    private val launchesRepository: LaunchesRepository,
    private val bookmarksRepository: BookmarksRepository
) {
    operator fun invoke() = bookmarksRepository.getAllBookmarksIds().flatMapConcat {
        launchesRepository
            .getLaunchesByIds(it.map { it.launchId }.toSet())
            .map { launchEntities ->
                launchEntities.map { entity ->
                    entity.asExternalModel()
                }
            }
    }
}