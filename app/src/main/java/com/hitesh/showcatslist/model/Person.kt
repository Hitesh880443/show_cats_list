package com.hitesh.showcatslist.model

data class Person(
    val age: Int,
    val gender: String,
    val name: String,
    val pets: ArrayList<Pet>?
)
