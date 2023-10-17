package com.thermondo.common

/**
 * For Insertions in Room:
 * A return value of -1 indicates that the insertion has failed.
 * Any value greater than or equal to 0 is the row ID and indicates success.
 */
fun Long.isAddedSuccessfully() = this != -1L
