package br.com.module.situationworld.view.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import br.com.module.situationworld.R
import br.com.module.situationworld.models.Country
import br.com.module.situationworld.utils.NumberFormatCustomUtil
import br.com.module.situationworld.views.activities.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.Serializable


class RvaCountries(countries: MutableList<Country>, context: Context) : RecyclerView.Adapter<RvaCountries.ItemViewHolder>(), Filterable{

    private val mCountries: MutableList<Country>

    private var mCountriesFilter: MutableList<Country>

    private var mContext: Context

    var mFilter: MyFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.irv_coutries, parent, false)
        return ItemViewHolder(v)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.mTxtName.setText(mCountries[position].country)
        holder.mTxtContinent.setText(mCountries[position].continents)
        holder.mItemDivisao.setBackgroundColor(mContext.getColor(R.color.colorBackgroundSecondary))
        holder.mCountry = mCountries[position]
        loadWithGlide(holder, position)

        holder.mTxtCases.setText("Cases: "+ NumberFormatCustomUtil.longToStringNoDecimal(mCountries[position].cases))
        holder.mTxtIso3.setText(mCountries[position].countryInfo.iso3)

    }

    private fun loadWithGlide(holder: ItemViewHolder, position: Int) {
        Glide.with(holder.mImgFlag)
            .load(mCountries[position].countryInfo.flag)
            .error(R.drawable.notfound).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.mPgrImageView.visibility = View.GONE
                    holder.mImgFlag.setVisibility(View.VISIBLE)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.mPgrImageView.visibility = View.GONE
                    holder.mImgFlag.setVisibility(View.VISIBLE)
                    return false
                }

            }).into(holder.mImgFlag).clearOnDetach()
    }

    override fun getItemCount(): Int {
        return mCountries.size
    }

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val mImgFlag: ImageView
        val mItemDivisao: View
        val mPgrImageView: ProgressBar
        val mTxtName: TextView
        val mTxtContinent: TextView
        val mTxtCases: TextView
        val mTxtIso3: TextView
        lateinit var mCountry: Country

        init {
            mTxtContinent = itemView.findViewById(R.id.idTxtContinent)
            mTxtName = itemView.findViewById(R.id.idTxtName)
            mItemDivisao = itemView.findViewById(R.id.item_divisao)
            mImgFlag = itemView.findViewById(R.id.idImgFlag)
            mPgrImageView = itemView.findViewById(R.id.idPgrFlag)
            mTxtCases = itemView.findViewById(R.id.idTxtCases)
            mTxtIso3 = itemView.findViewById(R.id.idTxtIso3)
            mPgrImageView.visibility = View.VISIBLE


            itemView.setOnClickListener { view ->
                val viewImagem = itemView.findViewById<View>(R.id.idImgFlag)
                val intent = Intent(view.context, DetailActivity::class.java)
                intent.putExtra("COUNTRY", mCountry as Serializable)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val optionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (view.context as Activity),
                            Pair.create(
                                viewImagem,
                                itemView.context.resources
                                    .getString(R.string.app_transition_imagem)
                            )
                        )
                    view.context.startActivity(intent, optionsCompat.toBundle())
                } else {
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    init {
        mCountries = countries
        mCountriesFilter = ArrayList<Country>()
        mContext = context
    }

    override fun getFilter(): Filter? {
        if (mFilter == null) {
            mCountriesFilter.clear()
            mCountriesFilter.addAll(mCountries)
            mFilter = MyFilter(mCountriesFilter, this)
        }
        return mFilter
    }

    class MyFilter(countries: MutableList<Country>, rvaCountries: RvaCountries) : Filter() {

        var mCountriesFilter: MutableList<Country>
        var mCountries: MutableList<Country>
        var mRvaCountries: RvaCountries
        override fun performFiltering(p0: CharSequence): FilterResults {
            mCountriesFilter.clear()
            val results = FilterResults()
            if (p0.isEmpty()) {
                mCountriesFilter.addAll(mCountries)
            } else {
                val filterPattern: String = p0.toString().toLowerCase().trim()
                for (country in mCountries) {
                    if (country.country.toLowerCase().contains(filterPattern)) {
                        mCountriesFilter.add(country)
                    }
                }
            }

            results.values = mCountriesFilter
            results.count = mCountriesFilter.size
            return results
        }

        override fun publishResults(p0: CharSequence, p1: FilterResults) {
            mRvaCountries.mCountries.clear()
            mRvaCountries.mCountries.addAll(p1.values as ArrayList<Country>)
            mRvaCountries.notifyDataSetChanged()
        }

        init {
            mCountries = countries
            mCountriesFilter = ArrayList<Country>()
            mRvaCountries = rvaCountries
        }
    }
}