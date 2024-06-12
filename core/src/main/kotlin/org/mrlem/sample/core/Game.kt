package org.mrlem.sample.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import ktx.app.KtxGame

class Game : KtxGame<Screen>() {

    override fun create() {
        Gdx.app.log("app", "starting game")

        addScreen(SampleScreen())
        setScreen<SampleScreen>()
    }

}