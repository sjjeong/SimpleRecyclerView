package com.dino.simplerecyclerview.pagination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dino.simplerecyclerview.R
import com.dino.simplerecyclerview.databinding.ActivityPaginationBinding

class PaginationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaginationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pagination)
        binding.vm = PaginationViewModel()
    }

}