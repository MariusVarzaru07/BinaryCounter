package com.example.binarycounter2

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }
    private val timer: CountDownTimer
    private var millis:Long

    private var _currentTime = MutableLiveData<String>()
    val currentTime: LiveData<String>
        get() = _currentTime

    private var _reset = MutableLiveData<Boolean>()
    val reset: LiveData<Boolean>
        get() = _reset

    init {

        millis = 0L
        _reset.value = false

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
               _currentTime.value = Integer.toBinaryString((millis/ ONE_SECOND).toInt())
                millis = millis + ONE_SECOND

            }

            override fun onFinish() {
                // TODO implement what should happen when the timer finishes
               // _currentTime.value = (DONE/ ONE_SECOND).toString()
                _currentTime.value = Integer.toBinaryString((millis/ ONE_SECOND).toInt())
                millis = millis + ONE_SECOND
            }
        }

        timer.start()
    }

    fun resetCounter(){
        millis = 0L
        timer.start()
        _reset.value = false
    }

    fun onReset(){
        _reset.value = true
    }


    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel", "GameViewModel Destroyed")
    }

}