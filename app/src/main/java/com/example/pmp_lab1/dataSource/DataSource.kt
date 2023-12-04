package com.example.pmp_lab1.dataSource

import com.example.pmp_lab1.R
import com.example.pmp_lab1.model.Planet

class DataSource {
    fun getData(): List<Planet> {
        return listOf(
                Planet("Mercury", R.drawable.mercury,"Milky Way", 58000000.0, 3.7),
                Planet("Venus", R.drawable.venus,"Milky Way", 108200000.0, 8.87),
                Planet("Earth", R.drawable.earth,"Milky Way", 150000000.0, 9.8),
                Planet("Mars", R.drawable.mars,"Milky Way", 228340000.0, 3.71),
                Planet("Jupiter", R.drawable.jupiter,"Milky Way", 778000000.0, 24.79),
                Planet("Saturn", R.drawable.saturn,"Milky Way", 1400000000.0, 10.44),
                Planet("Uranus", R.drawable.uranus,"Milky Way", 2871000000.0, 8.87),
                Planet("Neptune", R.drawable.neptune,"Milky Way", 4472000000.6, 11.4),
        )
    }
}