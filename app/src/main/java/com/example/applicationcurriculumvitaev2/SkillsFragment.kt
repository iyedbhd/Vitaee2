package com.example.applicationcurriculumvitaev2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applicationcurriculumvitaev2.databinding.ActivityMainBinding
import com.example.applicationcurriculumvitaev2.databinding.FragmentSkillsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [SkillsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var android: Int = 0;
    private var ios: Int = 0;
    private var flutter: Int = 0;
    private lateinit var binding: FragmentSkillsBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            android = it.getInt("android")
            ios = it.getInt("ios")
            flutter = it.getInt("flutter")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSkillsBinding.inflate(inflater, container, false);
        binding.seekBarAndroid.setProgress(android);
        binding.seekBarIos.setProgress(ios);
        binding.seekBarFlutter.setProgress(flutter);
        binding.seekBarAndroid.isEnabled = false;
        binding.seekBarIos.isEnabled = false;
        binding.seekBarFlutter.isEnabled = false;

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
        fun newInstance(androidSkill :Int, iosSkill:Int, flutterSkill:Int) =
            SkillsFragment().apply {
                arguments = Bundle().apply {
                    putInt("android", androidSkill)
                    putInt("ios", iosSkill)
                    putInt("flutter", flutterSkill)
                    println("my android skill in new instance is " + androidSkill);

                }
            }
    }
}