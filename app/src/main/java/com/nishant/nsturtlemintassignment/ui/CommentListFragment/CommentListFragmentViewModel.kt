package com.nishant.nsturtlemintassignment.ui.CommentListFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishant.nsturtlemintassignment.Repository
import com.nishant.nsturtlemintassignment.database.CommentData
import com.nishant.nsturtlemintassignment.database.IssueDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class CommentListFragmentViewModel(application: Application) : ViewModel() {

    companion object{
        private const val TAG = "CommentListFragmentViewModel"
    }

    private val _error = MutableLiveData<String?>()

    val error: LiveData<String?>
        get() = _error

    @InternalCoroutinesApi
    val repository : Repository

    init {
        val dao = IssueDatabase.getDatabase(application).dao()
        repository = Repository(dao)
    }

    fun resetError(){
        _error.value = null
    }

    fun getCommentList(title: String) : LiveData<List<CommentData>> {
        return repository.getCommentData(title)
    }

    override fun onCleared() {
        super.onCleared()
        Log.v(TAG,"onCleared")
    }

}