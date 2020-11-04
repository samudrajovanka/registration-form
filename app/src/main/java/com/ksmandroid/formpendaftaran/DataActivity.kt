package com.ksmandroid.formpendaftaran

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity(), View.OnClickListener {
    private var textName = ""
    private var textNim = ""
    private var textStudyProgram = ""
    private var textGender = ""
    private var textAddress = ""
    private var textDateOfBirth = ""
    private var listOfMonth = arrayOf(
        "Januari",
        "Februari",
        "Maret",
        "April",
        "Mei",
        "Juni",
        "Juli",
        "Agustus",
        "September",
        "Oktober",
        "November",
        "Desember"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        initiateUI()
    }

    private fun initiateUI() {
        val intent = intent
        val date = intent.getStringExtra("DATE_USER")
        val month = intent.getIntExtra("MONTH_USER", 0)
        val year = intent.getStringExtra("YEAR_USER")

        btn_back.setOnClickListener(this)
        tv_name_data.text = intent.getStringExtra("NAME_USER")
        tv_nim_data.text = intent.getStringExtra("NIM_USER")
        tv_study_program_data.text = intent.getStringExtra("STUDY_PROGRAM_USER")
        tv_gender_data.text = intent.getStringExtra("GENDER_USER")
        tv_address_data.text = intent.getStringExtra("ADDRESS_USER")
        tv_date_of_birth_data.text = "$date ${listOfMonth.get(month-1)} $year"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}