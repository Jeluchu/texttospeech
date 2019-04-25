package com.jeluchu.texttospeech

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTextToSpeech()
        btnSpeak.setOnClickListener {
            speak()
        }

    }

    private fun initTextToSpeech() {
        mTTS = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                mTTS.language = Locale("es", "ES")
            }
        })
    }

    private fun speak() {
        val text = etTexttoSay.text.toString()
        var pitch = sbPitch.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1f
        var speed = sbSpeed.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1f

        mTTS.setPitch(pitch)
        mTTS.setSpeechRate(speed)

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

}
