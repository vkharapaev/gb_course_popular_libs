package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ActivityMainBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.MainPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener { presenter.onButton1Clicked() }
        vb?.btnCounter2?.setOnClickListener { presenter.onButton2Clicked() }
        vb?.btnCounter3?.setOnClickListener { presenter.onButton3Clicked() }

    }

    override fun showText1(text: String) {
        vb?.btnCounter1?.text = text
    }

    override fun showText2(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun showText3(text: String) {
        vb?.btnCounter3?.text = text
    }

}