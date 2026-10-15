package com.example.ifwhenvar8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ifwhenvar8.ui.theme.IfWhenVar8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IfWhenVar8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AgeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AgeScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Возраст", fontSize = 20.sp)
            Spacer(Modifier.width(12.dp))
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Введите возраст") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(text = result, fontSize = 20.sp)
        Spacer(Modifier.height(24.dp))
        Button(onClick = { result = input }) {
            Text("OK")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgeScreenPreview() {
    AgeScreen()
}