package com.nishant.nsturtlemintassignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    //IssueData
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssueData(issueData: IssueData)

    @Query("DELETE FROM issue_table")
    suspend fun deleteAllIssueData()

    @Query("SELECT * FROM issue_table ORDER BY updatedAt DESC")
    fun getIssueData(): LiveData<List<IssueData>>

    //CommentsData
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommentData(commentData: CommentData)

    @Query("DELETE FROM comment_table")
    suspend fun deleteAllCommentData()

    @Query("SELECT * FROM comment_table WHERE title = :title ORDER BY commentedAt ASC")
    fun getCommentData(title: String): LiveData<List<CommentData>>

}