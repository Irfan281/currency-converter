package com.irfan.currencyapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.currencyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Currency Converter"


        binding.btnConvert.setOnClickListener {
            if (!binding.EditText.text.isNullOrEmpty()  && isNumeric(binding.EditText.text.toString())) {
                viewModel.getRates(
                    "zx7A5ngEAeKr8bG438XJiaevabWXp3Vr9ks7wrqI",
                    "USD",
                    "IDR,MYR,SGD,JPY"
                )
                setResult()
            } else {
                Toast.makeText(this, "Nilai Kosong atau Bukan Angka !!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let {
            val closeKeyboard: InputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as (InputMethodManager)
            closeKeyboard.hideSoftInputFromWindow(it.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progress.visibility = if (isLoading) View.VISIBLE else View.GONE
            card1.visibility = if (!isLoading) View.VISIBLE else View.INVISIBLE
            card2.visibility = if (!isLoading) View.VISIBLE else View.INVISIBLE
            card3.visibility = if (!isLoading) View.VISIBLE else View.INVISIBLE
            card4.visibility = if (!isLoading) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun isNumeric(toCheck: String): Boolean {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return toCheck.matches(regex)
    }

    private fun setResult() {
        viewModel.data.observe(this) {
            binding.apply {
                tvCode1.text = it.iDR.code
                tvNumber1.text = convert(it.iDR.value).toString()

                tvCode2.text = it.mYR.code
                tvNumber2.text = convert(it.mYR.value).toString()

                tvCode3.text = it.sGD.code
                tvNumber3.text = convert(it.sGD.value).toString()

                tvCode4.text = it.jPY.code
                tvNumber4.text = convert(it.jPY.value).toString()
            }
        }
    }

    private fun convert (rate: Double): Double {
        val input = binding.EditText.text.toString()

        return input.toDouble() * rate
    }
}