package org.cmedranv.cmpbooks.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import org.cmedranv.cmpbooks.di.initKoin
import org.cmedranv.cmpbooks.presentation.detail.BookDetailScreen
import org.cmedranv.cmpbooks.presentation.home.HomeScreen
import org.cmedranv.cmpbooks.presentation.login.LoginScreen
import org.cmedranv.cmpbooks.presentation.navigation.Routes
import org.koin.core.context.KoinContext

// Inicializa Koin una vez para toda la aplicación Multiplatform
/*actual fun getApp(): @Composable () -> Unit {
    initKoin()
    return {
        KoinContext { // Envuelve toda la app con KoinContext
            App()
        }
    }
}*/

@Composable
fun App() {
    initKoin()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = LoginScreen) {
                composable<LoginScreen> {
                    LoginScreen(
                        onLoginSuccess = {
                            navController.navigate(HomeScreen)
                        }
                    )
                }
                composable<HomeScreen> {
                    HomeScreen(
                        onBookClick = { bookId ->
                            navController.navigate(BookDetailScreen(bookId))
                        }
                    )
                }
                composable<BookDetailScreen> { backStackEntry ->
                    val detail = backStackEntry.toRoute<BookDetailScreen>()

                    BookDetailScreen(
                        bookId = detail.bookId,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}