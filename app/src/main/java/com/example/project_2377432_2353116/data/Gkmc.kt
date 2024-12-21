package com.example.project_2377432_2353116.data

import android.content.Context
import android.os.Parcelable
import android.util.Log
import angelo.acheregwa.project_2377432.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class GkmcSong(
    val position: Int,
    val name: String,
    val album: String,
    val length: String,
    val songwriters: String,
    val producers: String,
    val grammy_nominations: Int,
    val grammy_wins: Int,
    val photoResources: List<Int> = listOf(R.drawable.first_gkmc_song_photo) // List of resource IDs for photos
) : Parcelable {
    val grammy: Int
        get() = grammy_nominations + grammy_wins

    fun isClassic(): Boolean = grammy > 3
}


fun getGkmcSongs(name: String? = null, number: Int? = null): List<GkmcSong> =
    getGkmcSampleSongs()
        .filter { song ->
            (name == null || song.name.lowercase().contains(name.lowercase())) &&
                    (number == null || song.position == number)
        }

fun filterSongs(
    source: List<GkmcSong>,
    name: String? = null,
    number: Int? = null
): List<GkmcSong> =
    source
        .filter { song ->
            (name == null || song.name.lowercase().contains(name.lowercase())) &&
                    (number == null || song.position == number)
        }


fun getGkmcSampleSongs(): List<GkmcSong> {
    return listOf(
        GkmcSong(
            position = 1,
            name = "Sherane a.k.a Master Splinter's Daughter",
            album = "good kid, m.A.A.d city",
            length = "4:33",
            songwriters = "C.Whitacre, J.Henderson, Kendrick Lamar",
            producers = "Tha Bizness",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.first_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 3,
            name = "Backseat Freestyle",
            album = "good kid, m.A.A.d city",
            length = "3:32",
            songwriters = "Kendrick Lamar, Chauncey Hollis",
            producers = "Hit-Boy",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.third_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 4,
            name = "The Art of Peer Pressure",
            album = "good kid, m.A.A.d city",
            length = "5:24",
            songwriters = "Kendrick Lamar, Tabu, DJ Dahi",
            producers = "Tabu, DJ Dahi",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.fourth_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 5,
            name = "Money Trees",
            album = "good kid, m.A.A.d city",
            length = "6:26",
            songwriters = "Kendrick Lamar, Johnny McKenzie",
            producers = "DJ Dahi",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.fifth_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 6,
            name = "Poetic Justice",
            album = "good kid, m.A.A.d city",
            length = "5:00",
            songwriters = "Kendrick Lamar, Aubrey Graham, Janet Jackson",
            producers = "Scoop DeVille",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.sixth_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 7,
            name = "Good Kid",
            album = "good kid, m.A.A.d city",
            length = "3:34",
            songwriters = "Kendrick Lamar, Pharrell Williams",
            producers = "Pharrell Williams",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.seventh_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 8,
            name = "m.A.A.d city",
            album = "good kid, m.A.A.d city",
            length = "5:50",
            songwriters = "Kendrick Lamar, MC Eiht, Sounwave, Terrace Martin",
            producers = "Sounwave, Terrace Martin",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.eighth_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 9,
            name = "Swimming Pools (Drank)",
            album = "good kid, m.A.A.d city",
            length = "5:13",
            songwriters = "Kendrick Lamar, T-Minus",
            producers = "T-Minus",
            grammy_nominations = 1,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.ninth_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 10,
            name = "Sing About Me, I'm Dying of Thirst",
            album = "good kid, m.A.A.d city",
            length = "12:03",
            songwriters = "Kendrick Lamar, Skhye Hutch, Like",
            producers = "Skhye Hutch, Like",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.tenth_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 11,
            name = "Real",
            album = "good kid, m.A.A.d city",
            length = "7:23",
            songwriters = "Kendrick Lamar, Soundwave, Anna Wise",
            producers = "Soundwave, Terrace Martin",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.eleventh_gkmc_song_photo
            )
        ),
        GkmcSong(
            position = 12,
            name = "Compton",
            album = "good kid, m.A.A.d city",
            length = "4:08",
            songwriters = "Kendrick Lamar, Dr. Dre, Justus, Mike WiLL Made-It",
            producers = "Just Blaze",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.twelfth_gkmc_song_photo
            )
        )
    )
}

fun Context.saveToFile(players: List<GkmcSong>, filename: String) {
    try {
        val file = File(this.filesDir, filename)
        file.writeText(Gson().toJson(players))
    } catch (e: Exception) {
        Log.e("FileIO", "Erreur lors de l'écriture du fichier", e)
    }
}

fun Context.readGkmcSongsFromFile(filename: String): List<GkmcSong> {
    val file = File(this.filesDir, filename)
    return if (file.exists()) {
        try {
            val jsonString = file.readText()
            Log.i("jsonString", jsonString)

            val playerListType = object : TypeToken<List<GkmcSong>>() {}.type
            Gson().fromJson(jsonString, playerListType)
        } catch (e: Exception) {
            Log.e("FileIO", "Erreur lors de la lecture du fichier", e)
            emptyList()
        }
    } else {
        emptyList()
    }
}
