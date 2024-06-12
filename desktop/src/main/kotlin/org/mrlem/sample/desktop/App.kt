package org.mrlem.sample.desktop

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import org.mrlem.sample.core.Game

fun main() {
    val vsyncEnabled = true
    val antiAliasingEnabled = true

    Lwjgl3Application(
        Game(),
        Lwjgl3ApplicationConfiguration().apply {
            useVsync(vsyncEnabled)
            setForegroundFPS(if (vsyncEnabled) 60 else 0)
            setIdleFPS(if (vsyncEnabled) 30 else 0)
            val displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode()
            setWindowedMode(displayMode.width * 3 / 4, displayMode.height * 3 / 4)
            setTitle("Sample game")
            if (antiAliasingEnabled) {
                setBackBufferConfig(8, 8, 8, 8, 24, 0, 8)
            }
        },
    )
}