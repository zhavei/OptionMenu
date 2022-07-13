package com.syafei.optionmenu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syafei.optionmenu.R
import com.syafei.optionmenu.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding = requireNotNull(_binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** with only one fragment **/
        val tvLabel = binding.sectionLabel
        val tvFrActvity = binding.tvMenuFragmen

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        tvLabel.text = getString(R.string.content_tab_text, index)

        /**send data from activity to fragment**/
        val name = arguments?.getString(ARG_NAME)
        tvFrActvity.text = name

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_NAME = "app_name"
    }

}