package com.thermondo.database.util

import androidx.room.TypeConverter
import com.thermondo.database.model.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StringListConverter {

    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(",")
    }
}

class CoreEntityConverter {
    private val json = Json

    @TypeConverter
    fun fromStringToCoreEntity(content: String?): List<CoreEntity> {
        return content?.let {
            json.decodeFromString<List<CoreEntity>>(it)
        } ?: emptyList()
    }

    @TypeConverter
    fun fromCoreEntityToString(entity: List<CoreEntity>): String {
        return json.encodeToString(entity)
    }
}

class CrewEntityConverter {
    private val json = Json

    @TypeConverter
    fun fromStringToCrewEntity(content: String?): List<CrewEntity> {
        return content?.let {
            json.decodeFromString<List<CrewEntity>>(it)
        } ?: emptyList()
    }

    @TypeConverter
    fun fromCrewEntityToString(entity: List<CrewEntity>): String {
        return json.encodeToString(entity)
    }
}

class FailureEntityConverter {
    private val json = Json

    @TypeConverter
    fun fromStringToFailureEntity(content: String?): List<FailureEntity> {
        return content?.let {
            json.decodeFromString<List<FailureEntity>>(it)
        } ?: emptyList()
    }

    @TypeConverter
    fun fromFailureEntityToString(entity: List<FailureEntity>): String {
        return json.encodeToString(entity)
    }
}

