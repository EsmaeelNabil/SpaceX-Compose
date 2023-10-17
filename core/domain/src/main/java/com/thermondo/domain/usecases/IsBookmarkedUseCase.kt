package com.thermondo.domain.usecases

import com.thermondo.data.repository.BookmarksRepository
import javax.inject.Inject

class IsBookmarkedUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) {
    suspend operator fun invoke(launchId: String) =
        bookmarksRepository.isBookmarked(launchId)
}