package com.example.gobishops.myview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.example.gobishops.R


/**
 * Created by Yee on 2021/1/13.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
open class mImageView(context: Context, attrs: AttributeSet?): androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    private var mGradientDrawable:GradientDrawable? = null
    private var paint: Paint? = null
    private val bkColor = Color.WHITE
    private var cornerRadius: Float
    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.mImageView)
        cornerRadius = attr.getFloat(R.styleable.mImageView_mCornerRadius, 20f)
        paint = Paint()
    }


    private fun initView(){
        mGradientDrawable = GradientDrawable()
        mGradientDrawable!!.cornerRadius = cornerRadius
        background = mGradientDrawable
    }

    override fun onDraw(canvas: Canvas?) {
        val mDrawable: Drawable? = drawable
        if(null != mDrawable){
            Log.d("mImageView","not null")
            val bitmap: Bitmap = mDrawable.toBitmap()
            val b: Bitmap =getRoundBitmap(bitmap, cornerRadius)
            val rectSrc = Rect(0,0,b.width,b.height)
            val rectDest = Rect(0,0,width, height)
            paint!!.reset()
            canvas!!.drawBitmap(b, rectSrc, rectDest , paint)
        }else{
            Log.d("mImageView","null")
            super.onDraw(canvas)
        }

    }

    private fun getRoundBitmap(bitmap: Bitmap, radius: Float): Bitmap{
        var output: Bitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        var canvas: Canvas = Canvas(output)
        val rect: Rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF: RectF = RectF(rect)
        paint!!.isAntiAlias = true
        paint?.let { canvas.drawRoundRect(rectF, radius, radius, it) }
        paint!!.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}