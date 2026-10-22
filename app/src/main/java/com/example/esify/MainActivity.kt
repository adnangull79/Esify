package com.example.esify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.esify.navigation.AppNavigation
import com.example.esify.ui.theme.EsifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EsifyTheme {
                AppNavigation()
            }
        }
    }
}

//git add -A
//git commit -m "UI changes"
//git push