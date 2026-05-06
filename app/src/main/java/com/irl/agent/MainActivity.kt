package com.irl.agent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irl.agent.data.AppDatabase
import com.irl.agent.data.AppRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val database = AppDatabase.getDatabase(this)
        val repository = AppRepository(database.appLimitDao())
    }
}