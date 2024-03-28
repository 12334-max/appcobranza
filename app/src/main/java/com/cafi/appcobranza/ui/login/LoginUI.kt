package com.cafi.appcobranza.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cafi.appcobranza.R

class LoginUI {

    @Composable
    fun LoginScreen( navController: NavHostController) {
        val context = LocalContext.current
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isVisible by remember { mutableStateOf(false) }
        val keyboardController = LocalSoftwareKeyboardController.current
        var isTextFieldEnabled by remember { mutableStateOf(true) }
        var statusImage by remember{ mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(horizontal = 0.dp)
                .fillMaxSize()
                .background(Color.Transparent)

        ) {
            when(statusImage){
                false -> {
                    Image(
                        painter = painterResource(id = R.drawable.logo_cafi2),
                        contentDescription = "Logo",
                    )
                }
                true ->{
                    Box(
                        contentAlignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(1.0f, 1.0f)
                            .padding(bottom = 0.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_cafi1),
                            contentDescription = "Logo",
                        )
                    }
                }
            }

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            ){
                OutlinedCard(
                    modifier = Modifier
                        .height(290.dp)
                        .width(400.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.inverseSurface,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    shape = ShapeDefaults.ExtraLarge,
                    border = BorderStroke(1.dp, color = Color.DarkGray)
                ) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            isTextFieldEnabled = true
                        },
                        label = { Text("Usuario") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .onFocusChanged { statusImage = !statusImage },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Person, contentDescription = null)
                        },
                        shape = CircleShape
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 12.dp, end = 12.dp)
                            .onFocusChanged {
                                statusImage = !statusImage
                            }
                            .clickable {
                                isTextFieldEnabled = !isTextFieldEnabled
                            },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                                isTextFieldEnabled = !isTextFieldEnabled

                                statusImage = true
                            }
                        ),
                        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Lock, contentDescription = null)
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { isVisible = !isVisible }) {
                                when(isVisible){
                                    true -> {
                                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)
                                    }

                                    else -> {
                                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)
                                    }
                                }
                            }},
                        enabled = isTextFieldEnabled,
                        singleLine = true,
                        shape = CircleShape
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                            .background(Color.Transparent)
                    ){
                        Button(
                            {
                                password = ""
                                username = ""
                                statusImage = true
                                navController.navigate("home")
                            }, Modifier
                                .width(100.dp)
                                .height(90.dp)
                                .padding(top = 40.dp)
                                .background(Color.Transparent),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(255,230,230,),
                            ),
                            shape = ButtonDefaults.shape,
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 5.dp
                            )
                        ) {
                            Text("Iniciar")
                        }

                    }
                }
            }
        }
    }
}