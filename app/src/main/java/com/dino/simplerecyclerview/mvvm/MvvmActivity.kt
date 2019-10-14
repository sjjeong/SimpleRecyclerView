package com.dino.simplerecyclerview.mvvm

import com.dino.simplerecyclerview.R
import com.dino.simplerecyclerview.base.BaseMvvmActivity
import com.dino.simplerecyclerview.databinding.ActivityMvvmBinding

class MvvmActivity :
    BaseMvvmActivity<ActivityMvvmBinding>(R.layout.activity_mvvm, MvvmViewModel::class.java)