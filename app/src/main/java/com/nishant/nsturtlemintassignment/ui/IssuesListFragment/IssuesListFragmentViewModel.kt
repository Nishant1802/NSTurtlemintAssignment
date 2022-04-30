package com.nishant.nsturtlemintassignment.ui.IssuesListFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nishant.nsturtlemintassignment.Repository
import com.nishant.nsturtlemintassignment.database.IssueData
import com.nishant.nsturtlemintassignment.database.IssueDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.SupervisorJob

@InternalCoroutinesApi
class IssuesListFragmentViewModel(application: Application) : ViewModel() {

    companion object{
        private const val TAG = "IssuesListFragmentViewModel"
    }

    private val _error = MutableLiveData<String?>()

    val error: LiveData<String?>
        get() = _error

    val getIssuesList: LiveData<List<IssueData>>

    @InternalCoroutinesApi
    val repository : Repository

    init {
        val dao = IssueDatabase.getDatabase(application).dao()
        repository = Repository(dao)
        getIssuesList = repository.getIssuesList
    }

    fun resetError(){
        _error.value = null
    }

    override fun onCleared() {
        super.onCleared()
        Log.v(TAG,"onCleared")
    }

}