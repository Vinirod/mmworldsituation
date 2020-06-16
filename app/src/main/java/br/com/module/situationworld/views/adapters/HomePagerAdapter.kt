package br.com.module.situationworld.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.module.situationworld.views.fragments.FragmentFiltering
import br.com.module.situationworld.views.fragments.FragmentWorld

const val FRAGMENT_WORLD = 0
const val FRAGMENT_FILTERING = 1

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        FRAGMENT_WORLD to { FragmentWorld() },
        FRAGMENT_FILTERING to { FragmentFiltering() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}