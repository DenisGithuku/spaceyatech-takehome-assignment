package com.githukudenis.takehomeassignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.githukudenis.takehomeassignment.presentation.navigation.TakeHomeNavHost
import com.githukudenis.takehomeassignment.presentation.theme.TakeHomeAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TakeHomeAssignmentTheme {
                val navController = rememberNavController()
                TakeHomeNavHost(navController = navController)
            }
        }
    }
}


