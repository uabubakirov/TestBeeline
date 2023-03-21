package com.example.test.presentation.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.test.R
import com.example.test.data.local.sharedpreference.PreferencesHelper
import com.example.test.databinding.ActivityMainBinding
import com.example.test.presentation.utills.ConnectivityObserver
import com.example.test.presentation.utills.NetworkConnectivityObserver
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.places.panorama.PanoramaService.SearchSession
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.Session
import com.yandex.runtime.Error

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var connectivityObserver: NetworkConnectivityObserver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        internetStatus()
        setupNavigation()
        requestPermissions()

    }

    private fun internetStatus() {
        this.lifecycleScope.launch {
            connectivityObserver.observe().collect{
                when(it){
                    ConnectivityObserver.Status.Available -> {
                        binding.intenetStatus.text = AVAILABLE
                    }
                    ConnectivityObserver.Status.Unavailable -> {
                        binding.intenetStatus.text = UNAVAILABLE
                    }
                    ConnectivityObserver.Status.Losing ->{
                        binding.intenetStatus.text = LOSING
                    }
                    ConnectivityObserver.Status.Lost -> {
                        binding.intenetStatus.text = LOST
                    }
                }
            }
        }
    }

    fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSION_ID
        )
    }


    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.toolbar.setupWithNavController(navController)
    }
    private  companion object {
        const val PERMISSION_ID = 100
        const val AVAILABLE = "Available"
        const val UNAVAILABLE = "Unavailable"
        const val LOST = "Lost"
        const val LOSING = "Losing"
    }


}


