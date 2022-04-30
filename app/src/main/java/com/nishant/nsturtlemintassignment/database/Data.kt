package com.nishant.nsturtlemintassignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "issue_table", indices = [Index(value = ["title"], unique = true)])
data class IssueData(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "updatedAt")
    val updatedAt: Long

)


@Entity(tableName = "comment_table", indices = [Index(value = ["comment"], unique = true)])
data class CommentData(

    @ColumnInfo(name = "title")
    val title: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "comment")
    val comment: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "commentedAt")
    val commentedAt: Long

)