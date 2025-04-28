package com.example.calc

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity4 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playPauseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var seekBar: SeekBar
    private lateinit var songTitle: TextView
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        songTitle = findViewById(R.id.songTitle)
        playPauseButton = findViewById(R.id.playPauseButton)
        nextButton = findViewById(R.id.nextButton)
        prevButton = findViewById(R.id.prevButton)
        seekBar = findViewById(R.id.seekBar)*/

        // Инициализация MediaPlayer
        //mediaPlayer = MediaPlayer.create(this, R.raw.sample_song) // Замените на ваш трек
        seekBar.max = mediaPlayer.duration

        playPauseButton.setOnClickListener {
            if (isPlaying) {
                pauseMedia()
            } else {
                playMedia()
            }
        }

        nextButton.setOnClickListener {
            // Логика для следующего трека
        }

        prevButton.setOnClickListener {
            // Логика для предыдущего трека
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Не требуется
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Не требуется
            }
        })

        // Обновление SeekBar во время воспроизведения
        Thread {
            while (true) {
                if (isPlaying) {
                    seekBar.progress = mediaPlayer.currentPosition
                }
            }
        }.start()
    }

    private fun playMedia() {
        mediaPlayer.start()
        isPlaying = true
        playPauseButton.text = "Остановить"
    }

    private fun pauseMedia() {
        mediaPlayer.pause()
        isPlaying = false
        playPauseButton.text = "Пауза"
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
