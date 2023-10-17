package com.thermondo.database.model.launch

import androidx.room.ColumnInfo

data class FairingsEntity(
    val recovered: Boolean?,
    val recoveryAttempt: Boolean?,
    val reused: Boolean?,
    @ColumnInfo(name = "fairings_ships")
    val ships: List<String>
)