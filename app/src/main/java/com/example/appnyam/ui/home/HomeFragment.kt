package com.example.appnyam.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appnyam.R
import com.example.appnyam.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        binding.btnExit.setOnClickListener {
            Toast.makeText(this.requireContext(), "exiting the aplication", Toast.LENGTH_SHORT)
                .show()
            System.exit(0)
        }
        binding.btnOrders.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_ordersFragment)
        }


        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}