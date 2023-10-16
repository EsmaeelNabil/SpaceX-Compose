package com.thermondo.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.thermondo.common.Constants.PAGING_PAGE_SIZE
import com.thermondo.common.Constants.PAGING_PREFETCH_DISTANCE
import com.thermondo.common.di.Dispatcher
import com.thermondo.common.di.ThermondoDispatchers.IO
import com.thermondo.data.model.asExternalModel
import com.thermondo.data.repository.LaunchesRepository
import com.thermondo.model.data.Launch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPagedLaunchesUseCase @Inject constructor(
    private val launchesRepository: LaunchesRepository,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<PagingData<Launch>> = Pager(
        PagingConfig(
            pageSize = PAGING_PAGE_SIZE,
            prefetchDistance = PAGING_PREFETCH_DISTANCE
        )
    ) {
        launchesRepository.getLaunchesPaged()
    }.flow.map { value ->
        value.map { launchEntity ->
            launchEntity.asExternalModel()
        }
    }.flowOn(dispatcher)
}