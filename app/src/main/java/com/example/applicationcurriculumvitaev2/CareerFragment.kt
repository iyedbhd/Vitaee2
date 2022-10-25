package com.example.applicationcurriculumvitaev2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationcurriculumvitaev2.databinding.ActivityMainBinding
import com.example.applicationcurriculumvitaev2.databinding.FragmentCareerBinding
import com.example.applicationcurriculumvitaev2.databinding.FragmentSkillsBinding
import tn.esprit.loldatastorage.utils.AppDataBase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [CareerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CareerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentCareerBinding;
    lateinit var dataBase : AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentCareerBinding.inflate(inflater, container, false);
        binding.recyclerview.layoutManager = LinearLayoutManager(context);
        dataBase = AppDataBase.getDatabase(binding.recyclerview.context)
        val adapter = CustomAdapter(dataBase.expDAO().getAll())
        binding.recyclerview.adapter = adapter

        return binding.root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SkillsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CareerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}