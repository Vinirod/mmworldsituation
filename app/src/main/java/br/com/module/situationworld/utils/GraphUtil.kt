package br.com.module.situationworld.utils;

import android.graphics.Color
import android.os.SystemClock
import android.view.MotionEvent
import br.com.module.situationworld.models.Historical
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class GraphUtil(historical: Historical, lineChart: LineChart, choose: Long) {

    private val mHistorical: Historical
    private val mLineChart: LineChart
    private val mChoose: Long

    fun generateGraph() {
        mLineChart.setDragEnabled(true)
        mLineChart.setScaleEnabled(false)
        val yValues = ArrayList<Entry>()
        preencherValoresY(yValues, mHistorical)
        defineValueRightGraph(mLineChart)
        val set1: LineDataSet = defineLineGraph(yValues)
        defineValueTopGraph(mHistorical, mLineChart)
        defineDataSetOnGraph(mLineChart, set1)
        updateGraph(mLineChart)
    }

    private fun updateGraph(mLCGraphCases: LineChart) {
        val motionEvent = MotionEvent.obtain(
            SystemClock.uptimeMillis(),
            SystemClock.uptimeMillis() + 100,
            MotionEvent.ACTION_UP,
            0.0f,
            0.0f,
            0
        )
        mLCGraphCases.dispatchTouchEvent(motionEvent)
    }

    private fun defineDataSetOnGraph(mLCGraphCases: LineChart, set1: LineDataSet) {
        val dataSets = java.util.ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)
        mLCGraphCases.setData(data)
        mLCGraphCases.setTouchEnabled(true)
    }

    private fun defineValueTopGraph(historical: Historical, lineChart: LineChart) {
        lineChart.getAxisRight().setDrawLabels(false)
        val desc = Description()
        desc.isEnabled = false
        lineChart.setDescription(desc)

        val xAxis: XAxis = lineChart.getXAxis()
        xAxis.valueFormatter = AxisValueFormatter(
            when(mChoose){
                1L -> historical.cases.keys.toList().toTypedArray()
                2L -> historical.deaths.keys.toList().toTypedArray()
                else -> historical.recovered.keys.toList().toTypedArray()
            }
        )
        xAxis.granularity = 1f
    }

    private fun defineLineGraph(yValues: ArrayList<Entry>): LineDataSet {
        val set1: LineDataSet = when(mChoose){
            1L -> LineDataSet(yValues, "Total cases per month")
            2L -> LineDataSet(yValues, "Total deaths per month")
            else -> LineDataSet(yValues, "Total recovered per month")
        }
        set1.fillAlpha = 110
        set1.color = Color.RED
        set1.lineWidth = 3f
        set1.lineWidth = 3f
        set1.valueTextSize = 10f
        set1.valueTextColor = Color.BLACK
        return set1
    }

    private fun defineValueRightGraph(lineChart: LineChart) {
        val leftAxis: YAxis = lineChart.getAxisRight()
        leftAxis.enableGridDashedLine(10f, 10f, 0f)
        leftAxis.setDrawLimitLinesBehindData(true)
    }

    private fun preencherValoresY(yValues: ArrayList<Entry>, historical: Historical) {
        yValues.clear()
        val arrayHistorical = when(mChoose) {
            1L -> historical.cases.values.toList() as List<Float>
            2L -> historical.deaths.values.toList() as List<Float>
            else -> historical.recovered.values.toList() as List<Float>
        }

        for (i in arrayHistorical.indices) {
            yValues.add(Entry(i.toFloat(), arrayHistorical[i]))
        }
    }

    init {
        mLineChart = lineChart
        mHistorical = historical
        mChoose = choose
    }

    class AxisValueFormatter(private val mValues: Array<String>) :
        ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return if (value.toInt() >= mValues.size || value.toInt() == -1) mValues[0] else mValues[value.toInt()]
        }
    }
}