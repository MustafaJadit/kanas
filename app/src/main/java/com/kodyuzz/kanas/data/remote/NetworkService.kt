package com.kodyuzz.kanas.data.remote

import com.kodyuzz.kanas.data.remote.request.DummyRequest
import com.kodyuzz.kanas.data.remote.request.LoginRequest
import com.kodyuzz.kanas.data.remote.request.PostCreationRequest
import com.kodyuzz.kanas.data.remote.request.PostLikeModifyRequest
import com.kodyuzz.kanas.data.remote.response.*
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @POST(Endpoints.DUMMY)
    fun doDummyCall(
        @Body request: DummyRequest,
        @Header(Networking.HEADER_API_KEY) apikey: String = Networking.API_KEY
    ): Single<DummyResponse>

    @POST(Endpoints.LOGIN)
    fun doLoginCall(
        @Body request: LoginRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<LoginResponse>

    @GET(Endpoints.HOME_POSTS_LIST)
    fun doHomePostListCall(
        @Query("firstPostId") firstPostId: String?,
        @Query("lastPostId") lastPostId: String?,
        @Header(Networking.HEADER_USER_ID) userId:String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken:String,
        @Header(Networking.HEADER_API_KEY) apiKey: String=Networking.API_KEY
    ): Single<PostListResponse>

    @PUT(Endpoints.POST_LIKE)
    fun doPostLikeCall(
        @Body request: PostLikeModifyRequest,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String=Networking.API_KEY
    ):Single<GeneralResponse>

    @PUT(Endpoints.POST_UNLIKE)
    fun doPOstUnlikeCall(
        @Body request: PostLikeModifyRequest,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String=Networking.API_KEY
    ):Single<GeneralResponse>

    @Multipart
    @POST(Endpoints.UPLOAD_IMAGE)
    fun doImageUPload(
        @Part image:MultipartBody.Part,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String=Networking.API_KEY

    ):Single<ImageResponse>

    @POST(Endpoints.CREATE_POST)
    fun doPostCreationCall(
        @Body request: PostCreationRequest,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String= Networking.API_KEY
    ): Single<PostCreationResponse>
}