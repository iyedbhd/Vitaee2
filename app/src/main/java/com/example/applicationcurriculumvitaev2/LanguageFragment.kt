package com.example.applicationcurriculumvitaev2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applicationcurriculumvitaev2.databinding.FragmentHobbiesBinding
import com.example.applicationcurriculumvitaev2.databinding.FragmentLanguageBinding
import com.example.applicationcurriculumvitaev2.databinding.FragmentSkillsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [LanguageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LanguageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var langs: String? = null
    private lateinit var binding:FragmentLanguageBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            langs = it.getString("lang")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLanguageBinding.inflate(inflater, container, false);
        binding.checkboxArabic.isChecked = (langs?.indexOf("Arabic") != -1);
        binding.checkboxEng.isChecked = (langs?.indexOf("English") != -1);
        binding.checkboxFr.isChecked = (langs?.indexOf("French") != -1);
        binding.checkboxArabic.isEnabled = false
        binding.checkboxEng.isEnabled = false
        binding.checkboxFr.isEnabled = false

        return binding.root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Hobbies.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(langs: String?) =
            LanguageFragment().apply {
                arguments = Bundle().apply {
                    putString("lang", langs)
                }
            }
    }
}