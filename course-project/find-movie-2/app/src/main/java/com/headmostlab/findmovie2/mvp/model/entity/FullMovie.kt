package com.headmostlab.findmovie2.mvp.model.entity

data class FullMovie(
    val id: Int,
    val title: String,
    val origTitle: String,
    val genres: List<String>,
    val duration: Int,
    val rating: Double,
    val votesAverage: Double,
    val votesCount: Int,
    val budget: Int,
    val revenue: Long,
    val date: String,
    val description: String,
    val poster: String,
    var people: List<Person>? = null
)