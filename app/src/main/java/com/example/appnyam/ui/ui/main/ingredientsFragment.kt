package com.example.appnyam.ui.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnyam.R

class ingredientsFragment : Fragment() {

    companion object {
        fun newInstance() = ingredientsFragment()
    }

    private lateinit var viewModel: IngredientsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IngredientsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}