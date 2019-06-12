package com.hitesh.showcatslist.catlist

import com.hitesh.showcatslist.api.PersonApi
import com.hitesh.showcatslist.model.Person
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CatRepo @Inject constructor(private val personAPI: PersonApi) {

    fun getDataFromApi(): Observable<ArrayList<Person>> {
        return personAPI.getPerson()
    }
}