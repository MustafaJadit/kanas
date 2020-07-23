package com.kodyuzz.kanas.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kodyuzz.kanas.R
import com.kodyuzz.kanas.di.component.FragmentComponent
import com.kodyuzz.kanas.ui.base.BaseFragment
import com.kodyuzz.kanas.ui.home.posts.PostAdapter
import com.kodyuzz.kanas.ui.main.MainSharedViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {
    companion object {

        const val TAG: String = "HomeFragment"

        fun newInstance(): HomeFragment {
            val arg = Bundle()
            val frament = HomeFragment()
            frament.arguments = arg
            return frament
        }
    }

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var postsAdapter: PostAdapter

    override fun provideLayoutId():Int= R.layout.fragment_home

    override fun injectDependencies(buildFragmentComponent: FragmentComponent) {
       buildFragmentComponent.inject(this)
    }

    override fun setupObservers(){
        super.setupObservers()

        viewMode.loading.observe(this, Observer {
            progressBar.visibility=if (it)View.VISIBLE else View.GONE
        })

        viewMode.posts.observe(this, Observer {
            it.data?.run { postsAdapter.appendData(this) }
        })

        mainSharedViewModel.newPost.observe(this, Observer {
            it.getIfNotHandled()?.run { viewMode.onNewPost(this) }
        })

        viewMode.refreshPosts.observe(this, Observer {
            it.data?.run {
                postsAdapter.updateList(this)
                rvPosts.scrollToPosition(0)
            }
        })
    }

    override fun setupVIew(view: View) {
       rvPosts.apply {
           layoutManager=linearLayoutManager
           adapter=postsAdapter
           addOnScrollListener(object :RecyclerView.OnScrollListener(){
               override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                   super.onScrolled(recyclerView, dx, dy)
                   layoutManager?.run {
                       if (this is LinearLayoutManager
                           && itemCount >0
                           && itemCount == findLastVisibleItemPosition()+1
                       )
                           viewMode.onLoadMore()

                   }
               }
           })
       }
    }



}