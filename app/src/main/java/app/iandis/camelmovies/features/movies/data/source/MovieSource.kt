package app.iandis.camelmovies.features.movies.data.source

import app.iandis.camelmovies.features.movies.domain.entities.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSource {
    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int): List<Movie>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): List<Movie>

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("page") page: Int): List<Movie>
}