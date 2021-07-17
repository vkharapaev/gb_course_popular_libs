package com.headmostlab.gbconverter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.core.content.res.ResourcesCompat
import com.headmostlab.gbconverter.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import java.io.File

class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        const val QUALITY = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            binding.btnConvert.text = getString(R.string.cancel)
            binding.image.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_placeholder,
                    theme
                )
            )

            Handler(mainLooper).postDelayed({
                val fileName = getString(R.string.picture_name)
                val jpgPictureStream = resources.assets.open("$fileName.jpg")
                val storePath = File(cacheDir.absolutePath, "$fileName.png")
                val bmp = BitmapFactory.decodeStream(jpgPictureStream)
                bmp.compress(Bitmap.CompressFormat.PNG, QUALITY, storePath.outputStream())
                binding.image.setImageURI(Uri.fromFile(storePath))
                binding.btnConvert.text = getString(R.string.convert)
            }, 500)

        }
    }
}