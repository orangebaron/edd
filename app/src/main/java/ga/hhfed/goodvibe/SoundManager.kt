package ga.hhfed.goodvibe

import android.content.Context
import android.media.SoundPool

class SoundManager(private val context: Context) {
    private var currentSound = -1
    fun stopSound() {
        if (currentSound == -1) return
        soundPlr.stop(currentSound)
        currentSound = -1
    }
    fun playSound(soundNum: Int, volume: Float, rate: Float = 1f, loop: Int = 0) {
        stopSound()
        currentSound = soundPlr.play(loadedSounds[soundNum], volume, volume, 0, loop, rate)
    }
    private val soundPlr: SoundPool = SoundPool.Builder().setMaxStreams(20).build()
    private fun loadSound(i: Int) = soundPlr.load(context, i, 0)
    private val loadedSounds = listOf(
        loadSound(R.raw.signman),
        loadSound(R.raw.thunderstormy_metal)
    )
}