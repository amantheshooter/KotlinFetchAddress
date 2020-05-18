package com.shooter.kotlinfetchaddress.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shooter.kotlinfetchaddress.R
import com.shooter.kotlinfetchaddress.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {
    private lateinit var dataViewModel: DataViewModel
    var addressList = ArrayList<String?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val autoTextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, addressList
        )
        autoTextView.setAdapter(adapter)

        autoTextView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dataViewModel.fetchAddress(s.toString(), city.text.toString())
                dataViewModel.addressLiveData.observe(this@MainActivity, Observer {
                    if (addressList.size > 0)
                        addressList.clear()
                    addressList.addAll(it.stream().map { it ->
                        it.addressString
                    }.collect(Collectors.toList()) as List<String>)
                    val adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_list_item_1, addressList
                    )
                    autoTextView.setAdapter(adapter)
                })
            }
        })

    }
}
