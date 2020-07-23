package com.kodyuzz.kanas.ui.home.posts

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.kodyuzz.kanas.data.model.Post
import com.kodyuzz.kanas.ui.base.BaseAdapter

class PostAdapter(
    parentLifecycle: Lifecycle,
    posts: ArrayList<Post>
):BaseAdapter<Post,PostItemViewHolder> (parentLifecycle,posts) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
         return PostItemViewHolder(parent)
    }

}