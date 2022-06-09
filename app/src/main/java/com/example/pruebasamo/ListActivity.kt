package com.example.pruebasamo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pruebasamo.helpers.Constants
import com.example.pruebasamo.viewmodels.ListViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ListActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val vm : ListViewModel = getViewModel()
            val movieList = vm.movieList.value

            val context = LocalContext.current

            fun openYouTubeVideo(key: String, context: Context) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://${key}"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }

            MaterialTheme {
                Column {
                    val checkedState = remember { mutableStateOf(false) }
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Most Popular")
                        Switch(checked = checkedState.value, onCheckedChange = {
                            checkedState.value = it
                            vm.getMovieListByCategory(checkedState.value)
                        })
                        Text(text = "Playing Now")
                    }
                    
                    LazyColumn{
                        items(movieList) { movie ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                shape = MaterialTheme.shapes.medium,
                                elevation = 8.dp
                            ) {
                                var expanded by remember { mutableStateOf(false) }
                                var hasVideo by remember { mutableStateOf( !movie.video_url.isNullOrEmpty() )}
                                
                                Column(Modifier.clickable { expanded = !expanded }) {
                                    AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data("${Constants.IMAGE_URL}${movie.backdrop_path}")
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = "Poster for ${movie.title.toString()}",
                                            contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = movie.title.toString(), modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp), textAlign = TextAlign.Center
                                    )
                                    AnimatedVisibility(visible = expanded) {
                                        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                            Text(text = "Idioma Original:", fontWeight = FontWeight.Bold)
                                            Text(text = " ${movie.original_language}")
                                            Text(text = "Titulo Original:", fontWeight = FontWeight.Bold)
                                            Text(text = "${movie.original_title}")
                                            Text(text = "Resumen:", fontWeight = FontWeight.Bold)
                                            Text(text = "${movie.overview}")
                                            Text(text = "Fecha de estreno:", fontWeight = FontWeight.Bold)
                                            Text(text = "${movie.release_date}")
                                            Text(text = "Calificacion:", fontWeight = FontWeight.Bold)
                                            Text(text = "${movie.vote_average} de 10")
                                            if(hasVideo) {
                                                Button(onClick = {
                                                    movie.video_url?.let {
                                                        openYouTubeVideo(
                                                            it,
                                                            context
                                                        )
                                                    }
                                                }, modifier = Modifier.fillMaxWidth()) {
                                                    Text(text = "Ver el Trailer")
                                                }
                                            }
                                            
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }





        }
    }

}

