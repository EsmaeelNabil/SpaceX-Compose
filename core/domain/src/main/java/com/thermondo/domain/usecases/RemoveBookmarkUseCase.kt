package com.thermondo.domain.usecases

import com.thermondo.data.repository.BookmarksRepository
import javax.inject.Inject

class RemoveBookmarkUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) {
    suspend operator fun invoke(launchId: String) =
        bookmarksRepository.removeBookmark(launchId)
}