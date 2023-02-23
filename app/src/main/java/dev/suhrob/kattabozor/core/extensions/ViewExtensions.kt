package dev.suhrob.kattabozor.core.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

fun ImageView.setSvgColor(@ColorRes color: Int) =
    setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadImage(context: Context, path: String) {
    Log.d("dkkfjshdjn", "loadImage: $path")
    Glide.with(context).load(path)
        .into(this)
}

fun animateToolBarTittle(view: View) {
    YoYo.with(Techniques.BounceInRight)
        .duration(1300)
        .repeat(0)
        .playOn(view)
}