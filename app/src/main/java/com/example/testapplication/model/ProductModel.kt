package com.example.testapplication.model

import java.io.Serializable

data class ProductListModel(
    val category: String?="",
    val description: String?="",
    val id: Int,
    val image: String?="",
    val price: Double?=0.0,
    val rating: RatingModel? = RatingModel(),
    val title: String?=""
) :Serializable
data class RatingModel(
    val count: Int?=0,
    val rate: Double?=0.0
):Serializable