package com.kodyuzz.kanas.ui.home.posts

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodyuzz.kanas.R
import com.kodyuzz.kanas.data.model.Post
import com.kodyuzz.kanas.di.component.ViewHolderComponent
import com.kodyuzz.kanas.ui.base.BaseItemViewHolder
import com.kodyuzz.kanas.utils.common.GlideHelper
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostItemViewHolder(parent:ViewGroup):
BaseItemViewHolder<Post,PostItemViewModel>(R.layout.item_view_post,parent){


    override fun setupVIew(itemView: View) {
        itemView.ivLike.setOnClickListener{viewModel.onclickClick()}
    }

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.name.observe(this, Observer {
            itemView.tvName.text=it
        })

        viewModel.postTime.observe(this, Observer {
            itemView.tvTime.text=it
        })

        viewModel.isLiked.observe(this, Observer {
            if (it)itemView.ivLike.setImageResource(R.drawable.ic_heart_selected)
            else itemView.ivLike.setImageResource(R.drawable.ic_heart_unselected)
        })

        viewModel.profileImage.observe(this, Observer {
            it?.run {
                val glideRequest= Glide
                    .with(itemView.ivProfile.context)
                    .load(GlideHelper.getProtectedUrl(url,headers))
                    .apply(RequestOptions.circleCropTransform())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_selected))

                if (placeHolderWidth >0 && placeHolderHeight>0){
                    val params= itemView.ivProfile.layoutParams as ViewGroup.LayoutParams
                    params.width= placeHolderWidth
                    params.height= placeHolderHeight
                    itemView.ivProfile.layoutParams=params
                    glideRequest.apply(RequestOptions.overrideOf(placeHolderWidth,placeHolderHeight))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_unselected))

                }
                glideRequest.into(itemView.ivProfile)
            }
        })

        viewModel.imageDetail.observe(this, Observer {
            it?.run {
                val glideRequest=Glide
                    .with(itemView.ivPost.context)
                    .load(GlideHelper.getProtectedUrl(url,headers))

                if (placeHolderWidth >0 && placeHolderHeight >0){
                    val params= itemView.ivPost.layoutParams as ViewGroup.LayoutParams
                    params.width= placeHolderWidth
                    params.height=placeHolderHeight
                    itemView.ivPost.layoutParams=params
                    glideRequest.apply(RequestOptions.overrideOf(placeHolderWidth,placeHolderHeight))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_photo))
                }
                glideRequest.into(itemView.ivPost)
            }
        })
    }

}