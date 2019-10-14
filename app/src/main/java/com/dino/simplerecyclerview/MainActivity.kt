package com.dino.simplerecyclerview

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dino.simplerecyclerview.mvp.MvpActivity
import com.dino.simplerecyclerview.mvvm.MvvmActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun showMvpActivity(view: View) {
        startActivity(Intent(this, MvpActivity::class.java))
    }

    fun showMvvmActivity(view: View) {
        startActivity(Intent(this, MvvmActivity::class.java))
    }
}
