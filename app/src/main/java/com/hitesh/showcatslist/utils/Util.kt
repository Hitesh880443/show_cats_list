package com.hitesh.showcatslist.utils

import com.hitesh.showcatslist.model.Person
import com.hitesh.showcatslist.model.Pet
import java.util.*

object Util {

    fun filterList(dataList: ArrayList<Person>): HashMap<String, ArrayList<Pet>> {
        var datamap: HashMap<String, ArrayList<Pet>> = HashMap()

        for (item in dataList) {
            val key = item.gender
            var cats: ArrayList<Pet>? = null

            item.pets?.let {

                // Filtering Pet list with type Cat
                cats = ArrayList(it.let { pets ->
                    pets.filter { pet ->
                        pet.type == "Cat"
                    }
                })

                // Sorting Cat list
                cats?.let { pet ->
                    Collections.sort(pet, AlphabeticComparator())
                }
            }

            if (datamap.containsKey(key)) {
                val oldList = datamap[key]
                cats?.let {
                    oldList?.addAll(it)
                    oldList?.let { pet ->
                        datamap[key] = pet
                    }
                }
            } else {
                cats?.let { pet ->
                    datamap[key] = pet
                }
            }
        }
        return datamap
    }


}