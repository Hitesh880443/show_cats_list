package com.hitesh.showcatslist.utils

import com.hitesh.showcatslist.model.Pet

class AlphabeticComparator : Comparator<Pet> {
    override fun compare(p1: Pet, p2: Pet): Int {
        return p1.name.compareTo(p2.name)
    }
}