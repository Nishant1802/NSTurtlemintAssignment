package com.nishant.nsturtlemintassignment.ui.IssuesListFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishant.nsturtlemintassignment.adapter.IssueListAdapter
import com.nishant.nsturtlemintassignment.databinding.FragmentIssuesListBinding
import com.nishant.nsturtlemintassignment.ui.CommentListFragment.CommentListFragment
import kotlinx.coroutines.InternalCoroutinesApi

class IssuesListFragment : Fragment() {

    companion object{
        private const val TAG = "IssuesListFragment"
    }

    private lateinit var binding: FragmentIssuesListBinding

    @InternalCoroutinesApi
    private lateinit var issuesListFragmentViewModel: IssuesListFragmentViewModel

    private lateinit var issuesListFragmentViewModelFactory: IssuesListFragmentViewModelFactory

    private lateinit var adapter: IssueListAdapter

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIssuesListBinding.inflate(inflater,container, false)

        Log.i(TAG,"onCreate called")

        issuesListFragmentViewModelFactory = IssuesListFragmentViewModelFactory(requireActivity().application)

        issuesListFragmentViewModel = ViewModelProvider(this, issuesListFragmentViewModelFactory).get(
            IssuesListFragmentViewModel::class.java)

        adapter = IssueListAdapter(IssueListAdapter.OnClickListener{issueData ->
            this.findNavController().navigate(
                IssuesListFragmentDirections
                    .actionIssuesListFragmentToCommentListFragment(issueData.title))
        })

        binding.tvStatus.visibility = View.GONE

        binding.rvIssuesList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIssuesList.adapter = adapter

        issuesListFragmentViewModel.error.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
                issuesListFragmentViewModel.resetError()
            }
        })

        issuesListFragmentViewModel.getIssuesList.observe(viewLifecycleOwner, Observer {usersList->
            usersList?.let {
                Log.i(TAG, "Issues List : $it")
                if (it.isEmpty()){
                    binding.tvStatus.visibility = View.VISIBLE
                }else{
                    binding.tvStatus.visibility = View.GONE
                }
                adapter.submitList(it)
            }
        })

        return binding.root
    }

}