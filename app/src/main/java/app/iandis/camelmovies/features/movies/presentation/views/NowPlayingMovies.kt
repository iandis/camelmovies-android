package app.iandis.camelmovies.features.movies.presentation.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.iandis.camelmovies.features.movies.presentation.viewmodels.NowPlayingMovieListViewModel

@Composable
fun NowPlayingMovies(viewModel: NowPlayingMovieListViewModel) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state) {

    }
}