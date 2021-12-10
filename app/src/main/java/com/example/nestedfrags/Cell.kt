package com.example.nestedfrags

import android.util.Log
import java.util.concurrent.ThreadLocalRandom

class Cell () {
    val volts = generateRandomFloat(2.8f , 4.2f)
    val temp = generateRandomFloat(-23f , 180f)

//    https://stackoverflow.com/questions/40431966/what-is-the-best-way-to-generate-a-random-float-value-included-into-a-specified/51247968
    fun generateRandomFloat(min: Float, max: Float): Float {
        require(min < max) { "max must be greater than min" }
        var result: Float = ThreadLocalRandom.current().nextFloat() * (max - min) + min
        if (result >= max) // correct for rounding
            result = java.lang.Float.intBitsToFloat(java.lang.Float.floatToIntBits(max) - 1)
        Log.i("CellActivity", "Cells Generated")
        return result
    }
}