package com.thermondo.database.model.launch

import androidx.room.*

@Entity(tableName = "launches")
data class LaunchEntity(
    @PrimaryKey
    val id: String,
    val autoUpdate: Boolean,
    val capsules: List<String>,
    val cores: List<CoreEntity>,
    val crew: List<CrewEntity>,
    val dateLocal: String,
    val datePrecision: String,
    val dateUnix: Int,
    val dateUtc: String,
    val details: String?,
    val failures: List<FailureEntity>,
    @Embedded
    val fairings: FairingsEntity?,
    val flightNumber: Int,
    val launchLibraryId: String?,
    val launchpad: String,
    @Embedded
    val links: LinksEntity,
    val name: String,
    val net: Boolean,
    val payloads: List<String>,
    val rocket: String,
    @ColumnInfo(name = "launch_ships")
    val ships: List<String>,
    val staticFireDateUnix: Int?,
    val staticFireDateUtc: String?,
    val success: Boolean?,
    val tbd: Boolean,
    val upcoming: Boolean,
    val window: Int?
)

