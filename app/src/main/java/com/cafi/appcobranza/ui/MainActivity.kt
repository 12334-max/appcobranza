package com.cafi.appcobranza.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cafi.appcobranza.ui.layout.MainLayout
import com.cafi.appcobranza.ui.theme.AppCobranzaTheme
import com.cafi.appcobranza.ui.login.LoginUI
import com.jakewharton.threetenabp.AndroidThreeTen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        setContent {
            AppCobranzaTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login" ){
                        composable("login"){
                            LoginUI().LoginScreen(navController = navController)
                        }

                        composable("home"){
                            MainLayout().AppContent()
                        }
                    }
                }
            }
        }
    }
}


