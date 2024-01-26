package com.example.myapplication

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class Converters {

    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        if (value == null) {
            return null
        }
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(type)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String? {
        if (list == null) {
            return null
        }
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(type)
        return adapter.toJson(list)
    }

    @TypeConverter
    fun fromJson(json: String?): List<Any>? {
        if (json == null) return null

        val type: Type = Types.newParameterizedType(List::class.java, Any::class.java)
        val adapter: JsonAdapter<List<Any>> = Moshi.Builder().build().adapter(type)

        return adapter.fromJson(json)
    }

    @TypeConverter
    fun toJson(options: List<Any>?): String? {
        if (options == null) return null

        val type: Type = Types.newParameterizedType(List::class.java, Any::class.java)
        val adapter: JsonAdapter<List<Any>> = Moshi.Builder().build().adapter(type)

        return adapter.toJson(options)
    }

    @TypeConverter
    fun fromScoreEntries(scoreEntries: ScoreEntries?): String? {
        if (scoreEntries == null) return null
        val type = Types.newParameterizedType(ScoreEntries::class.java)
        val adapter: JsonAdapter<ScoreEntries> = moshi.adapter(type)
        return adapter.toJson(scoreEntries)
    }

    @TypeConverter
    fun toScoreEntries(json: String?): ScoreEntries? {
        if (json == null) return null
        val type = Types.newParameterizedType(ScoreEntries::class.java)
        val adapter: JsonAdapter<ScoreEntries> = moshi.adapter(type)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromContestantList(contestants: List<Contestant>?): String? {
        if (contestants == null) return null
        val type = Types.newParameterizedType(List::class.java, Contestant::class.java)
        val adapter: JsonAdapter<List<Contestant>> = moshi.adapter(type)
        return adapter.toJson(contestants)
    }

    @TypeConverter
    fun toContestantList(json: String?): List<Contestant>? {
        if (json == null) return null
        val type = Types.newParameterizedType(List::class.java, Contestant::class.java)
        val adapter: JsonAdapter<List<Contestant>> = moshi.adapter(type)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromVenue(venue: Venue?): String? {
        if (venue == null) return null
        val type = Types.newParameterizedType(Venue::class.java)
        val adapter: JsonAdapter<Venue> = moshi.adapter(type)
        return adapter.toJson(venue)
    }

    @TypeConverter
    fun toVenue(json: String?): Venue? {
        if (json == null) return null
        val type = Types.newParameterizedType(Venue::class.java)
        val adapter: JsonAdapter<Venue> = moshi.adapter(type)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromMatchReport(matchReport: MatchReport?): String? {
        if (matchReport == null) return null
        val adapter: JsonAdapter<MatchReport> = moshi.adapter(MatchReport::class.java)
        return adapter.toJson(matchReport)
    }

    @TypeConverter
    fun toMatchReport(json: String?): MatchReport? {
        if (json == null) return null
        val type = Types.newParameterizedType(MatchReport::class.java)
        val adapter: JsonAdapter<MatchReport> = moshi.adapter(type)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromMatchPreview(matchPreview: MatchPreview?): String? {
        if (matchPreview == null) return null
        val type = Types.newParameterizedType(MatchPreview::class.java)
        val adapter: JsonAdapter<MatchPreview> = moshi.adapter(type)
        return adapter.toJson(matchPreview)
    }

    @TypeConverter
    fun toMatchPreview(json: String?): MatchPreview? {
        if (json == null) return null
        val type = Types.newParameterizedType(MatchPreview::class.java)
        val adapter: JsonAdapter<MatchPreview> = moshi.adapter(type)
        return adapter.fromJson(json)
    }
}
