package com.headmostlab.gbconverter.ui.view

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.headmostlab.gbconverter.R
import com.headmostlab.gbconverter.databinding.ActivityMainBinding
import com.headmostlab.gbconverter.domain.Image
import com.headmostlab.gbconverter.ui.presenter.MainPresenter
import com.headmostlab.gbconverter.ui.presenter.MainView
import com.headmostlab.gbconverter.AndroidImageConverter
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.io.File

class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter {
        MainPresenter(AndroidImageConverter(), AndroidSchedulers.mainThread())
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnChooseImage.setOnClickListener {
            presenter.onChooseImage()
        }

        binding.btnConvertOrCancel.setOnClickListener {
            val storeToPath = File(cacheDir.absolutePath, getString(R.string.new_picture_name))
            presenter.convert(storeToPath)
        }
    }

    override fun showConvertButton() {
        binding.btnConvertOrCancel.text = getString(R.string.convert)
    }

    override fun showCancelButton() {
        binding.btnConvertOrCancel.text = getString(R.string.cancel)
    }

    override fun showMessageChooseImage() {
        Toast.makeText(this, getString(R.string.msg_choose_image), Toast.LENGTH_SHORT).show()
    }

    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                contentResolver.openInputStream(uri)?.readBytes()?.let { data ->
                    presenter.onImageSelected(Image(uri.toString(), data))
                }
            }
        }

    override fun chooseImage() {
        imagePicker.launch("image/*")
    }

    private fun setTimerImage() {
        val animatedDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_clock_timer)
        animatedDrawable?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                animatedDrawable.start()
            }
        })
        binding.ivConvertedImage.setImageDrawable(animatedDrawable)
        animatedDrawable?.start()
    }

    override fun showChosenImage(image: Image) {
        Picasso.get().load(Uri.parse(image.path)).into(binding.ivOriginalImage)
    }

    override fun showTimer() {
        setTimerImage()
    }

    override fun showPlaceholder() {
        binding.ivConvertedImage.setImageResource(R.drawable.ic_placeholder)
    }

    override fun showConvertedImage(image: File) {
        Picasso.get().load(image).memoryPolicy(MemoryPolicy.NO_CACHE).into(binding.ivConvertedImage)
    }

    override fun showError(error: Throwable?) {
        error?.message?.let { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

}