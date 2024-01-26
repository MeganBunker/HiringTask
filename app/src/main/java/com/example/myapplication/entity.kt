package com.example.myapplication

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val tableid: Long = 0,

    @Embedded
    @Json(name = "liveData")
    val liveData: LiveData,

    @Embedded
    @Json(name = "matchInfo")
    val matchInfo: MatchInfo
)

@JsonClass(generateAdapter = true)
data class LiveData(
    @Json(name = "matchStatus")
    val matchStatus: String,

    @Json(name = "isLive")
    val isLive: Boolean,

    @Json(name = "home_score")
    val homeScore: Int,

    @Json(name = "away_score")
    val awayScore: Int,

    @Json(name = "scoreEntries")
    val scoreEntries: ScoreEntries,

    @Json(name = "match_winner")
    val matchWinner: String,

    @Json(name = "match_length_min")
    val matchLengthMin: Int
)

@JsonClass(generateAdapter = true)
data class ScoreEntries(
    @Json(name = "ft")
    val ft: Score,

    @Json(name = "total")
    val total: Score
)

@JsonClass(generateAdapter = true)
data class Score(
    @Json(name = "home_score")
    val homeScore: Int,

    @Json(name = "away_score")
    val awayScore: Int
)

@JsonClass(generateAdapter = true)
data class MatchInfo(
    @Json(name = "id")
    val id: Int,

    @Json(name = "externalId")
    val externalId: String,

    @Json(name = "legacyId")
    val legacyId: String,

    @Json(name = "date")
    val date: Long,

    @Json(name = "description")
    val description: String,

    @Json(name = "contestant")
    val contestant: List<Contestant>,

    @Json(name = "venue")
    val venue: Venue,

    @Json(name = "matchReport")
    val matchReport: MatchReport? = null,

    @Json(name = "matchPreview")
    val matchPreview: MatchPreview? = null
)

@JsonClass(generateAdapter = true)
data class Contestant(
    @Json(name = "code")
    val code: String,

    @Json(name = "crest")
    val crest: Crest,

    @Json(name = "externalId")
    val externalId: String,

    @Json(name = "id")
    val id: Int,

    @Json(name = "legacyId")
    val legacyId: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "officialName")
    val officialName: String,

    @Json(name = "position")
    val position: String,

    @Json(name = "shortName")
    val shortName: String
)

@JsonClass(generateAdapter = true)
data class Venue(
    @Json(name = "id")
    val id: Int,

    @Json(name = "externalId")
    val externalId: String,

    @Json(name = "longName")
    val longName: String,

    @Json(name = "shortName")
    val shortName: String
)

@JsonClass(generateAdapter = true)
data class Crest(
    @Json(name = "uri_1x")
    val uri1x: String,

    @Json(name = "uri_1x-dark")
    val uri1xDark: String,

    @Json(name = "uri_2x")
    val uri2x: String,

    @Json(name = "uri_2x-dark")
    val uri2xDark: String,

    @Json(name = "uri_3x")
    val uri3x: String,

    @Json(name = "uri_3x-dark")
    val uri3xDark: String
)

@JsonClass(generateAdapter = true)
data class MatchReport(
    @Json(name = "id")
    val id: String?,

    @Json(name = "path")
    val path: String?
)

@JsonClass(generateAdapter = true)
data class MatchPreview(
    @Json(name = "id")
    val id: String?,

    @Json(name = "path")
    val path: String?
)



/*

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val current: Int,
    val currentExternalId: String,
    val currentLegacyId: String,
    @Embedded(prefix = "liveData_")
    val liveData: LiveDataEntity?,

    @Embedded(prefix = "matchInfo_")
    val matchInfo: MatchInfoEntity?
)

data class LiveDataEntity(
    val matchStatus: String,
    val isLive: Boolean,
    val home_score: Int,
    val away_score: Int,
    val match_winner: String,
    val match_length_min: Int
)

data class MatchInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val externalId: String,
    val legacyId: String,
    val date: Long,
    val tbc: Boolean,
    val lastUpdated: Long,
    val externalLastUpdated: Long,
    val description: String,

    @Embedded(prefix = "venue_")
    val venue: VenueEntity,

    @Embedded(prefix = "matchReport_")
    val matchReport: MatchReportEntity?,

    @Embedded(prefix = "matchPreview_")
    val matchPreview: MatchPreviewEntity?,

    @Embedded(prefix = "firstCallToAction_")
    val firstCallToAction: CallToActionEntity?,

    @Embedded(prefix = "SecondCallToAction_")
    val secondCallToAction: CallToActionEntity?,
    val relatedTags: List<String>? = null
) {
    // Empty constructor or constructor with parameters matching the fields can be added if needed
}

data class VenueEntity(
    val id: Int,
    val externalId: String,
    val longName: String,
    val shortName: String
)

data class MatchReportEntity(
    val id: String?,
    val path: String?
)

data class MatchPreviewEntity(
    val id: String?,
    val path: String?
)

data class CallToActionEntity(
    var uri: String? = null,
    var title: String? = null,
    var options: List<Any>? = null,
    var icon: String? = null
)

@Entity(tableName = "related_tags")
data class RelatedTagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "tag")
    val tag: String
*/