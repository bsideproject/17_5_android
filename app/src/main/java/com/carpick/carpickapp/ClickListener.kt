package com.carpick.carpickapp

import com.carpick.carpickapp.model.Choice

interface ClickListener {
    fun click(item : Choice)
}