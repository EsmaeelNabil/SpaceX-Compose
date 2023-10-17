package com.thermondo.bookmarks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thermondo.bookmarks.BookmarksScreen
import com.thermondo.bookmarks.BookmarksViewModel

const val bookmarksNavigationRoute = "bookmarks_route"

fun NavController.navigateToBookmarks() {
    this.navigate(bookmarksNavigationRoute)
}

fun NavGraphBuilder.bookmarksScreen(onLaunchClick: (String) -> Unit) {
    composable(
        route = bookmarksNavigationRoute,
    ) {
        BookmarksRoute(onLaunchClick)
    }
}

@Composable
internal fun BookmarksRoute(
    onLaunchClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    BookmarksScreen(
        modifier = modifier,
        state = state,
        onLaunchClick = onLaunchClick,
        resetErrorState = { viewModel.resetErrorState() },
        onRemoveBookmark = { launch -> viewModel.removeBookmark(launch.id) },
    )
}
