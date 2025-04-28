package com.example.calc

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calc.databinding.ActivityMain3Binding
import java.io.IOException


class MainActivity3 : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    //@SuppressLint("SdCardPath")
    //private val filePath = "Internal shared storage/Zelda.mp3"
        //private val audioPath = "/Internal shared storage/emulated/0/Zelda.mp3"
    private val targetFileName = "Zelda.mp3" // Имя файла, который мы ищем
    lateinit var binding3: ActivityMain3Binding

    //private lateinit var mediaPlayer: MediaPlayer
    private lateinit var searchInput: EditText

    private lateinit var seekBar: SeekBar
    private lateinit var runnable: Runnable
    private val handler = Handler()
    private var delay = 1000L
    var isPlaying = false
    private var currentTrackIndex = 0
    /*private val tracks = listOf(
        R.raw.em,
        R.raw.tech,
    )*/
    //private var tracks = listOf<String>()
    //private val tracks = loadMusicFromStorage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding3 = ActivityMain3Binding.inflate(layoutInflater)
        val view3: LinearLayout = binding3.root
        setContentView(view3)


        //mediaPlayer = MediaPlayer.create(this, tracks)
        /*seekBar = findViewById(R.id.seekBar)
        seekBar.max = mediaPlayer.duration
        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, delay)
        }*/
        //loadTrack(currentTrackIndex)            ////
        binding3.buttonPlay.setOnClickListener {
            //startPlayback()
            //playAudio()
            //playFirstAudioFromMediaStore()
            findAndPlaySpecificAudio()

        }

        binding3.buttonBack.setOnClickListener() {
            // playPreviousTrack()
        }
        binding3.buttonNext.setOnClickListener() {
            //playNextTrack()
        }
    }

    private fun findAndPlaySpecificAudio() {
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.RELATIVE_PATH
        )


        val selection = "${MediaStore.Audio.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(targetFileName)

        val cursor = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val id = it.getLong(it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                val displayName =
                    it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                playAudio(contentUri, displayName)
            } else {
                Toast.makeText(this, "Файл,который мы ищем $targetFileName не найден", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun playAudio(uri: Uri, title: String) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(applicationContext, uri)
                prepareAsync()

                setOnPreparedListener { mp ->
                    mp.start()
                    /*Toast.makeText(
                        this@MediaStoreAudioPlayerActivity,
                        "Воспроизводится: $title",
                        Toast.LENGTH_SHORT
                    ).show()*/
                }

                setOnErrorListener { _, _, _ ->
                    /*Toast.makeText(
                        this@MediaStoreAudioPlayerActivity,
                        "Ошибка воспроизведения",
                        Toast.LENGTH_SHORT
                    ).show()*/
                    true
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}
/*private fun playAudio() {
    // Если плеер уже играет, освобождаем его
    mediaPlayer?.release()

    try {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioPath)
            prepareAsync() // Асинхронная подготовка чтобы не блокировать UI поток

            setOnPreparedListener { mp ->
                mp.start()
            }

            setOnErrorListener { mp, what, extra ->
                // Обработка ошибки воспроизведения
                true
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        // Обработка ошибки (например, файл не найден)
    }
}
}*/

    /*private fun startPlayback() {
        try {
            //mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(filePath)
                //prepareAsync()
                setOnPreparedListener { start() }
                setOnErrorListener { _, what, extra ->
                    println("Ошибка воспроизведения: $what, $extra")
                    false
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }*/
//}

        /*seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                handler.removeCallbacks(runnable)
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                handler.postDelayed(runnable, delay)
            }
        })
    }*/

        /* private fun loadTrack(index: Int) {
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
        mediaPlayer = MediaPlayer.create(this, tracks[index])
        mediaPlayer.setOnCompletionListener {
            if (currentTrackIndex < tracks.size - 1) {
                currentTrackIndex++
                loadTrack(currentTrackIndex)
                mediaPlayer.start()
            }
            binding3.songTitle.text = "Track ${index + 1}"

            *//*else{
                mediaPlayer.stop()
                currentTrackIndex = 0
                loadTrack(currentTrackIndex)
                mediaPlayer.start()
            }*//*
        }
        binding3.songTitle.text = "Track ${index + 1}"


    }*/

        /*private fun loadMusicFromStorage(): List<String> {
        val musicList = mutableListOf<String>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Audio.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val path = it.getString(0)
                if (path.endsWith(".mp3") || path.endsWith(".wav")) {
                    musicList.add(path)
                }
            }
        }
        return musicList
    }*/
        /*private fun playTrack(index: Int) {
        try {
            // Освобождаем предыдущий MediaPlayer
            if (::mediaPlayer.isInitialized) {
                mediaPlayer.release()
            }

            // Создаем новый MediaPlayer для текущего трека
            mediaPlayer = MediaPlayer().apply {
                setDataSource(tracks[index]) // Устанавливаем путь к файлу
                prepareAsync() // Готовимся к воспроизведению (асинхронно)

                setOnPreparedListener { mp ->
                    // Когда трек готов к воспроизведению
                    //binding3.songTitle.text = getTrackName(tracks[index])
                    seekBar.max = mp.duration
                    if (isPlaying) {
                        mp.start()
                        handler.postDelayed(runnable, 1000)
                    }
                }

                setOnCompletionListener {
                    // Когда трек закончился - воспроизводим следующий
                    playNextTrack()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Ошибка воспроизведения", Toast.LENGTH_SHORT).show()
        }
    }*/
        /*private fun playNextTrack() {
        if (tracks.isEmpty()) return

        currentTrackIndex = (currentTrackIndex + 1) % tracks.size
        playTrack(currentTrackIndex)
        if (isPlaying) {
            mediaPlayer.start()
        }
    }

    private fun playPreviousTrack() {
        if (tracks.isEmpty()) return

        if (mediaPlayer.currentPosition > 3000) {
            // Если трек играет больше 3 секунд - перематываем в начало
            mediaPlayer.seekTo(0)
        } else {
            // Иначе переключаем на предыдущий трек
            currentTrackIndex = (currentTrackIndex - 1 + tracks.size) % tracks.size
            playTrack(currentTrackIndex)
            if (isPlaying) {
                mediaPlayer.start()
            }
        }
    }*/


