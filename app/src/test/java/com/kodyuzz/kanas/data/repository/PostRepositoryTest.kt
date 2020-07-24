package com.kodyuzz.kanas.data.repository

import com.kodyuzz.kanas.Demo1
import com.kodyuzz.kanas.data.model.User
import com.kodyuzz.kanas.data.remote.NetworkService
import com.kodyuzz.kanas.data.remote.Networking
import com.kodyuzz.kanas.data.remote.response.PostListResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        Networking.API_KEY = "FAKE_API_KEY"
        postRepository = PostRepository(networkService)
    }

    @Test
    fun fetchHomePostList_requestDoHomePostListCall() {

        val user = User("userId", "userName", "userEmail", "accessToken", "profilePicUrl")

        Mockito.doReturn(Single.just(PostListResponse("statusCode", "message", listOf())))
            .`when`(networkService)
            .doHomePostListCall(
                "firstPostId",
                "lastPostId",
                user.id,
                user.accessToken,
                Networking.API_KEY
            )

        postRepository.fetchHomePostList("firstPostId", "lastPostId", user)

        Mockito.verify(networkService).doHomePostListCall(
            "firstPostId",
            "lastPostId",
            user.id,
            user.accessToken,
            Networking.API_KEY
        )

    }

}