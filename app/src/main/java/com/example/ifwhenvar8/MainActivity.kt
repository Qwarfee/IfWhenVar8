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
    var error by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    fun onOkClick() {
        focusManager.clearFocus()
        val text = input.trim()

        if (text.isEmpty()) {
            error = "Введите возраст"
            result = ""
            return
        }

        val age = text.toIntOrNull()
        if (age == null) {
            error = "Введите целое число"
            result = ""
            return
        }

        error = ""
        result = getAgeMessage(age)
    }

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
                onValueChange = { new ->
                    if (new.length <= 3 && new.all { it.isDigit() }) {
                        input = new
                        error = ""
                    }
                },
                isError = error.isNotEmpty(),
                supportingText = { if (error.isNotEmpty()) Text(error) },
                label = { Text("Введите возраст") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { onOkClick() }),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(text = result, fontSize = 20.sp)
        Spacer(Modifier.height(24.dp))
        Button(onClick = { onOkClick() }) {
            Text("OK")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgeScreenPreview() {
    AgeScreen()
}

// Возвращает сообщение для введённого возраста
fun getAgeMessage(age: Int): String = when (age) {
    in Int.MIN_VALUE..20 -> "Вы слишком молоды!"
    30, 40, 50, 60       -> "Поздравляем с повышением!"
    65                   -> "Преподносим вам золотые часы!"
    in 66..Int.MAX_VALUE -> "Вы слишком стары!"
    else                 -> "Продолжайте накапливать опыт!"
}