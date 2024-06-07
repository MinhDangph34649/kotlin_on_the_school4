package com.example.lab7noplai


data class Movie(val title: String, val description: String) {
    companion object {
        fun getSampleMovies(): List<Movie> {
            return listOf(
                Movie("Movie 1", "Description 1"),
                Movie("Movie 2", "Description 2"),
                Movie("Movie 3", "Description 3")
            )
        }
    }
}