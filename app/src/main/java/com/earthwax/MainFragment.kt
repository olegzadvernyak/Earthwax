package com.earthwax

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private val viewModel by inject<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val waxAdapter = WaxAdapter()
        with(waxesRecyclerView) {
            adapter = waxAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.waxes.observe(this, Observer { waxes ->
            waxAdapter.setWaxes(waxes)
        })
        addWaxButton.setOnClickListener {
            viewModel.addRandomWax()
        }
    }

}