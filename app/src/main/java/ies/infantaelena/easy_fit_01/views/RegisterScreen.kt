package ies.infantaelena.easy_fit_01.views

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import ies.infantaelena.easy_fit_01.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import ies.infantaelena.easy_fit_01.LoginName
import ies.infantaelena.easy_fit_01.LoginPassword
import ies.infantaelena.easy_fit_01.checkLogin
import ies.infantaelena.easy_fit_01.model.customTextSelectionColors


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SeePreview() {
//    RegisterScreen()
//}
private lateinit var auth: FirebaseAuth

@Composable
fun RegisterScreen(navController: NavController) {
    auth = FirebaseAuth.getInstance()
    var emailValue: String by rememberSaveable { mutableStateOf("") }
    var userValue: String by rememberSaveable { mutableStateOf("") }
    var passwordValue: String by rememberSaveable { mutableStateOf("") }
    var reppasswordValue: String by rememberSaveable { mutableStateOf("") }
    val context: Context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Image(
            painter = painterResource(id = R.drawable.easy_fit_logo),
            contentDescription = R.string.logoDescription.toString(),
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
        )
        // Llamada al metodo que contiene el Textfield del Email de usuario
        RegisterEmail(email = emailValue, onInputChanged = { emailValue = it })
        Spacer(modifier = Modifier.padding(top = 30.dp))
        // Llamada al metodo que contiene el Textfield del nombre de usuario
        RegisterName(user = userValue, onInputChanged = { userValue = it })
        Spacer(modifier = Modifier.padding(top = 30.dp))
        // Llamada al metodo que contiene el Textfield de la contrasenia
        RegisterPassword(password = passwordValue, onInputChanged = { passwordValue = it })
        Spacer(modifier = Modifier.padding(top = 30.dp))
        // Llamada al metodo que contiene el Textfield de la contrasenia repetida
        RegisterRepPassword(
            reppassword = reppasswordValue,
            onInputChanged = { reppasswordValue = it })
        Spacer(modifier = Modifier.padding(top = 30.dp))
        /*
        Componente Button que maneja el intento de entrada a la aplicacion llamando a la funcion
        checkLogin()
         */
        Button(
            onClick = {
                makeRegister(
                    email = emailValue,
                    user = userValue,
                    password = passwordValue,
                    reppassword = reppasswordValue,
                    context = context,
                    nav = navController
                )
            },
            modifier = Modifier
                .height(50.dp)
                .width(140.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Composable
fun RegisterEmail(email: String, onInputChanged: (String) -> Unit) {
    // funcion usada para envolver al textfield y darle el color que queramos al los text selectors
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        TextField(
            value = email,
            onValueChange = onInputChanged,
            label = {
                Text(
                    text = stringResource(id = R.string.email),
                    color = MaterialTheme.colors.onPrimary
                )
            },
            singleLine = true,
            modifier = Modifier
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary
            )
        )
    }

}

@Composable
fun RegisterName(user: String, onInputChanged: (String) -> Unit) {
    // funcion usada para envolver al textfield y darle el color que queramos al los text selectors
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        TextField(
            value = user,
            onValueChange = onInputChanged,
            label = {
                Text(
                    text = stringResource(id = R.string.user),
                    color = MaterialTheme.colors.onPrimary
                )
            },
            singleLine = true,
            modifier = Modifier
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary
            )
        )
    }
}

@Composable
fun RegisterPassword(password: String, onInputChanged: (String) -> Unit) {
    //Variable para saber si queremos mostrar o no la contrasenia
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        TextField(
            value = password,
            onValueChange = onInputChanged,
            label = {
                Text(
                    text = stringResource(id = R.string.password),
                    color = MaterialTheme.colors.onPrimary,
                )
            },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary,
            ),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Localizacion para accesivilidad
                val description =
                    if (passwordVisible) stringResource(id = R.string.hidePasswordDescription)
                    else stringResource(id = R.string.showPasswordDescription)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
    }
}

@Composable
fun RegisterRepPassword(reppassword: String, onInputChanged: (String) -> Unit) {
    //Variable para saber si queremos mostrar o no la contrasenia
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        TextField(
            value = reppassword,
            onValueChange = onInputChanged,
            label = {
                Text(
                    text = stringResource(id = R.string.reppassword),
                    color = MaterialTheme.colors.onPrimary,
                )
            },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary,
            ),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Localizacion para accesivilidad
                val description =
                    if (passwordVisible) stringResource(id = R.string.hidePasswordDescription)
                    else stringResource(id = R.string.showPasswordDescription)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
    }
}

fun makeRegister(
    email: String,
    user: String,
    password: String,
    reppassword: String,
    context: Context,
    nav: Any
) {
    fun showAlert(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en el registro")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
        //TODO: hacer el string de los alerts
    }

    val regex = "^[a-zA-Z0-9]*\$".toRegex()

    if (email.isBlank() || user.isBlank() || password.isBlank() || reppassword.isBlank()) {

        Toast.makeText(context, "Rellene los campos", Toast.LENGTH_SHORT).show()
        //TODO: Hay que hcaer los string de los toast
    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        Toast.makeText(context, "Fomato email incorrecto", Toast.LENGTH_SHORT).show()
    } else if (!regex.containsMatchIn(user)) {
        Toast.makeText(context, "Fomato user incorrecto", Toast.LENGTH_SHORT).show()
    } else if (!password.equals(reppassword)) {
        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
    } else {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(context, "todo correcto", Toast.LENGTH_SHORT).show()

                } else {
                   showAlert()
                }
            }

    }
    }
