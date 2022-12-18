package com.example.lab9_race

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.*
import com.example.lab9_race.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var progressRabbit = 0
    private var progressTurtle = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            binding.btnStart.isEnabled = false
            progressRabbit = 0
            progressTurtle = 0
            binding.sbRabbit.progress = 0
            binding.sbTurtle.progress = 0
            runRabbit()
            runTurtle()
        }
    }

    private fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


    private val handler = Handler(Looper.getMainLooper()) { msg ->

        if (msg.what == 1)
            binding.sbRabbit.progress = progressRabbit

        if (progressRabbit >= 100 && progressTurtle < 100) {
            showToast("兔子勝利")
            binding.btnStart.isEnabled = true
        }
        true
    }

    private fun runRabbit() {
        Thread {

            val sleepProbability = arrayOf(true, true, false)
            while (progressRabbit < 100 && progressTurtle < 100) {
                try {
                    Thread.sleep(100)
                    if (sleepProbability.random())
                        Thread.sleep(300)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressRabbit += 3
                val msg = Message()
                msg.what = 1
                handler.sendMessage(msg)
            }
        }.start()
    }

    private fun runTurtle() {
        GlobalScope.launch(Dispatchers.Main) {
            while (progressTurtle < 100 && progressRabbit < 100) {
                delay(100)
                progressTurtle += 1
                binding.sbTurtle.progress = progressTurtle
                if (progressTurtle >= 100 && progressRabbit < 100) {
                    showToast("烏龜勝利")
                    binding.btnStart.isEnabled = true
                }
            }
        }
    }
}