package com.kodyuzz.kanas.ui.home.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kodyuzz.kanas.R
import com.kodyuzz.kanas.data.model.Post
import com.kodyuzz.kanas.data.remote.Networking
import com.kodyuzz.kanas.data.repository.PostRepository
import com.kodyuzz.kanas.data.repository.UserRepository
import com.kodyuzz.kanas.ui.base.BaseItemViewModel
import com.kodyuzz.kanas.utils.common.Image
import com.kodyuzz.kanas.utils.common.Resource
import com.kodyuzz.kanas.utils.common.TImeUtils
import com.kodyuzz.kanas.utils.display.ScreenUtils
import com.kodyuzz.kanas.utils.log.Logger
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    userRepository: UserRepository,
    private val postRepository: PostRepository
) : BaseItemViewModel<Post>(schedulerProvider, disposable, networkHelper) {

    companion object{
        const val TAG="PostItemViewModel"
    }

    private val user=userRepository.getCurrentUser()!!
    private val screenWidth=ScreenUtils.getScreenWidth()
    private val screenHeight=ScreenUtils.getScreenHeight()
    private val headers= mapOf(
        Pair(Networking.HEADER_API_KEY,Networking.API_KEY),
        Pair(Networking.HEADER_USER_ID,user.id),
        Pair(Networking.HEADER_ACCESS_TOKEN,user.accessToken)
    )

    val name:LiveData<String> =Transformations.map(data){it.creator.name}
    val postTime:LiveData<String> = Transformations.map(data){TImeUtils.getTimeAgo(it.createdAt)}
    val likedCount:LiveData<Int> = Transformations.map(data){it.likedBy?.size?:0}
    val isLiked:LiveData<Boolean> = Transformations.map(data){
        it.likedBy?.find { postUser ->postUser.id==user.id }!==null
    }

     val profileImage: LiveData<Image> = Transformations.map(data){
         it.creator.profilePicUrl?.run {  Image(this,headers) }
     }

    val imageDetail: LiveData<Image> = Transformations.map(data){
        Image(
            it.imageUrl,
            headers,
            screenWidth,
            it.imageHeight?.let {
                height-> return@let  (calculateScaleFactor(it)* height).toInt()
            }?:screenWidth/3
        )
    }

    private fun calculateScaleFactor(post:Post)=
        post.imageWidth?.let { return@let screenWidth.toFloat()/it }?:1f

    override fun onCreate( ) {
      Logger.d(TAG,"oncreate called")
    }

    fun onclickClick()= data.value?.let {
        if (networkHelper.isNetworkConnected()){
            val api=
                if (isLiked.value==true)
                    postRepository.makeUnlikePost(it,user)
            else postRepository.makeLikePost(it,user)

            compositeDisposable.add(
                api.subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {responsePost -> if(responsePost.id==it.id) updateData(responsePost)},
                        { error -> handleNetworkError(error)}
                    )
            )
        }else{
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
        }
    }


}