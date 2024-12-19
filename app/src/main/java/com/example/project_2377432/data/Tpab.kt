package com.example.project_2377432.data

import android.content.Context
import android.os.Parcelable
import android.util.Log
import angelo.acheregwa.project_2377432.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class TpabSong(
    val position: Int,
    val name: String,
    val album: String,
    val length: String,
    val songwriters: String,
    val producers: String,
    val grammy_nominations: Int,
    val grammy_wins: Int,
    val photoResources: List<Int> = listOf(R.drawable.first_tpab_song_photo) // List of resource IDs for photos
) : Parcelable {
    val grammy: Int
        get() = grammy_nominations + grammy_wins

    fun isClassic(): Boolean = grammy > 3
}


fun getTpabSongs(name: String? = null, number: Int? = null): List<TpabSong> =
    getTpabSampleSongs()
        .filter { song ->
            (name == null || song.name.lowercase().contains(name.lowercase())) &&
                    (number == null || song.position == number)
        }

fun filterSongs(
    source: List<TpabSong>,
    name: String? = null,
    number: Int? = null
): List<TpabSong> =
    source
        .filter { song ->
            (name == null || song.name.lowercase().contains(name.lowercase())) &&
                    (number == null || song.position == number)
        }


fun getTpabSampleSongs(): List<TpabSong> {
    return listOf(
        TpabSong(
            position = 1,
            name = "Wesley's Theory",
            album = "To Pimp a Butterfly",
            length = "4:47",
            songwriters = "Kendrick Lamar, Boris Gardiner, Stephen Bruner, others",
            producers = "Flying Lotus, Thundercat",
            grammy_nominations = 1,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.first_tpab_song_photo
            )
        ),
        TpabSong(
            position = 2,
            name = "For Free? (Interlude)",
            album = "To Pimp a Butterfly",
            length = "2:10",
            songwriters = "Kendrick Lamar, Terrace Martin",
            producers = "Terrace Martin",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.second_tpab_song_photo
            )
        ),
        TpabSong(
            position = 3,
            name = "King Kunta",
            album = "To Pimp a Butterfly",
            length = "3:54",
            songwriters = "Kendrick Lamar, Stephen Bruner, others",
            producers = "Soundwave, Terrace Martin",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.third_tpab_song_photo
            )
        ),
        TpabSong(
            position = 4,
            name = "Institutionalized",
            album = "To Pimp a Butterfly",
            length = "4:31",
            songwriters = "Kendrick Lamar, Bilal, Snoop Dogg, others",
            producers = "Soundwave, Terrace Martin",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.fourth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 5,
            name = "These Walls",
            album = "To Pimp a Butterfly",
            length = "5:00",
            songwriters = "Kendrick Lamar, Terrace Martin, others",
            producers = "Larrance Dopson, Terrace Martin",
            grammy_nominations = 1,
            grammy_wins = 1,
            photoResources = listOf(
                R.drawable.fifth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 6,
            name = "u",
            album = "To Pimp a Butterfly",
            length = "4:28",
            songwriters = "Kendrick Lamar, Taz Arnold",
            producers = "Taz Arnold, Soundwave",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.sixth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 7,
            name = "Alright",
            album = "To Pimp a Butterfly",
            length = "3:39",
            songwriters = "Kendrick Lamar, Pharrell Williams",
            producers = "Pharrell Williams",
            grammy_nominations = 4,
            grammy_wins = 2,
            photoResources = listOf(
                R.drawable.seventh_tpab_song_photo
            )
        ),
        TpabSong(
            position = 8,
            name = "For Sale? (Interlude)",
            album = "To Pimp a Butterfly",
            length = "4:51",
            songwriters = "Kendrick Lamar, Terrace Martin",
            producers = "Terrace Martin",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.seventh_tpab_song_photo
            )
        ),
        TpabSong(
            position = 9,
            name = "Momma",
            album = "To Pimp a Butterfly",
            length = "4:43",
            songwriters = "Kendrick Lamar, Knxwledge",
            producers = "Knxwledge",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.eighth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 10,
            name = "Hood Politics",
            album = "To Pimp a Butterfly",
            length = "4:52",
            songwriters = "Kendrick Lamar, Tae Beast",
            producers = "Tae Beast",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.nineth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 11,
            name = "How Much a Dollar Cost",
            album = "To Pimp a Butterfly",
            length = "4:21",
            songwriters = "Kendrick Lamar, Ronald Isley, James Fauntleroy",
            producers = "LoveDragon",
            grammy_nominations = 1,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.tenth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 12,
            name = "Complexion (A Zulu Love)",
            album = "To Pimp a Butterfly",
            length = "4:23",
            songwriters = "Kendrick Lamar, Rapsody, Thundercat",
            producers = "Thundercat",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.eleventh_tpab_song_photo
            )
        ),
        TpabSong(
            position = 13,
            name = "The Blacker the Berry",
            album = "To Pimp a Butterfly",
            length = "5:28",
            songwriters = "Kendrick Lamar, Soundwave, others",
            producers = "Soundwave",
            grammy_nominations = 1,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.twelveth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 14,
            name = "You Ain't Gotta Lie (Momma Said)",
            album = "To Pimp a Butterfly",
            length = "4:01",
            songwriters = "Kendrick Lamar, Thundercat",
            producers = "Thundercat",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.thirteenth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 15,
            name = "i",
            album = "To Pimp a Butterfly",
            length = "5:36",
            songwriters = "Kendrick Lamar, Rahki",
            producers = "Rahki",
            grammy_nominations = 2,
            grammy_wins = 1,
            photoResources = listOf(
                R.drawable.fourteenth_tpab_song_photo
            )
        ),
        TpabSong(
            position = 16,
            name = "Mortal Man",
            album = "To Pimp a Butterfly",
            length = "12:07",
            songwriters = "Kendrick Lamar, Thundercat, Terrace Martin",
            producers = "Terrace Martin",
            grammy_nominations = 0,
            grammy_wins = 0,
            photoResources = listOf(
                R.drawable.fifteenth_tpab_song_photo
            )
        )
    )
}


fun Context.saveToFile(players: List<TpabSong>, filename: String) {
    try {
        val file = File(this.filesDir, filename)
        file.writeText(Gson().toJson(players))
    } catch (e: Exception) {
        Log.e("FileIO", "Erreur lors de l'écriture du fichier", e)
    }
}

fun Context.readTpabSongsFromFile(filename: String): List<TpabSong> {
    val file = File(this.filesDir, filename)
    return if (file.exists()) {
        try {
            val jsonString = file.readText()
            Log.i("jsonString", jsonString)

            val playerListType = object : TypeToken<List<TpabSong>>() {}.type
            Gson().fromJson(jsonString, playerListType)
        } catch (e: Exception) {
            Log.e("FileIO", "Erreur lors de la lecture du fichier", e)
            emptyList()
        }
    } else {
        emptyList()
    }
}