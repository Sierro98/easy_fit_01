package ies.infantaelena.easy_fit_01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ies.infantaelena.easy_fit_01.navigation.Screen

/**
 * Clase con la funcionalidad de MainScreen
 */
class MainScreenViewModel(): ViewModel(){
    /**
     * Funcion que se encarga de deslogear el usuario de Firebase Authentication
     */
    fun LogOut(nav: NavController){
        try {
            FirebaseAuth.getInstance().signOut()
            nav.popBackStack()
            nav.navigate(route = Screen.LoginScreen.route)
        }catch (ex:java.lang.Exception){
            ex.printStackTrace()
        }



    }
}