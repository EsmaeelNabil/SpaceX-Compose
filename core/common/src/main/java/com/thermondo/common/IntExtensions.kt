package com.thermondo.common

/**
 * For Delete operations in Room:
 * A return value of 0 indicates that no rows were deleted,
 * which may or may not be considered a "success"
 * A value greater than 0 indicates the number of rows successfully deleted.
 */
fun Int.isRemovedSuccessfully() = this > 0

/**
 * used in [LocalBookmarksRepository] isBookmarked
 */
fun Int.exists() = this > 0
