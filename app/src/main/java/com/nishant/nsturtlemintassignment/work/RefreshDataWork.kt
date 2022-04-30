package com.nishant.nsturtlemintassignment.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nishant.nsturtlemintassignment.Repository
import com.nishant.nsturtlemintassignment.database.IssueDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import retrofit2.HttpException

class RefreshDataWork(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params){

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
        private const val TAG = "RefreshDataWork"
    }

    @InternalCoroutinesApi
    override suspend fun doWork(): Result {
        val dao = IssueDatabase.getDatabase(applicationContext).dao()
        val repository = Repository(dao)

        return try {
            Log.v(TAG,"Fetching Issues")
            repository.refreshIssues()
            Result.success()
        }catch (e: HttpException){
            Log.e(TAG,"Error in fetching Issues :- \nResponse code : ${e.code()}\nResponse message : ${e.message()}")
            Result.retry()
        }
    }

}