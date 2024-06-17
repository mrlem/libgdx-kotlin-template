package org.mrlem.sample.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import ktx.app.KtxGame
import org.mrlem.sample.core.screens.EmptyScreen

class Game : KtxGame<Screen>() {

    override fun create() {
        Gdx.app.log("app", "starting game")

        addScreen(EmptyScreen())

        setScreen<EmptyScreen>()
    }

}