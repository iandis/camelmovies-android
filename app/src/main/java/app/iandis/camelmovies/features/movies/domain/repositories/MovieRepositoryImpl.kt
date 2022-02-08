package app.iandis.camelmovies.features.movies.domain.repositories

import app.iandis.camelmovies.features.movies.data.source.MovieSource
import app.iandis.camelmovies.features.movies.domain.entities.Movie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val _movieSource: MovieSource
) : MovieRepository {
    override suspend fun getPopular(page: Int): List<Movie> = _movieSource.getPopular(page)
    override suspend fun getNowPlaying(page: Int): List<Movie> = _movieSource.getNowPlaying(page)
    override suspend fun getUpcoming(page: Int): List<Movie> = _movieSource.getUpcoming(page)
}