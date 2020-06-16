package br.com.module.situationworld.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.module.situationworld.R
import br.com.module.situationworld.models.Historical
import br.com.module.situationworld.models.World
import br.com.module.situationworld.utils.GraphUtil
import br.com.module.situationworld.utils.NumberFormatCustomUtil
import br.com.module.situationworld.utils.ScrollViewUtil
import br.com.module.situationworld.viewmodels.WorldViewModel
import com.github.mikephil.charting.charts.LineChart


class FragmentWorld : Fragment(){

    private lateinit var mWorldViewModel: WorldViewModel

    private lateinit var mTxtCases: TextView

    private lateinit var mTxtTodayCases: TextView

    private lateinit var mTxtTodayDeaths: TextView

    private lateinit var mTxtDeaths: TextView

    private lateinit var mTxtTests: TextView

    private lateinit var mTxtTestsPerOneMillion: TextView

    private lateinit var mTxtCasesPerMillion: TextView

    private lateinit var mTxtDeathsPerMillion: TextView

    private lateinit var mTxtRecovered: TextView

    private lateinit var mTxtAffectedCountries: TextView

    private lateinit var mLCGraphCases: LineChart

    private lateinit var mLCGraphDeaths: LineChart

    private lateinit var mLCGraphRecovered: LineChart

    private lateinit var mPrgWorld: ProgressBar

    private lateinit var mLnContent: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_world, container, false)

        mWorldViewModel = ViewModelProvider(this).get(WorldViewModel::class.java)

        mTxtCases = root.findViewById(R.id.idTxtCases)
        mTxtTodayCases = root.findViewById(R.id.idTxtTodayCases)
        mTxtDeaths = root.findViewById(R.id.idTxtDeaths)
        mTxtTodayDeaths = root.findViewById(R.id.idTxtTodayDeaths)
        mTxtTests = root.findViewById(R.id.idTxtTests)
        mTxtRecovered = root.findViewById(R.id.idTxtRecovered)
        mTxtAffectedCountries = root.findViewById(R.id.idTxtAffectedCountries)
        mTxtCasesPerMillion = root.findViewById(R.id.idTxtCasesPerOneMillion)
        mTxtTestsPerOneMillion = root.findViewById(R.id.idTxtTestsPerOneMillion)
        mTxtDeathsPerMillion = root.findViewById(R.id.idTxtDeathsPerOneMillion)
        mLCGraphCases = root.findViewById(R.id.idLCGraphCases)
        mLCGraphDeaths = root.findViewById(R.id.idLCGraphDeaths)
        mLCGraphRecovered = root.findViewById(R.id.idLCGraphRecovered)
        mLCGraphCases.getParent().requestDisallowInterceptTouchEvent(true)
        mLCGraphRecovered.getParent().requestDisallowInterceptTouchEvent(true)
        mLCGraphDeaths.getParent().requestDisallowInterceptTouchEvent(true)
        mPrgWorld = root.findViewById(R.id.idPgrWorld)
        mLnContent = root.findViewById(R.id.idLnContent)

        ScrollViewUtil.onTouchListener(mLCGraphRecovered)
        ScrollViewUtil.onTouchListener(mLCGraphDeaths)
        ScrollViewUtil.onTouchListener(mLCGraphCases)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWorld()
    }

    private fun getWorld() {
        mWorldViewModel.getWorld().observe(viewLifecycleOwner, object : Observer<World> {
            override fun onChanged(t: World) {
                mTxtCases.text = NumberFormatCustomUtil.longToStringNoDecimal(t.cases)
                mTxtTodayCases.text = NumberFormatCustomUtil.longToStringNoDecimal(t.todayCases)
                mTxtDeaths.text = NumberFormatCustomUtil.longToStringNoDecimal(t.deaths)
                mTxtTodayDeaths.text = NumberFormatCustomUtil.longToStringNoDecimal(t.todayDeaths)
                mTxtTests.text = NumberFormatCustomUtil.longToStringNoDecimal(t.tests)
                mTxtTestsPerOneMillion.text = NumberFormatCustomUtil.floatToStringNoDecimal(t.testsPerOneMillion)
                mTxtCasesPerMillion.text = NumberFormatCustomUtil.longToStringNoDecimal(t.casesPerOneMillion)
                mTxtDeathsPerMillion.text = NumberFormatCustomUtil.floatToStringNoDecimal(t.deathsPerMillion)
                mTxtRecovered.text = NumberFormatCustomUtil.longToStringNoDecimal(t.recovered)
                mTxtAffectedCountries.text = NumberFormatCustomUtil.longToStringNoDecimal(t.affectedCountries)
                getHistorical()
            }
        })
    }

    private fun getHistorical(){
        mWorldViewModel.getHistorical(mLnContent, mPrgWorld).observe(viewLifecycleOwner, object : Observer<Historical>{
            override fun onChanged(t: Historical) {
                GraphUtil(t, mLCGraphCases, 1).generateGraph()
                GraphUtil(t, mLCGraphDeaths, 2).generateGraph()
                GraphUtil(t, mLCGraphRecovered, 3).generateGraph()
            }
        })
    }
}