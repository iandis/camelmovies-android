package app.iandis.camelmovies.features.movies.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import app.iandis.camelmovies.core.base.ViewModelBase
import app.iandis.camelmovies.core.base.ViewModelStatus
import app.iandis.camelmovies.features.movies.domain.entities.Movie
import app.iandis.camelmovies.features.movies.domain.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMovieListViewModel @Inject constructor(
    private val _movieRepository: MovieRepository,
) : ViewModelBase<NowPlayingMovieListViewModelState>(NowPlayingMovieListViewModelState.Init) {

    companion object {
        private const val _TAG: String = "MovieListViewModel"
    }

    fun fetch(more: Boolean = false) = viewModelScope.launch {
        val state: NowPlayingMovieListViewModelState = state.value
        if (state.isBusy || (state.isAtEndOfPage && more)) {
            return@launch
        }

        val nextStatus: ViewModelStatus =
            if (more) ViewModelStatus.LoadingMore else ViewModelStatus.Loading

        val nextPage: Int = if (more) state.page + 1 else state.page

        emit(
            state.copy(
                status = nextStatus,
                isAtEndOfPage = false,
                error = null
            )
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movies: List<Movie> = _movieRepository.getNowPlaying(page = nextPage)
                val nextMovies: List<Movie> = if (more) state.movies + movies else movies
                val isAtEndOfPage: Boolean = movies.isEmpty()

                viewModelScope.launch {
                    emit(
                        state.copy(
                            status = ViewModelStatus.Loaded,
                            page = nextPage,
                            movies = nextMovies,
                            isAtEndOfPage = isAtEndOfPage
                        )
                    )
                }
            } catch (err: Exception) {
                Log.e(_TAG, "Exception thrown", err)
                viewModelScope.launch {
                    emit(
                        state.copy(
                            status = ViewModelStatus.Error,
                            error = err.message ?: err.toString(),
                        )
                    )
                }
            }
        }
    }

}