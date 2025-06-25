package com.example.appshop.presentation.screens.login

import LoginViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.appshop.R
import com.example.appshop.presentation.theme.ui.GreenButton

@Composable
fun LoginContent(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text("Hello Again!", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(
            "Welcome Back You've Been Missed!",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Text(
            "Recovery Password",
            color = Color(0xFF2ECC71),
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { /* TODO: implementar recuperación */ },
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenButton, contentColor = Color.White)
        ) {
            Text("Sign In", fontSize = 18.sp)
        }

        // Mensaje de error si el login falla
        val loginResult = viewModel.loginResult
        if (loginResult == false) {
            Text(
                text = "Email or password incorrect",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Or sign in with", color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(12.dp))

        // Botón Google
        OutlinedButton(
            onClick = { /* TODO: Iniciar sesión con Google */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Google",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign in with Google")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón Facebook
        OutlinedButton(
            onClick = { /* TODO: Iniciar sesión con Facebook */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1877F2))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "Facebook",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign in with Facebook")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text("Don’t have an account?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Sign Up For Free",
                color = Color(0xFF2ECC71),
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }

        // Navegación en caso de login exitoso
        if (loginResult == true) {
            onLoginSuccess()
        }
    }
}
