package com.thermondo.androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.thermondo.designsystem.component.ThermondoButton
import com.thermondo.designsystem.theme.ThermondoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThermondoTheme {
                ThermondoButton(onClick = { }) {
                    Text(text = "Design system")
                }
            }
        }
    }
}