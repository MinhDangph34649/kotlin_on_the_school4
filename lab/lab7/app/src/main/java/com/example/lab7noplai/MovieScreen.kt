package com.example.lab7noplai



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieScreen(movies: List<Movie>) {
    Column(modifier = Modifier.fillMaxSize()) {
        movies.forEach { movie ->
            Text(text = movie.title)
            Text(text = movie.description)
        }
    }
}
