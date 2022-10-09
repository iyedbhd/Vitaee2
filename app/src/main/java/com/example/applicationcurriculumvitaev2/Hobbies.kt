package com.example.applicationcurriculumvitaev2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applicationcurriculumvitaev2.databinding.FragmentHobbiesBinding
import com.example.applicationcurriculumvitaev2.databinding.FragmentSkillsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [Hobbies.newInstance] factory method to
 * create an instance of this fragment.
 */
class Hobbies : Fragment() {
    // TODO: Rename and change types of parameters
    private var hobbies: String? = null
    private lateinit var binding: FragmentHobbiesBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hobbies = it.getString("hbs")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHobbiesBinding.inflate(inflater, container, false);
        binding.checkBoxMusic.isChecked = (hobbies?.indexOf("Music") != -1);
        binding.checkBoxSport.isChecked = (hobbies?.indexOf("Sport") != -1);
        binding.checkBoxGame.isChecked = (hobbies?.indexOf("Games") != -1);
        binding.checkBoxMusic.isEnabled = false
        binding.checkBoxSport.isEnabled = false
        binding.checkBoxGame.isEnabled = false
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
        fun newInstance(hobbies: String?) =
            Hobbies().apply {
                arguments = Bundle().apply {
                    putString("hbs", hobbies)
                }
            }
    }
}