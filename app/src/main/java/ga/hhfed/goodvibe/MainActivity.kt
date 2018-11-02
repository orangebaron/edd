package ga.hhfed.goodvibe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.thread
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private lateinit var sounds: SoundManager

    private fun mainLoop() {
        sounds.playSound(0, 1f)
    }

    val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sounds = SoundManager(this)
        timer.scheduleAtFixedRate(timerTask {
            try {
                mainLoop()
            } catch (e: Throwable) {
                println("UWE-ERR timer $e")
            }
        }, (1000).toLong(), (5000).toLong())
    }
}
