package com.example.jamarpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import android.text.TextWatcher
import android.text.Editable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar y mostrar el Spinner
        val spinner = findViewById<Spinner>(R.id.my_spinner)
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.spinner_options, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val button = findViewById<MaterialButton>(R.id.my_button)
        button.isEnabled = false

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se usa
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {

                    button.isEnabled = false
                } else {
                    // Si se ha escrito algo en el EditText, habilita el botón
                    button.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No se usa
            }
        })
    }




}

private fun EditText.addTextChangedListener(any: Any) {

}
