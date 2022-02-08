package app.iandis.camelmovies.features.movies.presentation.viewmodels

import app.iandis.camelmovies.core.base.ViewModelStatus
import app.iandis.camelmovies.features.movies.domain.entities.Movie

data class NowPlayingMovieListViewModelState(
    val status: ViewModelStatus,
    val page: Int,
    val movies: List<Movie>,
    val isAtEndOfPage: Boolean = false,
    val error: String? = null
) {
    val isLoading: Boolean get() = status == ViewModelStatus.Loading
    val isLoadingMore: Boolean get() = status == ViewModelStatus.LoadingMore
    val isBusy: Boolean get() = isLoading || isLoadingMore
    val hasError: Boolean get() = status == ViewModelStatus.Error

    companion object {
        val Init: NowPlayingMovieListViewModelState = NowPlayingMovieListViewModelState(
            status = ViewModelStatus.Init,
            page = 0,
            movies = listOf()
        )
    }
}