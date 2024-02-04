package com.gana.ebenezer.rachael.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer(private val context: Context) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile(file: File) {
        if (player == null) {
            player = MediaPlayer()
            player?.setDataSource(context, file.toUri())
            player?.prepare()
        }
        player?.start()
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}