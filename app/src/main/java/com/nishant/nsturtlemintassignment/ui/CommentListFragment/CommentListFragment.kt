package com.nishant.nsturtlemintassignment.ui.CommentListFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishant.nsturtlemintassignment.adapter.CommentListAdapter
import com.nishant.nsturtlemintassignment.databinding.FragmentCommentListBinding
import kotlinx.coroutines.InternalCoroutinesApi

class CommentListFragment : Fragment() {

    companion object{
        private const val TAG = "CommentListFragment"
    }

    private lateinit var binding: FragmentCommentListBinding

    @InternalCoroutinesApi
    private lateinit var commentListFragmentViewModel: CommentListFragmentViewModel

    private lateinit var commentListFragmentViewModelFactory: CommentListFragmentViewModelFactory

    private lateinit var adapter: CommentListAdapter

    private lateinit var args: CommentListFragmentArgs

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommentListBinding.inflate(inflater,container, false)

        Log.i(TAG,"onCreate called")

        args = CommentListFragmentArgs.fromBundle(requireArguments())

        binding.tvHeading.text = args.title

        commentListFragmentViewModelFactory = CommentListFragmentViewModelFactory(requireActivity().application)

        commentListFragmentViewModel = ViewModelProvider(this, commentListFragmentViewModelFactory).get(
            CommentListFragmentViewModel::class.java)

        adapter = CommentListAdapter(CommentListAdapter.OnClickListener{

        })

        binding.tvStatus.visibility = View.GONE

        binding.rvCommentList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCommentList.adapter = adapter

        commentListFragmentViewModel.error.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
                commentListFragmentViewModel.resetError()
            }
        })

        commentListFragmentViewModel.getCommentList(args.title).observe(viewLifecycleOwner, Observer {commentList->
            commentList?.let {
                Log.i(TAG, "Comment List : $it")
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