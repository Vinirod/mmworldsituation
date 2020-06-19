package br.com.module.situationworld.views.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import br.com.module.situationworld.R
import br.com.module.situationworld.databinding.ActivityDetailBinding
import br.com.module.situationworld.models.Country
import br.com.module.situationworld.utils.AnimatorTabUtils
import br.com.module.situationworld.utils.VersionUtils
import com.bumptech.glide.Glide
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks
import com.github.ksoichiro.android.observablescrollview.ScrollState
import com.github.ksoichiro.android.observablescrollview.ScrollUtils

class DetailActivity : AppCompatActivity() , ObservableScrollViewCallbacks {

    private lateinit var mCountry: Country

    private lateinit var mSlContent: ObservableScrollView

    private lateinit var mToolbarView: Toolbar

    private var mParallaxImageHeight: Int = 0

    private lateinit var mImgFlag: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        VersionUtils.checkVersioAndEnableTransition(window)
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this@DetailActivity, R.layout.activity_detail)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        mCountry = intent.getSerializableExtra("COUNTRY") as Country
        binding.country = mCountry
        mSlContent = findViewById(R.id.idSlContent)
        mToolbarView = findViewById(R.id.idTbDetail)
        mToolbarView = findViewById(R.id.idTbDetail)
        mImgFlag = findViewById(R.id.idImgFlag)
        mSlContent.setScrollViewCallbacks(this)
        Glide.with(mImgFlag.getContext()).load(mCountry.countryInfo.flag).error(R.drawable.notfound).into(mImgFlag)
        setSupportActionBar(mToolbarView)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_back);

        val text: Spannable = SpannableString(supportActionBar?.title)
        text.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            text.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        supportActionBar?.title = text

        startToolbar()
    }

    private fun startToolbar() {
        setSupportActionBar(mToolbarView)
        mToolbarView.visibility = View.GONE
        this.title = getString(R.string.app_detail)
        mToolbarView.setTitleTextColor(getColor(R.color.colorText))
        mParallaxImageHeight = resources.getDimensionPixelSize(R.dimen.parallax_image_height)
    }
    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

    }

    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
        mToolbarView.visibility = View.VISIBLE
        val mBaseColor = getColor(R.color.colorPrimary)
        val mAlpha = Math.min(1f, scrollY.toFloat() / mParallaxImageHeight)
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(mAlpha, mBaseColor))
        AnimatorTabUtils.setTranslationY(mImgFlag, (scrollY / 2).toFloat())
    }

    override fun onDownMotionEvent() {

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        onScrollChanged(mSlContent.getCurrentScrollY(), false, false)
    }
}
