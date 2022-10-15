package com.wilman.easysoccer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.wilman.easysoccer.databinding.ActivityMakeUserReservationBinding
import com.wilman.easysoccer.ui.calendarUser.DatePickerFragment
import com.wilman.easysoccer.ui.calendarUser.TimePickerFragment
class MakeUserReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakeUserReservationBinding
    private val database = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeUserReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editTxtDate.setOnClickListener { showDatePickerDialog() }
        binding.editTxtTime.setOnClickListener { showTimePickerDialog() }
        binding.btnPayReservation.setOnClickListener {
            createReservation()
            var intent = packageManager.getLaunchIntentForPackage("com.nequi.MobileApp")
            if (intent == null) {
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("market://details?id=$packageName")
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }
    }

    fun createReservation(){

        val nameCenter = binding.txtNameSportCenterReal.text
        val numberPlayers = binding.editTxtNumberPlayers.text
        val date = binding.editTxtDate.text
        val time = binding.editTxtTime.text
        val payNow = binding.editTxtValueReservationNowStadium.text

        database.collection("users").document("Yotasars@gmail.com")
            .collection("reservation").document("Yotasars@gmail.com").set(
                hashMapOf(
                    "nameCenter" to nameCenter.toString(),
                    "numberPlayers" to numberPlayers.toString(),
                    "date" to date.toString(),
                    "time" to time.toString(),
                    "payNow" to payNow.toString()
                )
            )



    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { time -> onTimeSelected(time) }
        timePicker.show(supportFragmentManager, "TimePicker")
    }

    private fun onTimeSelected(time: String) {
        binding.editTxtTime.setText("$time")
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.editTxtDate.setText("$day / $month / $year")
    }

}