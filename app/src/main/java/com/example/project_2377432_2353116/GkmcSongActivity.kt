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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import angelo.acheregwa.project_2377432.R
import com.example.project_2377432_2353116.data.GkmcSong
import com.example.project_2377432_2353116.screens.GkmcSongCard

/**
 * Activité affichant les détails d'une chanson GKMC.
 *
 * Cette activité reçoit un objet [GkmcSong] via les extras de l'intention et affiche ses détails
 * en utilisant des composables Jetpack Compose.
 */
class GkmcSongActivity : ComponentActivity() {
    /**
     * Méthode appelée lors de la création de l'activité.
     *
     * @param savedInstanceState État précédemment enregistré de l'activité, ou null.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val song = intent.getParcelableExtra("song", GkmcSong::class.java)

        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            // Utilisation d'un Box pour superposer le bouton et le titre
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
                            }
                        }
                    )

                }
            ) { innerPadding ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    if (song != null)
                    // Affiche la carte de la chanson avec des détails extensibles
                        GkmcSongCard(
                            song = song,
                            expandable = true,
                            clickable = false
                        )
                    else {
                        Text(text = stringResource(R.string.player_not_found))
                    }
                }
            }
        }
    }
}
