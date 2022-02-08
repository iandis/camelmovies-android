package app.iandis.camelmovies.features.movies.domain.repositories

import app.iandis.camelmovies.features.movies.domain.entities.Movie
import javax.inject.Singleton

@Singleton
interface MovieRepository {
    suspend fun getPopular(page: Int = 1): List<Movie>
    suspend fun getNowPlaying(page: Int = 1): List<Movie>
    suspend fun getUpcoming(page: Int = 1): List<Movie>
}