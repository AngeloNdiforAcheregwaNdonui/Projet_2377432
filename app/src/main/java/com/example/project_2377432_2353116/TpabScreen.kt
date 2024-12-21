package com.example.project_2377432_2353116.screens

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import angelo.acheregwa.project_2377432.R
import com.example.project_2377432_2353116.HorizontalImageCarousel
import com.example.project_2377432_2353116.SearchTextFields
import com.example.project_2377432_2353116.SongDataRow
import com.example.project_2377432_2353116.TpabSongActivity
import com.example.project_2377432_2353116.VerticalImageCarousel
import com.example.project_2377432_2353116.data.TpabSong
import com.example.project_2377432_2353116.data.getTpabSongs

/**
 * Composable affichant les données de base d'une chanson.
 */
@Composable
fun SongBasicData(song: TpabSong) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        // First Photo of the Song (Album Art)
        song.photoResources.firstOrNull()?.let { photoResource ->
            Image(
                painter = painterResource(id = photoResource),
                contentDescription = stringResource(R.string.first_photo, song.name),
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // Song Information
        Column {
            Row {
                // Classic Icon if song is considered a "Classic"
                if (song.isClassic()) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = stringResource(R.string.is_classic),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
                Text(
                    text = song.name,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            SongDataRow(R.string.position, song.position.toString())
            SongDataRow(R.string.album, song.album)
            SongDataRow(R.string.length, song.length)
            SongDataRow(R.string.producers, song.producers)
        }
    }
}

/**
 * Composable affichant les détails supplémentaires d'une chanson.
 */
@Composable
fun SongDetails(song: TpabSong) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // First Column: General Song Details
        Column {
            SongDataRow(R.string.songwriters, song.songwriters)
            SongDataRow(R.string.producers, song.producers)
            SongDataRow(R.string.grammy_nominations, song.grammy_nominations.toString())
            SongDataRow(R.string.grammy_wins, song.grammy_wins.toString())
        }

        Spacer(modifier = Modifier.weight(1f))

        // Second Column: Derived Details
        Column {
            SongDataRow(R.string.total_grammys, song.grammy.toString())
            SongDataRow(R.string.is_classic, if (song.isClassic()) "Yes" else "No")
            SongDataRow(R.string.length, song.length)
            SongDataRow(R.string.album, song.album)
        }
    }
}

/**
 * Composable représentant une carte de chanson avec des informations de base et des détails extensibles.
 *
 * @param song Instance de [TpabSong] contenant les informations de la chanson.
 * @param expandable Indique si la carte est extensible pour afficher plus de détails.
 * @param clickable Indique si la carte est cliquable pour naviguer vers les détails de la chanson.
 * @param modifier Modificateur pour personnaliser l'apparence de la carte.
 */
@Composable
fun TpabSongCard(
    song: TpabSong,
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
                    val intent = Intent(context, TpabSongActivity::class.java)
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

/**
 * Composable représentant l'écran principal des chansons TPAB avec fonctionnalités de recherche et liste des chansons.
 *
 * @param nameSearch Texte de recherche par nom de chanson.
 * @param onNameChange Callback appelé lorsque le texte de recherche par nom change.
 * @param numberSearch Numéro de recherche (par exemple, position dans le classement).
 * @param onNumberChange Callback appelé lorsque le numéro de recherche change.
 */
@Composable
fun TpabScreen(
    nameSearch: String = "",
    onNameChange: (String) -> Unit = {},
    numberSearch: Int? = null,
    onNumberChange: (String) -> Unit = {}
) {
    // Filtre les chansons en fonction des critères de recherche
    val filteredSongs = getTpabSongs(name = nameSearch, number = numberSearch)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Champs de recherche pour le nom et le numéro
        SearchTextFields(
            nameSearch = nameSearch,
            onNameChange = onNameChange,
            numberSearch = numberSearch,
            onNumberChange = onNumberChange
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Grille verticale affichant les cartes des chansons filtrées
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredSongs) { song ->
                TpabSongCard(song = song, expandable = false)
            }
        }
    }
}
