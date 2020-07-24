package com.kodyuzz.kanas.data.repository

import com.kodyuzz.kanas.data.local.db.DatabaseService
import com.kodyuzz.kanas.data.local.pref.UserPreferences
import com.kodyuzz.kanas.data.model.User
import com.kodyuzz.kanas.data.remote.NetworkService
import com.kodyuzz.kanas.data.remote.request.LoginRequest
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun saveCurrentUser(user: User) {
        userPreferences.setUserId(user.id)
        userPreferences.setUserName(user.name)
        userPreferences.setUserEmail(user.email)
        userPreferences.setAccessToken(user.accessToken)
    }

    fun removeCurrentUser() {
        userPreferences.removeUserEmail()
        userPreferences.removeAccessToken()
        userPreferences.removeUserName()
        userPreferences.removeUserId()
    }

    fun getCurrentUser(): User? {
        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName()
        val userEmail = userPreferences.getUserEmail()
        val userAccessToken = userPreferences.getAccessToken()

        return if (userId != null && userName != null && userEmail != null && userAccessToken != null)
            User(userId, userName, userEmail, userAccessToken)
        else null
    }

    fun doUserLogin(email: String, password: String): Single<User> =
        networkService.doLoginCall(
            LoginRequest(email, password)
        ).map {
            User(
                it.userId,
                it.userName,
                it.userEmail,
                it.accessToken,
                it.profilePicUrl
            )
        }
}