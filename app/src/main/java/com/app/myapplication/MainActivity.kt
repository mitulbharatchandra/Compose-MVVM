package com.app.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.myapplication.presentation.search.SearchScreen
import com.app.myapplication.presentation.tracker_overview.TrackerOverviewScreen
import com.app.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val snackbarHostState = SnackbarHostState()
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    modifier = Modifier.fillMaxSize()
                ) { contentPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = TrackerOverview
                    ) {
                        composable<TrackerOverview> {
                            TrackerOverviewScreen(
                                modifier = Modifier.padding(contentPadding),
                                onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(Search(
                                        mealName = mealName,
                                        day = day,
                                        month = month,
                                        year = year
                                    ))
                                }
                            )
                        }
                        composable<Search> {
                            val args = it.toRoute<Search>()
                            SearchScreen(
                                scaffoldState = snackbarHostState,
                                modifier = Modifier.padding(contentPadding),
                                mealName = args.mealName,
                                dayOfMonth = args.day,
                                month = args.month,
                                year = args.year,
                                onNavigateUp = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object TrackerOverview

@Serializable
data class Search(
    val mealName: String,
    val day: Int,
    val month: Int,
    val year: Int
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}