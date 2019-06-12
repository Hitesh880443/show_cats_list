package com.hitesh.showcatslist.api

import com.hitesh.showcatslist.model.Person
import io.reactivex.Observable
import retrofit2.http.GET

interface PersonApi {

    /**
     * Get the list of the people from the API
     */
    @GET("/people.json")
    fun getPerson(): Observable<ArrayList<Person>>

}