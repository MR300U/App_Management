package com.example.management.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.management.mvp.model.ModelMainActivity
import com.example.management.mvp.presenter.PresenterMainActivity
import com.example.management.mvp.view.ViewMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this)
        setContentView(view.binding.root)

        presenter = PresenterMainActivity(view , ModelMainActivity(this))
        presenter.onCreate()

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}