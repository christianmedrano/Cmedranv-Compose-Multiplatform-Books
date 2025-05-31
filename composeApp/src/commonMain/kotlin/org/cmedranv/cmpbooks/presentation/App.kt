package org.cmedranv.cmpbooks.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview

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

            NavHost(navController = navController, startDestination = Routes.LOGIN) {
                composable(Routes.LOGIN) {
                    LoginScreen(
                        onLoginSuccess = {
                            navController.navigate(Routes.HOME) {
                                popUpTo(Routes.LOGIN) { inclusive = true }
                            }
                        }
                    )
                }
                composable(Routes.HOME) {
                    HomeScreen(
                        onBookClick = { bookId ->
                            navController.navigate("${Routes.BOOK_DETAIL}/${bookId}")
                        }
                    )
                }
                composable(
                    Routes.BOOK_DETAIL,
                    arguments = listOf(navArgument("bookId") { type = androidx.navigation.NavType.StringType }) // <<-- MODIFICACIÓN AQUÍ
                ) { backStackEntry ->
                    //val bookId: String? = backStackEntry.arguments?.getString("bookId")
                    BookDetailScreen(
                        bookId = "bookId",
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}