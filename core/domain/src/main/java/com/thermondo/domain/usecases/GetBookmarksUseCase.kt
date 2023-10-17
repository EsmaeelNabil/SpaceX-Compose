package com.thermondo.domain.usecases

import com.thermondo.data.repository.BookmarksRepository
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) {
    operator fun invoke() = bookmarksRepository.getAllBookmarksIds()
}