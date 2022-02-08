package app.iandis.camelmovies.features.movies.domain.entities

import android.os.Build
import app.iandis.camelmovies.core.env.EnvConfig
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("vote_count")
    val ratingCount: Int,
    @SerializedName("poster_path")
    val image: String? = null,
) {
    val year: String
        get() {
            if (releaseDate == null) {
                return "--"
            }
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.parse(releaseDate, DateTimeFormatter.ISO_DATE).year.toString()
            } else {
                SimpleDateFormat.getDateInstance().parse(releaseDate)!!.year.toString()
            }
        }

    val imageThumb: String?
        get() {
            if (image != null) {
                return EnvConfig.BASE_IMAGE_URL + EnvConfig.BASE_IMAGE_THUMB + image
            }
            return null
        }
}
