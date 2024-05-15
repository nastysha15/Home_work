package com.example.myapplication

import androidx.lifecycle.ViewModel
import java.lang.Math.PI
import java.util.Random
import kotlin.math.ln

class MainViewModel : ViewModel() {
    var mean: Double? = null
    var variance: Double? = null
    var randomNumber: Double? = null

    private val random = Random()

    fun getResult(): Double? {
        if (mean == null || variance == null)
            randomNumber = null
        else if (mean!! > 0 && variance!! >= 0)
            randomNumber = generate(mean!!, variance!!, 1.0)
        return randomNumber
    }

    private fun generate(mu: Double, sigma: Double, multi: Double): Double {
        val res = kotlin.math.sqrt(-2.0 * ln(random.nextDouble())) * kotlin.math.cos(2.0 * PI * random.nextDouble())
        return kotlin.math.exp(mu + kotlin.math.sqrt(sigma * multi) * res)
    }

}
