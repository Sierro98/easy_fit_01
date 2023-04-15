package ies.infantaelena.easy_fit_01

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ies.infantaelena.easy_fit_01.data.MenuItem
import ies.infantaelena.easy_fit_01.navigation.Screen
import ies.infantaelena.easy_fit_01.views.AppBar
import ies.infantaelena.easy_fit_01.views.DrawerBody
import ies.infantaelena.easy_fit_01.views.DrawerHeader
import kotlinx.coroutines.launch


@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        // TODO: internacionalizar los items del menu
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "user",
                        title = "User",
                        contentDescription = "Go to user screen",
                        icon = Icons.Default.Person
                    ),
                    MenuItem(
                        id = "challenges",
                        title = "Challenges",
                        contentDescription = "Go to challenges screen",
                        icon = Icons.Default.Star
                    ),
                    MenuItem(
                        id = "info",
                        title = "Information",
                        contentDescription = "Go to information screen",
                        icon = Icons.Default.Info
                    ),
                    MenuItem(
                        id = "logout",
                        title = "Logout",
                        contentDescription = "Logout and go to the login page",
                        icon = Icons.Default.Logout
                    )
                ),
                // TODO: hacer todas las redirecciones
                onItemClick = {
                    Toast.makeText(context, "Pulsado ${it.title}", Toast.LENGTH_SHORT).show()
                    when (it.id) {
                        "logout" -> {
                            // TODO: en el caso del logout, hacer logout en la base de datos
                            navController.popBackStack()
                            navController.navigate(Screen.LoginScreen.route)
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        paddingValues.calculateTopPadding()
    }
}
