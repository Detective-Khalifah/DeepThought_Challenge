package com.blogspot.thengnet.khadeepthoughtchallenge.data

import com.beust.klaxon.*

private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
    this.converter(object: Converter {
        @Suppress("UNCHECKED_CAST")
        override fun toJson(value: Any)        = toJson(value as T)
        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
    })

private val klaxon = Klaxon()
    .convert(Category::class,         { Category.fromValue(it.string!!) },         { "\"${it.value}\"" })
    .convert(CommitmentType::class,   { CommitmentType.fromValue(it.string!!) },   { "\"${it.value}\"" })
    .convert(DatumStatus::class,      { DatumStatus.fromValue(it.string!!) },      { "\"${it.value}\"" })
    .convert(AssetContentType::class, { AssetContentType.fromValue(it.string!!) }, { "\"${it.value}\"" })
    .convert(AssetType::class,        { AssetType.fromValue(it.string!!) },        { "\"${it.value}\"" })
    .convert(TaskStatus::class,       { TaskStatus.fromValue(it.string!!) },       { "\"${it.value}\"" })
    .convert(Type::class,             { Type.fromValue(it.string!!) },             { "\"${it.value}\"" })

data class Deepthought (
    val status: StatusClass? = null,
    val response: Response? = null
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Deepthought>(json)
    }
}

data class Response (
    val data: List<Datum>? = null,

    @Json(name = "per_page")
    val perPage: Long? = null,

    @Json(name = "current_page")
    val currentPage: Long? = null,

    @Json(name = "first_page_url")
    val firstPageURL: String? = null,

    @Json(name = "last_page_url")
    val lastPageURL: String? = null,

    @Json(name = "next_page_url")
    val nextPageURL: String? = null,

    @Json(name = "prev_page_url")
    val prevPageURL: Any? = null,

    @Json(name = "last_page")
    val lastPage: Long? = null,

    val from: Long? = null,
    val to: Long? = null
)

data class Datum (
    @Json(name = "_id")
    val id: String? = null,

    @Json(name = "_key")
    val key: String? = null,

    val category: Category? = null,
    val cid: Any? = null,
    val commitment: String? = null,

    @Json(name = "commitment_type")
    val commitmentType: CommitmentType? = null,

    val deadline: String? = null,
    val description: String? = null,
    val faqs: List<Any?>? = null,
    val globalTags: List<Any?>? = null,
    val isActive: Boolean? = null,
    val lastposttime: Long? = null,

    @Json(name = "learning_outcomes")
    val learningOutcomes: List<String>? = null,

    @Json(name = "mainPid")
    val mainPID: Long? = null,

    @Json(name = "native_tid")
    val nativeTid: Long? = null,

    @Json(name = "native_uid")
    val nativeUid: Long? = null,

    val postcount: Long? = null,

    @Json(name = "pre_requisites")
    val preRequisites: List<String>? = null,

    @Json(name = "project_image")
    val projectImage: String? = null,

    @Json(name = "short_description")
    val shortDescription: String? = null,

    val slug: String? = null,
    val status: DatumStatus? = null,
    val tasks: List<Task>? = null,
    val tid: Long? = null,
    val timestamp: Long? = null,
    val title: String? = null,
    val type: Type? = null,
    val uid: Long? = null,
    val viewcount: Long? = null,
    val publishedAt: Long? = null,
    val scorecardAssociationTime: Long? = null,

    @Json(name = "scorecardId")
    val scorecardID: Long? = null,

    val scorecardTitle: String? = null,
    val recruiter: Recruiter? = null,
    val macrodata: Macrodata? = null,
    val evaluatedCount: Long? = null
)

enum class Category(val value: String) {
    Course("Course"),
    Event("Event"),
    Project("Project"),
    Selection("Selection");

    companion object {
        public fun fromValue(value: String): Category = when (value) {
            "Course"    -> Course
            "Event"     -> Event
            "Project"   -> Project
            "Selection" -> Selection
            else        -> throw IllegalArgumentException()
        }
    }
}

enum class CommitmentType(val value: String) {
    Custom("custom");

    companion object {
        public fun fromValue(value: String): CommitmentType = when (value) {
            "custom" -> Custom
            else     -> throw IllegalArgumentException()
        }
    }
}

data class Macrodata (
    @Json(name = "applicant_count")
    val applicantCount: Long? = null,

    @Json(name = "pending_count")
    val pendingCount: Long? = null,

    @Json(name = "reAsigned_count")
    val reAsignedCount: Long? = null
)

data class Recruiter (
    val username: String? = null,
    val fullname: String? = null,
    val userslug: String? = null,
    val picture: String? = null,
    val uid: Long? = null,
    val displayname: String? = null,

    @Json(name = "icon:text")
    val iconText: String? = null,

    @Json(name = "icon:bgColor")
    val iconBgColor: String? = null
)

enum class DatumStatus(val value: String) {
    Published("published");

    companion object {
        public fun fromValue(value: String): DatumStatus = when (value) {
            "published" -> Published
            else        -> throw IllegalArgumentException()
        }
    }
}

data class Task (
    @Json(name = "task_id")
    val taskID: Long? = null,

    @Json(name = "task_title")
    val taskTitle: String? = null,

    @Json(name = "task_description")
    val taskDescription: String? = null,

    val status: TaskStatus? = null,
    val assets: List<Asset>? = null
)

data class Asset (
    @Json(name = "asset_id")
    val assetID: Long? = null,

    @Json(name = "asset_title")
    val assetTitle: String? = null,

    @Json(name = "asset_description")
    val assetDescription: String? = null,

    @Json(name = "asset_content")
    val assetContent: String? = null,

    @Json(name = "asset_type")
    val assetType: AssetType? = null,

    @Json(name = "asset_content_type")
    val assetContentType: AssetContentType? = null
)

enum class AssetContentType(val value: String) {
    Article("article"),
    Docs("docs"),
    Eaglebuilder("eaglebuilder"),
    Form("form"),
    Image("image"),
    Threadbuilder("threadbuilder"),
    Video("video");

    companion object {
        public fun fromValue(value: String): AssetContentType = when (value) {
            "article"       -> Article
            "docs"          -> Docs
            "eaglebuilder"  -> Eaglebuilder
            "form"          -> Form
            "image"         -> Image
            "threadbuilder" -> Threadbuilder
            "video"         -> Video
            else            -> throw IllegalArgumentException()
        }
    }
}

enum class AssetType(val value: String) {
    DisplayAsset("display_asset"),
    InputAsset("input_asset");

    companion object {
        public fun fromValue(value: String): AssetType = when (value) {
            "display_asset" -> DisplayAsset
            "input_asset"   -> InputAsset
            else            -> throw IllegalArgumentException()
        }
    }
}

enum class TaskStatus(val value: String) {
    Notworkyet("notworkyet");

    companion object {
        public fun fromValue(value: String): TaskStatus = when (value) {
            "notworkyet" -> Notworkyet
            else         -> throw IllegalArgumentException()
        }
    }
}

enum class Type(val value: String) {
    Project("project");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "project" -> Project
            else      -> throw IllegalArgumentException()
        }
    }
}

data class StatusClass (
    val code: String? = null,
    val message: String? = null
)
