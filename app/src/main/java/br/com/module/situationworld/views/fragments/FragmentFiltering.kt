package br.com.module.situationworld.views.fragments

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ProgressBar
import androidx.annotation.Nullable
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.module.situationworld.R
import br.com.module.situationworld.models.Country
import br.com.module.situationworld.view.adapters.RvaCountries
import br.com.module.situationworld.viewmodels.FilteringViewModel


class FragmentFiltering : Fragment(){

    private lateinit var mFilteringViewModel: FilteringViewModel

    private lateinit var mRvCountries: RecyclerView

    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private lateinit var mRvaCountries: RvaCountries

    private lateinit var mView: View

    private lateinit var mPrgWorld: ProgressBar

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_filtering, container, false)

        mFilteringViewModel = ViewModelProvider(this).get(FilteringViewModel::class.java)

        mRvCountries = mView.findViewById(R.id.idRvCountries)

        mPrgWorld = mView.findViewById(R.id.idPgrWorld)

        setHasOptionsMenu(true)

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getContries()
    }

    private fun getContries() {
        mFilteringViewModel.getFiltering(mPrgWorld, mRvCountries).observe(viewLifecycleOwner, object : Observer<MutableList<Country>>{
            override fun onChanged(t: MutableList<Country>) {
                initRecyclerCoutries(t)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        activity?.menuInflater?.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setSubmitButtonEnabled(true)
        val searchEditText =
            searchView.findViewById<View>(R.id.search_src_text) as EditText
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                mRvaCountries.getFilter()?.filter(p0)
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                mRvaCountries.getFilter()?.filter(p0)
                return true
            }
        })
    }

    private fun initRecyclerCoutries(t: MutableList<Country>) {
        mRvCountries.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(activity)
        mRvCountries.layoutManager = mLayoutManager
        mRvaCountries = RvaCountries(t, mView.context)
        mRvCountries.adapter = mRvaCountries
    }
}