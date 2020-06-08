package com.iflippie.level5_task2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.iflippie.level5_task2.model.Game
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var addViewModel: AddActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        addViewModel = ViewModelProvider(this).get(AddActivityViewModel::class.java)
    }

    private fun initViews() {
        fab.setOnClickListener { _ ->
            if (isFieldsValid()) {
                val title = etTitle.text.toString()
                val platform = etPlatform.text.toString()
                val releaseD = etDay.text.toString().toInt()
                val releaseM = etMonth.text.toString().toInt()
                val releaseY = etYear.text.toString().toInt()
                val gameToAdd: Game = Game(title, platform, releaseD, releaseM, releaseY)
                addViewModel.insertGame(gameToAdd)
                finish()
            } else {
                Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isFieldsValid() : Boolean {
        val fieldsValid =  !(etTitle.text.toString().isBlank() ||
        etPlatform.text.toString().isBlank() ||
        etDay.text.toString().isBlank() ||
        etMonth.text.toString().isBlank()  ||
        etYear.text.toString().isBlank())
        if (!fieldsValid) return false
        val monthValid = etMonth.text.toString().toInt()
        return monthValid in 1..12
    }

}
