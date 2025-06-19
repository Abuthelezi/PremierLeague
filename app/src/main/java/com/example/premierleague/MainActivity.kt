package com.example.premierleague

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.premierleague.ui.theme.PremierLeagueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val teams = remember { mutableListOf("", "", "") }
            val points = remember { mutableListOf(0, 0, 0 ) }
            var tempPoints = 0
            var tempTeams = ""
            var count = 0
            var index = 0
            var inputTeam by remember { mutableStateOf("") }
            var inputPoints by remember { mutableStateOf("") }
            var showInfo by remember { mutableStateOf(false) }
            Column {
            while (count < teams.size) {


                TextField(value = inputPoints, onValueChange = { inputPoints = it })
                TextField(value = inputTeam, onValueChange = { inputTeam = it })
                Spacer(modifier = Modifier.height(150.dp))

                Button(onClick = {

                    teams[count] = inputTeam
                    points[count] = inputPoints.toIntOrNull() ?: 0

                    inputTeam = ""
                    inputPoints = ""
                }) { Text("Save") }
                count = count + 1
            }

                while (index < teams.size) {
                    count = 0
                    while (count < teams.size - index - 1) {
                        if (points[count] > points[count + 1]) {
                            tempPoints = points[count]
                            points[count] = points[count + 1]
                            points[count + 1] = tempPoints
                            tempTeams = teams[count]
                            teams[count] = teams[count + 1]
                            teams[count + 1] = tempTeams

                        }
                        count = count + 1
                    }
                    index = index + 1
                }

            }
            Column {
                Spacer(modifier = Modifier.height(150.dp))

                Button(onClick = {
                        showInfo = true

                    }) {
                        Text("Display results")
                    }
                    if (showInfo) {
                        Spacer(modifier = Modifier.height(50.dp))
                        Text("${teams.last()} are the new English premier league with ${points.last()} points and ${teams.first()} has been relegated with ${points.first()} points")
                    }

            }
        }
    }
}