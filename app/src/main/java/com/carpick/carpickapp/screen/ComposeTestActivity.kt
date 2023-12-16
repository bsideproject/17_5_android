package com.carpick.carpickapp.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.carpick.carpickapp.AppMain
import com.carpick.carpickapp.R
import com.carpick.carpickapp.ui.theme.CarpickAppTheme

class ComposeTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContent {
            CarpickAppTheme {
                val receivedData = intent.getIntExtra("page", -1)
                Log.e("ljy", "receive $receivedData")
                Button(onClick = {

                    val resultIntent = Intent().apply {
                        putExtra("page", receivedData)
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }) {
                    Text("Set Result and Finish")
                }
            }
        }
    }
}