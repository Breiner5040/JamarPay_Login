package com.example.jamarpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.button.MaterialButton
import android.text.TextWatcher
import android.text.Editable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val secondScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler(Looper.getMainLooper())

        secondScope.launch {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://16oardvn23.execute-api.us-east-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val gettingDocumentType = retrofit.create(ApiService::class.java)
            val documentType = gettingDocumentType.getDocumentType("JA")
            Log.i("DocumentType", documentType.body().toString())
            val documentTypeList: List<DocumentTypeItem> = documentType.body()?.data ?: emptyList()
            val labels = documentTypeList.map { it.label }
            val mySpinner = findViewById<Spinner>(R.id.my_spinner)
            Log.i("Label", labels.toString())
            handler.post {
                val adapter =
                    ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, labels)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                mySpinner.adapter = adapter
            }

            val editText = findViewById<EditText>(R.id.editTextTextPersonName)
            val button = findViewById<MaterialButton>(R.id.my_button)
            button.isEnabled = false

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
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
}
