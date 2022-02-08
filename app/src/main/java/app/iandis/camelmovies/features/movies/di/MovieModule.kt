package app.iandis.camelmovies.features.movies.di

import app.iandis.camelmovies.core.env.EnvConfig
import app.iandis.camelmovies.core.network.AppHttpClient
import app.iandis.camelmovies.features.movies.data.source.MovieSource
import app.iandis.camelmovies.features.movies.domain.repositories.MovieRepository
import app.iandis.camelmovies.features.movies.domain.repositories.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {
    @Provides
    fun provideMovieSource(): MovieSource {
        return Retrofit.Builder()
            .baseUrl(EnvConfig.BASE_URL)
            .client(AppHttpClient())
            .build()
            .create(MovieSource::class.java)
    }

    @Provides
    fun provideMoveRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository =
        movieRepositoryImpl
}
