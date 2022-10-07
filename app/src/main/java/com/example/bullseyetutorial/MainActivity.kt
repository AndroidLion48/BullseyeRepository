package com.example.bullseyetutorial

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bullseyetutorial.databinding.ActivityMainBinding
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private var currentSliderValue = 0
    private val minSliderValue = 1
    private val maxSliderValue = 100

    private var playerScore = 0
    private var targetNumber = 0

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val random = Random()
        targetNumber = random.nextInt(maxSliderValue - minSliderValue) + minSliderValue
        binding.targetTxtView.text = targetNumber.toString()


        binding.hitMeButton.setOnClickListener {
            Log.i("Button Click Event", "You clicked the Hit Me button")
            showResult()
        }


        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentSliderValue = progress
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }

    private fun calculatePlayerScore(): Int {

        // calculate score based on Proximity to target Number
        // if tempSliderScore is less than or equal to targetNumber we Subtract FROM targetNumber
        // if tempSliderScore is greater than targetNumber we Subtract FROM tempSliderScore
        playerScore = abs(currentSliderValue - targetNumber)
        return playerScore
    }

    private fun showResult() {
        val dialogTitle = getString(R.string.result_dialog_title)
        val dialogMessage = getString(R.string.result_dialog_message, currentSliderValue, calculatePlayerScore())
//        val dialogMessage = "The slider's value is $sliderValue"

        val builder = AlertDialog.Builder(this)

        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.setPositiveButton(R.string.result_dialog_button_text) { dialog, _ -> dialog.dismiss() }

        builder.create().show()
    }
}
