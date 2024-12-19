package com.example.project_2377432.screens

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_2377432.GkmcSongActivity
import com.example.project_2377432.GkmcSongCard
import com.example.project_2377432.HorizontalImageCarousel
import com.example.project_2377432.SearchTextFields
import com.example.project_2377432.SongBasicData
import com.example.project_2377432.SongDetails
import com.example.project_2377432.VerticalImageCarousel
import com.example.project_2377432.data.GkmcSong
import com.example.project_2377432.data.getGkmcSongs

@Composable
fun GkmcSongCard(
    song: GkmcSong,
    expandable: Boolean = false,
    clickable: Boolean = true,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(expandable) }

    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                if (clickable) {
                    val intent = Intent(context, GkmcSongActivity::class.java)
                    intent.putExtra("song", song)
                    context.startActivity(intent)
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // la mise en page a besoin d'être améliorée
            if (expanded) {

                if (isLandscape) {
                    Row {
                        SongBasicData(song)
                        SongDetails(song)
                    }
                    HorizontalImageCarousel(photoResources = song.photoResources)
                } else {
                    SongBasicData(song)
                    SongDetails(song)
                    VerticalImageCarousel(photoResources = song.photoResources)
                }
            } else {
                SongBasicData(song)
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreen(
    nameSearch: String = "",
    onNameChange: (String) -> Unit = {},
    numberSearch: Int? = null,
    onNumberChange: (String) -> Unit = {}
) {
    val filteredSongs = getGkmcSongs(name = nameSearch, number = numberSearch)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Fields at the Top
        SearchTextFields(
            nameSearch = nameSearch,
            onNameChange = onNameChange,
            numberSearch = numberSearch,
            onNumberChange = onNumberChange
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Use LazyVerticalGrid for the catalog layout
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
            modifier = Modifier
                .fillMaxSize(), // Ensure grid takes up the remaining space
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredSongs) { song ->
                GkmcSongCard(song = song, expandable = false)
            }
        }
    }
}

