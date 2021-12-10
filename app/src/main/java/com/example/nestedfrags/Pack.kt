package com.example.nestedfrags

import android.util.Log

class Pack constructor(numberOfModule: Int){
    var name = "pack"
    var modules = (1..numberOfModule).map { Module() }
    var avTemp : Float = 0.0f
    var avVolt : Float = 0.0f
    init {
        setAverages(modules)
    }

    fun setAverages(array: List<Module>){
        var totalModV = 0.0f
        var totalModT  = 0.0f
        var totalV   = 0.0f
        var totalT  = 0.0f

        for (mod : Module in array){
            for(cell : Cell in mod.cells){
//            Accumulate Cell voltages
                totalV+= cell.volts
                totalT+= cell.temp
            }
//            Set all Module averages
            mod.avVolts = (totalV/6)*6
            mod.avTemp = (totalV/6)*6
            Log.i("Pack Activity", "Module Generated\n" +
                    "Mod ${modules.indexOf(mod)}\n" +
                    "Voltage = ${mod.avVolts}        Temp = ${mod.avTemp}")

//            Accumulate Module averages
            totalModV+= mod.avVolts
            totalModT+= mod.avTemp
        }
//        Set pack Averages
        avVolt = (totalModV/array.size)*array.size
        avTemp = (totalModV/array.size)*array.size
        Log.i("Pack Activity", "Pack Generated\n" +
                "Pack: ${name}\n" +
                "Voltage = ${avVolt}        Temp = ${avTemp}")
    }

}