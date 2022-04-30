package com.nishant.nsturtlemintassignment.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//github issues
@JsonClass(generateAdapter = true)
data class Response (

    @Json(name = "url") val url : String?,
    @Json(name = "repository_url") val repository_url : String?,
    @Json(name = "labels_url") val labels_url : String?,
    @Json(name = "comments_url") val comments_url : String?,
    @Json(name = "events_url") val events_url : String?,
    @Json(name = "html_url") val html_url : String?,
    @Json(name = "id") val id : Int?,
    @Json(name = "node_id") val node_id : String?,
    @Json(name = "number") val number : Int?,
    @Json(name = "title") val title : String?,
    @Json(name = "user") val user : User?,
    @Json(name = "labels") val labels : List<Labels>?,
    @Json(name = "state") val state : String?,
    @Json(name = "locked") val locked : Boolean?,
    @Json(name = "assignee") val assignee : String?,
    @Json(name = "assignees") val assignees : List<String>?,
    @Json(name = "milestone") val milestone : Milestone?,
    @Json(name = "comments") val comments : Int?,
    @Json(name = "created_at") val created_at : String?,
    @Json(name = "updated_at") val updated_at : String?,
    @Json(name = "closed_at") val closed_at : String?,
    @Json(name = "author_association") val author_association : String?,
    @Json(name = "active_lock_reason") val active_lock_reason : String?,
    @Json(name = "body") val body : String?,
    @Json(name = "reactions") val reactions : Reactions?,
    @Json(name = "timeline_url") val timeline_url : String?,
    @Json(name = "performed_via_github_app") val performed_via_github_app : String?
)

//github issue comments
@JsonClass(generateAdapter = true)
data class Response2 (

    @Json(name = "url") val url : String?,
    @Json(name = "html_url") val html_url : String?,
    @Json(name = "issue_url") val issue_url : String?,
    @Json(name = "id") val id : Int?,
    @Json(name = "node_id") val node_id : String?,
    @Json(name = "user") val user : User?,
    @Json(name = "created_at") val created_at : String?,
    @Json(name = "updated_at") val updated_at : String?,
    @Json(name = "author_association") val author_association : String?,
    @Json(name = "body") val body : String?,
    @Json(name = "reactions") val reactions : Reactions?,
    @Json(name = "performed_via_github_app") val performed_via_github_app : String?
)

@JsonClass(generateAdapter = true)
data class Milestone (

    @Json(name = "url") val url : String?,
    @Json(name = "html_url") val html_url : String?,
    @Json(name = "labels_url") val labels_url : String?,
    @Json(name = "id") val id : Int?,
    @Json(name = "node_id") val node_id : String?,
    @Json(name = "number") val number : Int?,
    @Json(name = "title") val title : String?,
    @Json(name = "description") val description : String?,
    @Json(name = "creator") val creator : Creator?,
    @Json(name = "open_issues") val open_issues : Int?,
    @Json(name = "closed_issues") val closed_issues : Int?,
    @Json(name = "state") val state : String?,
    @Json(name = "created_at") val created_at : String?,
    @Json(name = "updated_at") val updated_at : String?,
    @Json(name = "due_on") val due_on : String?,
    @Json(name = "closed_at") val closed_at : String?
)

@JsonClass(generateAdapter = true)
data class Creator (

    @Json(name = "login") val login : String?,
    @Json(name = "id") val id : Int?,
    @Json(name = "node_id") val node_id : String?,
    @Json(name = "avatar_url") val avatar_url : String?,
    @Json(name = "gravatar_id") val gravatar_id : String?,
    @Json(name = "url") val url : String?,
    @Json(name = "html_url") val html_url : String?,
    @Json(name = "followers_url") val followers_url : String?,
    @Json(name = "following_url") val following_url : String?,
    @Json(name = "gists_url") val gists_url : String?,
    @Json(name = "starred_url") val starred_url : String?,
    @Json(name = "subscriptions_url") val subscriptions_url : String?,
    @Json(name = "organizations_url") val organizations_url : String?,
    @Json(name = "repos_url") val repos_url : String?,
    @Json(name = "events_url") val events_url : String?,
    @Json(name = "received_events_url") val received_events_url : String?,
    @Json(name = "type") val type : String?,
    @Json(name = "site_admin") val site_admin : Boolean?
)

@JsonClass(generateAdapter = true)
data class Labels (

    @Json(name = "id") val id : Int?,
    @Json(name = "node_id") val node_id : String?,
    @Json(name = "url") val url : String?,
    @Json(name = "name") val name : String?,
    @Json(name = "color") val color : String?,
    @Json(name = "default") val default : Boolean?,
    @Json(name = "description") val description : String?

)

@JsonClass(generateAdapter = true)
data class Reactions (

    @Json(name = "url") val url : String?,
    @Json(name = "total_count") val total_count : Int?,
    @Json(name = "+1") val plus1 : Int?,
    @Json(name = "-1") val minus1 : Int?,
    @Json(name = "laugh") val laugh : Int?,
    @Json(name = "hooray") val hooray : Int?,
    @Json(name = "confused") val confused : Int?,
    @Json(name = "heart") val heart : Int?,
    @Json(name = "rocket") val rocket : Int?,
    @Json(name = "eyes") val eyes : Int?

)

@JsonClass(generateAdapter = true)
data class User (

    @Json(name = "login") val login : String?,
    @Json(name = "id") val id : Int?,
    @Json(name = "node_id") val node_id : String?,
    @Json(name = "avatar_url") val avatar_url : String?,
    @Json(name = "gravatar_id") val gravatar_id : String?,
    @Json(name = "url") val url : String?,
    @Json(name = "html_url") val html_url : String?,
    @Json(name = "followers_url") val followers_url : String?,
    @Json(name = "following_url") val following_url : String?,
    @Json(name = "gists_url") val gists_url : String?,
    @Json(name = "starred_url") val starred_url : String?,
    @Json(name = "subscriptions_url") val subscriptions_url : String?,
    @Json(name = "organizations_url") val organizations_url : String?,
    @Json(name = "repos_url") val repos_url : String?,
    @Json(name = "events_url") val events_url : String?,
    @Json(name = "received_events_url") val received_events_url : String?,
    @Json(name = "type") val type : String?,
    @Json(name = "site_admin") val site_admin : Boolean?
)

