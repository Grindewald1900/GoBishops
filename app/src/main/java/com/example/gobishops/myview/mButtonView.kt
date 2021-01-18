package com.example.gobishops.myview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import com.example.gobishops.R
import kotlin.properties.Delegates


/**
 * Created by Yee on 2020/12/27.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
open class mButtonView(context: Context, attrs: AttributeSet?): androidx.appcompat.widget.AppCompatButton(context, attrs) {
    private var mGradientDrawable: GradientDrawable? = null
    private var mValueAnimator: ValueAnimator? = null
    private var paint: Paint? = null
    private var mLeft = 0f
    private var mRight = 0f
    private var start = 0f
    private var isDraw = false
    private val cornerRadius = 10f
    private var bkColor = Color.WHITE
    private var borderColor by Delegates.notNull<Int>()

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.mButtonView)
        borderColor = attr.getColor(R.styleable.mButtonView_border_color, Color.BLACK)
        bkColor = attr.getColor(R.styleable.mButtonView_background_color, Color.WHITE)
        initView()
    }

    /**
     * View initialization
     */
    private fun initView(){
        // Initialize painter
        paint = Paint()
        paint!!.isAntiAlias = true
//        paint!!.color = Color.parseColor("#ffffff")
        paint!!.color = borderColor
        paint!!.strokeWidth = 2f
        paint!!.style = Paint.Style.STROKE

        // Initialize button background scheme
        mGradientDrawable = GradientDrawable()
        mGradientDrawable!!.setColor(bkColor)
        mGradientDrawable!!.cornerRadius = cornerRadius
        background = mGradientDrawable
    }


    /**
     * Button Animation
     */
    open fun mBtnAnimation(){
        isEnabled = false
        text = ""
        val mValueAnimator = ValueAnimator.ofInt(width, height)
        mValueAnimator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val value: Int = animation?.animatedValue as Int
                mLeft = (width - value)/2f
                mRight = width - (width - value)/2f
                mGradientDrawable!!.setBounds(mLeft.toInt(), 0,mRight.toInt(), height)
            }
        })
        mValueAnimator.duration = 500
        mValueAnimator.start()
        mValueAnimator.addListener(object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                // Draw arc and load arc animation
                drawArc()
            }
        })
    }

    /**
     * Arc animation
     */
    private fun drawArc(){
        // Change the state so that onDraw() can draw the arc
        isDraw = true
        mValueAnimator = ValueAnimator.ofFloat(0f,360f)
        mValueAnimator!!.addUpdateListener(object :ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                start = animation!!.animatedValue as Float
                invalidate()
            }
        })
        mValueAnimator!!.repeatCount = ValueAnimator.INFINITE // 设置动画无限重复
        mValueAnimator!!.duration = 500
        mValueAnimator!!.start()
    }

    /**
     * End Animation
     */
    open fun endAnim(btnText:String){
        val mValueAnimator = ValueAnimator.ofInt(0,width)
        mValueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                val value: Int = animation.animatedValue as Int
                mGradientDrawable!!.setBounds((width-value)/2,0,width-(width-value)/2,height)
            }
        })
        mValueAnimator.duration = 500
        mValueAnimator.start()
        mValueAnimator.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                text = btnText
                isEnabled = true
            }
        })

        mValueAnimator!!.end()
        isDraw = false
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isDraw){
            val mRectF = RectF(mLeft+10,5f,mRight-10,height-10.toFloat())
            paint?.let { canvas!!.drawArc(mRectF,start,160f,false, it) } //画圆弧
        }

    }

}