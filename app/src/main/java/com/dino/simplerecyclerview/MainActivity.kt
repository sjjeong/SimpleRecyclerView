package com.dino.simplerecyclerview

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dino.simplerecyclerview.mvp.MvpActivity
import com.dino.simplerecyclerview.mvvm.MvvmActivity
import com.dino.simplerecyclerview.normal.NormalActivity
import com.dino.simplerecyclerview.pagination.PaginationActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun showNormalActivity(view: View) {
        startActivity(Intent(this, NormalActivity::class.java))
    }

    fun showMvpActivity(view: View) {
        startActivity(Intent(this, MvpActivity::class.java))
    }

    fun showMvvmActivity(view: View) {
        startActivity(Intent(this, MvvmActivity::class.java))
    }

    fun showPaginationActivity(view: View) {
        startActivity(Intent(this, PaginationActivity::class.java))
    }
}
