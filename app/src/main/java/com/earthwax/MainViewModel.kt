package com.earthwax

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val waxRepository: WaxRepository) : ViewModel() {

    val waxes = waxRepository.getAll()

    fun addRandomWax() {
        GlobalScope.launch(context = Dispatchers.IO) {
            waxRepository.add(Wax(title = "${WAX_TITLES.random()} - ${(1..9).random()}"))
        }
    }

    companion object {

        private val WAX_TITLES = listOf(
            "Meguiar's",
            "Chemical Guys",
            "Aero Cosmetics",
            "P21S",
            "Car Guys",
            "Collinite",
            "OPT",
            "Griot's Garage"
        )

    }

}