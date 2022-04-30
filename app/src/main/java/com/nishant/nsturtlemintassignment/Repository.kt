package com.nishant.nsturtlemintassignment

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.nishant.nsturtlemintassignment.database.CommentData
import com.nishant.nsturtlemintassignment.database.Dao
import com.nishant.nsturtlemintassignment.database.IssueData
import com.nishant.nsturtlemintassignment.network.Network
import com.nishant.nsturtlemintassignment.utils.Commons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dao: Dao) {

    companion object{
        private const val TAG = "Repository"
    }

    val getIssuesList: LiveData<List<IssueData>> = dao.getIssueData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertIssueData(issueData: IssueData){
        dao.insertIssueData(issueData)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllIssueData(){
        dao.deleteAllIssueData()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCommentData(commentData: CommentData){
        dao.insertCommentData(commentData)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllCommentData(){
        dao.deleteAllCommentData()
    }

    fun getCommentData(title: String) : LiveData<List<CommentData>>{
        return dao.getCommentData(title)
    }

    //Network Call to update the user data
    suspend fun refreshIssues(){
        withContext(Dispatchers.IO){
            val issuesList = Network.myService.getIssuesList().await()

            issuesList?.let { myIssueList ->
                Log.i(TAG,"Item inserted when asleep")

                for (item in myIssueList){

                    insertIssueData(
                        IssueData(
                            title = item.title ?: "NA",
                            description = item.body ?: "NA",
                            username = item.user?.login ?: "NA",
                            avatar = item.user?.avatar_url ?: "NA",
                            updatedAt = Commons.getMillis(item.updated_at?.substringBefore('T') ?: "")
                        )
                    )

                    item.comments_url?.let {
                        refreshComments(item.title, it)
                    }

                }
            }
        }
    }

    suspend fun refreshComments(title: String?, url: String){

        val commentList = Network.myService.getCommentDetails(url).await()

        commentList?.let { myCommentList ->
            Log.i(TAG,"Item inserted when asleep")

            for (item in myCommentList){
                insertCommentData(
                    CommentData(
                        title = title ?: "NA",
                        comment = item.body ?: "NA",
                        username = item.user?.login ?: "NA",
                        avatar = item.user?.avatar_url ?: "NA",
                        commentedAt = Commons.getMillis(item.updated_at?.substringBefore('T') ?: "NA")
                    )
                )
            }

        }
    }
}