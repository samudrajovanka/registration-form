package com.ksmandroid.formpendaftaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var textName = ""
    private var textNim = ""
    private var textStudyProgram = ""
    private var textGender = ""
    private var textAddress = ""
    private var textDate = ""
    private var textMonth = ""
    private var textYear = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initiateUI()
    }

    private val inputData: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            textName = et_name.text.toString().trim()
            textStudyProgram = et_study_program.text.toString().trim()
            textAddress = et_address.text.toString().trim()
            getRadioText()
            enableBtnSend()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private val inputNim: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            textNim = et_nim.text.toString().trim()
            if(textNim.length != 10) {
                tv_nim_alert.visibility = View.VISIBLE
            } else {
                tv_nim_alert.visibility = View.INVISIBLE
            }

            enableBtnSend()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private val inputDate: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            textDate = et_date.text.toString().trim()
            textMonth = et_month.text.toString().trim()
            textYear = et_year.text.toString().trim()

            if(textDate.isNotEmpty() && (textDate.toInt() < 0 || textDate.toInt() > 31)) {
                tv_date_alert.text = "Tanggal harus antara 1 sampai 31"
                tv_date_alert.visibility = View.VISIBLE
            } else if(textMonth.isNotEmpty() && (textMonth.toInt() < 0 || textMonth.toInt() > 12)) {
                tv_date_alert.text = "Bulan harus antara 1 sampai 12"
                tv_date_alert.visibility = View.VISIBLE
            } else if(textYear.isNotEmpty() && (textYear.toInt() < 1500 || textYear.toInt() > 2020)) {
                tv_date_alert.text = "Tahun harus antara 1500 sampai 2020"
                tv_date_alert.visibility = View.VISIBLE
            } else {
                tv_date_alert.visibility = View.INVISIBLE
            }

            enableBtnSend()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private fun initiateUI() {
        btn_send.setOnClickListener((this))

        et_name.addTextChangedListener(inputData)
        et_nim.addTextChangedListener(inputNim)
        et_study_program.addTextChangedListener(inputData)
        et_address.addTextChangedListener(inputData)
        et_date.addTextChangedListener(inputDate)
        et_month.addTextChangedListener(inputDate)
        et_year.addTextChangedListener(inputDate)

        getRadioText()
    }

    private fun getRadioText() {
        grup_radio_gender.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener() {
                radioGroup, i ->
            val radio: RadioButton = findViewById(i)
            textGender = radio.text.toString()
        })
    }

    private fun enableBtnSend() {
        btn_send.isEnabled =
            textName.isNotEmpty() && (textNim.isNotEmpty() && textNim.length == 10) && textStudyProgram.isNotEmpty() &&
                    textGender.isNotEmpty() && textAddress.isNotEmpty() && textDate.isNotEmpty() &&
                    textMonth.isNotEmpty() && textYear.isNotEmpty()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_send -> {
                val intent = Intent(this, DataActivity::class.java)

                intent.putExtra("NAME_USER", textName)
                intent.putExtra("NIM_USER", textNim)
                intent.putExtra("STUDY_PROGRAM_USER", textStudyProgram)
                intent.putExtra("GENDER_USER", textGender)
                intent.putExtra("ADDRESS_USER", textAddress)
                intent.putExtra("DATE_USER", textDate)
                intent.putExtra("MONTH_USER", textMonth.toInt())
                intent.putExtra("YEAR_USER", textYear)

                startActivity(intent)
            }
        }
    }
}