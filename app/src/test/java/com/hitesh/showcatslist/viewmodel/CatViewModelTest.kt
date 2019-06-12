package com.hitesh.showcatslist.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.hitesh.showcatslist.RxImmediateSchedulerRule
import com.hitesh.showcatslist.api.PersonApi
import com.hitesh.showcatslist.catlist.CatRepo
import com.hitesh.showcatslist.catlist.CatViewModel
import com.hitesh.showcatslist.model.Person
import com.hitesh.showcatslist.model.Pet
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit
import java.util.ArrayList
import java.util.HashMap

class CatViewModelTest {

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var personApi: PersonApi

    @Mock
    lateinit var repo: CatRepo

    lateinit var subject: CatViewModel

    @Before
    fun setUp() {
        repo = CatRepo(personApi)
        subject = CatViewModel(repo)
    }

    @Test
    fun test_load_data_success() {
        `when`(personApi.getPerson())
            .thenReturn(Observable.fromArray(getDummyPersonList()))

        val obseverData = mock(Observer::class.java) as Observer<HashMap<String, ArrayList<Pet>>>
        this.subject.mCatList.observeForever(obseverData)

        val obseverState = mock(Observer::class.java) as Observer<Boolean>
        this.subject.mState.observeForever(obseverState)

        this.subject.loadList()

        assertEquals(subject.mCatList.value?.size, 2)
        assertEquals(this.subject.mState.value, true)
    }


    @Test
    fun test_load_data_fail() {
        `when`(personApi.getPerson())
            .thenReturn(Observable.error(Throwable("Something went wrong")))

        val obseverData = mock(Observer::class.java) as Observer<HashMap<String, ArrayList<Pet>>>
        this.subject.mCatList.observeForever(obseverData)

        val obseverState = mock(Observer::class.java) as Observer<Boolean>
        this.subject.mState.observeForever(obseverState)

        this.subject.loadList()

        assertNull(subject.mCatList.value)
        assertEquals(this.subject.mState.value, false)
    }

    private fun getDummyPersonList(): ArrayList<Person> {
        val dummyPersonList: ArrayList<Person> = ArrayList()

        val pets1: ArrayList<Pet> = ArrayList()
        pets1.add(Pet("Garfield", "Cat"))
        pets1.add(Pet("Fido", "Dog"))
        val persion1: Person = Person(25, "Male", "Hitesh", pets1)
        dummyPersonList.add(persion1)

        val pets2: ArrayList<Pet> = ArrayList()
        pets2.add(Pet("Sam", "Cat"))
        pets2.add(Pet("Max", "Cat"))
        val persion2: Person = Person(20, "Female", "Samantha", pets2)
        dummyPersonList.add(persion2)

        return dummyPersonList
    }
}