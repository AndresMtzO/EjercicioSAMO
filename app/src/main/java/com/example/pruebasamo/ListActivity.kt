package com.example.pruebasamo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pruebasamo.viewmodels.ListViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ListActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val vm : ListViewModel = getViewModel()
            val movieList = vm.movieList.value

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
                                Column(Modifier.clickable { expanded = !expanded }) {
                                    Text(
                                        text = movie.title.toString(), modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp), textAlign = TextAlign.Center
                                    )
                                    AnimatedVisibility(visible = expanded) {
                                        Column(Modifier.padding(10.dp)) {
                                            Text(text = "I'm expanded!!!")
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

