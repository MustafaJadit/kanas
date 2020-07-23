package com.kodyuzz.kanas.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodyuzz.kanas.data.repository.PhotoRepository
import com.kodyuzz.kanas.data.repository.PostRepository
import com.kodyuzz.kanas.data.repository.UserRepository
import com.kodyuzz.kanas.ui.base.BaseFragment
import com.kodyuzz.kanas.ui.home.HomeViewModel
import com.kodyuzz.kanas.ui.home.posts.PostAdapter
import com.kodyuzz.kanas.ui.main.MainSharedViewModel
import com.kodyuzz.kanas.ui.photo.PhotoViewModel
import com.kodyuzz.kanas.ui.profile.ProfileVIewModel
import com.kodyuzz.kanas.utils.ViewModelProviderFactory
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import com.mindorks.paracamera.Camera
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import java.io.File

@Module
class FragmentModule(val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun providePostsAdapter() = PostAdapter(fragment.lifecycle, ArrayList())


    @Provides
    fun provideCamera() = Camera.Builder()
        .resetToCorrectOrientation(true)
        .setTakePhotoRequestCode(1)
        .setDirectory("temp")
        .setName("camera_temp_img")
        .setImageFormat(Camera.IMAGE_JPEG)
        .setCompression(75)
        .setImageHeight(500)
        .build(fragment)

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        postRepository: PostRepository
    ): HomeViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(
            HomeViewModel::class
        ) {
            HomeViewModel(
                schedulerProvider, compositeDisposable, networkHelper, userRepository,
                postRepository, ArrayList(), PublishProcessor.create()
            )
        }
    ).get(HomeViewModel::class.java)

    @Provides
    fun providePhotoViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        photoRepository: PhotoRepository,
        postRepository: PostRepository,
        directory: File
    ): PhotoViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(
            PhotoViewModel::class
        ) {
            PhotoViewModel(
                schedulerProvider, compositeDisposable, userRepository, photoRepository,
                postRepository, networkHelper, directory
            )
        }
    ).get(PhotoViewModel::class.java)


    @Provides
    fun provideProfileViewModel(
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): ProfileVIewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(
            ProfileVIewModel::class
        ) {
            ProfileVIewModel(schedulerProvider, disposable, networkHelper)
        }
    ).get(ProfileVIewModel::class.java)

    @Provides
    fun provideMainSharedViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainSharedViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }
    ).get(MainSharedViewModel::class.java)
}