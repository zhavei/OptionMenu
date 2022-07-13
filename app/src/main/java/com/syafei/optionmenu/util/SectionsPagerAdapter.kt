package com.syafei.optionmenu.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.syafei.optionmenu.fragment.MenuFragment

/** this own to view pager **/
class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    //send data from activity to fragment
    var appName: String = ""

    override fun createFragment(position: Int): Fragment {
        //with many different fragment
        /*var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = MenuFragment()
        }
        return fragment as Fragment*/


        //with one layout fragment
        val fragment = MenuFragment()
        fragment.arguments = Bundle().apply {
            putInt(MenuFragment.ARG_SECTION_NUMBER, position + 1)
            putString(MenuFragment.ARG_NAME, appName)
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return 3

    }

}