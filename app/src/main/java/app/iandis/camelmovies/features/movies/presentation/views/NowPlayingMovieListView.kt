package app.iandis.camelmovies.features.movies.presentation.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.iandis.camelmovies.features.movies.domain.entities.Movie
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NowPlayingMovieListView(movies: List<Movie>) {
    LazyColumn {
        items(movies.size) { index ->
            val movie: Movie = movies[index]
            Card(
                shape = RoundedCornerShape(10.dp)
            ) {
                Row {
                    GlideImage(imageModel = movie.imageThumb)
                    Text(text = movie.title)
                }
            }
        }
    }
}

@Preview
@Composable
fun NowPlayingMovieListViewPreview() {
    val movies: List<Movie> = listOf(
        Movie(id = 1, title = "Test", rating = 300.0, ratingCount = 1000)
    )
    NowPlayingMovieListView(movies = movies)
}