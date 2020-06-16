package br.com.module.situationworld.utils

import android.view.MotionEvent
import android.view.View
import com.github.mikephil.charting.charts.LineChart

object ScrollViewUtil {

    @JvmStatic
    fun onTouchListener(lineChart: LineChart) {
        lineChart.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val action = event.action
                when (action) {
                    MotionEvent.ACTION_DOWN ->         // Disallow ScrollView to intercept touch events.
                        v.parent.requestDisallowInterceptTouchEvent(false)
                    MotionEvent.ACTION_UP ->         // Allow ScrollView to intercept touch events.
                        v.parent.requestDisallowInterceptTouchEvent(false)
                }

                // Handle ListView touch events.
                v.onTouchEvent(event)
                return true
            }
        })
    }
}