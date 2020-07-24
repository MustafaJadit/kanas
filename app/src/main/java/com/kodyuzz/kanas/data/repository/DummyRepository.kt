package com.kodyuzz.kanas.data.repository

import com.kodyuzz.kanas.data.local.db.DatabaseService
import com.kodyuzz.kanas.data.model.Dummy
import com.kodyuzz.kanas.data.remote.NetworkService
import com.kodyuzz.kanas.data.remote.request.DummyRequest
import io.reactivex.Single
import javax.inject.Inject

class DummyRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchDummy(id: String): Single<List<Dummy>> =
        networkService.doDummyCall(DummyRequest(id)).map { it.data }

}