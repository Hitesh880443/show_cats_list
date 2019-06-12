package com.hitesh.showcatslist

import com.hitesh.showcatslist.model.Person
import com.hitesh.showcatslist.model.Pet
import com.hitesh.showcatslist.utils.Util
import org.junit.Assert.*
import org.junit.Test
import java.util.ArrayList

class UtilTest {

    @Test
    fun test_filter_list() {
        val data = Util.filterList(getDummyPersonList())
        assertNotNull(data)
    }

    @Test
    fun test_filter_list_null() {
        val data = Util.filterList(ArrayList())
        assertEquals(data.size, 0)

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