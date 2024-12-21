package com.example.project_2377432_2353116

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
<<<<<<< HEAD:app/src/main/java/com/example/project_2377432_2353116/TpabSongActivity.kt
import angelo.acheregwa.project_2377432.R
import com.example.project_2377432_2353116.data.TpabSong
import com.example.project_2377432_2353116.screens.TpabSongCard
=======
import com.example.project_2377432.R
import com.example.project_2377432.data.TpabSong
import com.example.project_2377432.screens.TpabSongCard
>>>>>>> d7e4beb350233299999e50c21af4c518a565ca54:app/src/main/java/com/example/project_2377432/TpabSongActivity.kt


/**
 * Activité affichant les détails d'une chanson TPAB.
 *
 * Cette activité reçoit un objet [TpabSong] via les extras de l'intention et affiche ses détails
 * en utilisant des composables Jetpack Compose.
 */
class TpabSongActivity : ComponentActivity() {

    /**
     * Méthode appelée lors de la création de l'activité.
     *
     * @param savedInstanceState État précédemment enregistré de l'activité, ou null.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Récupère  l'objet TpabSong passé via l'intention
        val song = intent.getParcelableExtra("song", TpabSong::class.java)

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
<<<<<<< HEAD:app/src/main/java/com/example/project_2377432_2353116/TpabSongActivity.kt
                        title = {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // Bouton pour terminer l'activité et revenir à l'écran précédent
                                Button(
                                    onClick = { finish() },
                                    modifier = Modifier.align(Alignment.BottomStart)
                                ) {
                                    Icon(Icons.Default.Done, "done")
                                }
                                // Titre centré de la barre d'application
                                Text(
                                    text = stringResource(R.string.app_name),
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.align(Alignment.Center)
                                )
=======
                        title = { Text(text = song?.name ?: "Song Details") },
                        navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
>>>>>>> d7e4beb350233299999e50c21af4c518a565ca54:app/src/main/java/com/example/project_2377432/TpabSongActivity.kt
                            }
                        }
                    )
                }
<<<<<<< HEAD:app/src/main/java/com/example/project_2377432_2353116/TpabSongActivity.kt
            ) { innerPadding ->
                // Contenu principal de l'activité avec padding fourni par Scaffold
=======
            ) { paddingValues ->
>>>>>>> d7e4beb350233299999e50c21af4c518a565ca54:app/src/main/java/com/example/project_2377432/TpabSongActivity.kt
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
<<<<<<< HEAD:app/src/main/java/com/example/project_2377432_2353116/TpabSongActivity.kt
                    if (song != null)
                    // Affiche la carte de la chanson avec des détails extensibles
                        TpabSongCard(
                            song = song,
                            expandable = true,
                            clickable = false
                        )
                    else {
                        // Affiche un message d'erreur si la chanson n'est pas trouvée
=======
                    if (song != null) {
                        TpabSongCard(song = song, expandable = true, clickable = false)
                    } else {
>>>>>>> d7e4beb350233299999e50c21af4c518a565ca54:app/src/main/java/com/example/project_2377432/TpabSongActivity.kt
                        Text(text = stringResource(R.string.player_not_found))
                    }
                }
            }
        }
    }
}

