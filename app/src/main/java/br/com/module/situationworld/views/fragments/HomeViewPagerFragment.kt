package br.com.module.situationworld.views.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.module.situationworld.R
import br.com.module.situationworld.databinding.FragmentViewPagerBinding
import br.com.module.situationworld.view.adapters.FRAGMENT_FILTERING
import br.com.module.situationworld.view.adapters.FRAGMENT_WORLD
import br.com.module.situationworld.view.adapters.HomePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import io.github.yavski.fabspeeddial.FabSpeedDial
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter
import java.net.URLEncoder

class HomeViewPagerFragment : Fragment() {

    private lateinit var mFabSpeedDial: FabSpeedDial

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = HomePagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()



        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFabSpeedDial = view.findViewById(R.id.idFab)

        mFabSpeedDial.setMenuListener(object : SimpleMenuListenerAdapter(){
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.action_whatsapp){
                    val packageManager = context!!.packageManager
                    val i = Intent(Intent.ACTION_VIEW)
                    try {
                        val url = "https://api.whatsapp.com/send?phone=" + "+553193613159" + "&text=" + URLEncoder.encode("Hey! I have some questions about the application and its services.", "UTF-8")
                        i.setPackage("com.whatsapp")
                        i.data = Uri.parse(url)
                        if (i.resolveActivity(packageManager!!) != null) {
                            startActivity(i)
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
                if(menuItem.itemId == R.id.action_share){
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! I would like to introduce you to the MM WorldSituation application where you can access data and graphs about the world: https://play.google.com/store/apps/details?id=br.com.module.situationworld")
                    sendIntent.type = "text/plain"
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
                return false
            }
        })
    }
    private fun getTabIcon(position: Int): Int {
        return when (position) {
            FRAGMENT_WORLD -> R.drawable.ic_world
            FRAGMENT_FILTERING -> R.drawable.ic_filtering
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            FRAGMENT_WORLD -> getString(R.string.world)
            FRAGMENT_FILTERING -> getString(R.string.filtering)
            else -> null
        }
    }
}