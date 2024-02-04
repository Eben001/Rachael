package com.gana.ebenezer.rachael.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}