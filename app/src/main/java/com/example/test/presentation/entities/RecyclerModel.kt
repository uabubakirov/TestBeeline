package com.example.test.presentation.entities


import com.yandex.mapkit.GeoObject
import com.yandex.mapkit.geometry.Point

data class RecyclerModel(
    val name: String?,
    val point: Point?
)

fun GeoObject.mapToUI(name: String?, point: Point?) = RecyclerModel(name,point)