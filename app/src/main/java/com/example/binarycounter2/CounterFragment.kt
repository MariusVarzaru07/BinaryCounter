package com.example.binarycounter2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.binarycounter2.databinding.FragmentCounterBinding

class CounterFragment:Fragment() {

    private lateinit var viewModel: CounterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding = DataBindingUtil.inflate<FragmentCounterBinding>(inflater, R.layout.fragment_counter, container, false)

        viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        binding.counterViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.reset.observe(viewLifecycleOwner, Observer {
            if (it){
                viewModel.resetCounter()
            }
        })

        return binding.root
    }

}