package com.example.test.presentation.fragments.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.postDelayed
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test.R
import com.example.test.databinding.FragmentMapBinding
import com.example.test.presentation.activity.MainActivity
import com.example.test.presentation.adapters.MapAdapter
import com.example.test.presentation.entities.MapUIEntity
import com.example.test.presentation.entities.RecyclerModel
import com.example.test.presentation.entities.mapToUI
import com.example.test.presentation.fragments.BaseFragment
import com.example.test.presentation.utills.UIState
import com.example.test.presentation.utills.showToastLong
import com.example.test.presentation.utills.showToastShort
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.search.*
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.runtime.Error
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : BaseFragment<MapViewModel,FragmentMapBinding>(R.layout.fragment_map){

    override val binding: FragmentMapBinding by viewBinding(FragmentMapBinding::bind)
    override val viewModel: MapViewModel by viewModels()
    private lateinit var userLocation: UserLocationLayer
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var searchManager: SearchManager
    private lateinit var searchSession:Session
    private lateinit var search: Session.SearchListener
    private lateinit var adapter: MapAdapter
    private var mapUIEntity = MapUIEntity("")



    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.initialize(requireContext())
        SearchFactory.initialize(requireContext());
        super.onCreate(savedInstanceState)

    }

    override fun initialize() {
        adapter = MapAdapter(this::clickPosition)
        binding.rvMap.adapter = adapter
        userLocation = MapKitFactory.getInstance().createUserLocationLayer(binding.map.mapWindow)
        searchManager =  SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)
        fusedLocationProviderClient = LocationServices
            .getFusedLocationProviderClient(requireContext())
        findMe()

    }
    override fun setupListeners() {
        clickFindMe()
        editText()
        responseCallback()
        cleanButtonClick()
    }

    private fun cleanButtonClick() {
        binding.clearRv.setOnClickListener {
            binding.rvMap.visibility = View.GONE
        }
    }


    private fun findMe() {
        if (checkPermission()){
            userLocation.isVisible = true
            find()
        }

    }

    @SuppressLint("MissingPermission")
    private fun find(){
        fusedLocationProviderClient.lastLocation.addOnCompleteListener {
            val point = Point(it.result.latitude,it.result.longitude)
            moveCamera(point)
        }
    }

    override fun setupSubscribes() {
        viewModel.getData()
        viewModel.mapState.observe(viewLifecycleOwner){
            when(it){
                is UIState.Empty -> {}
                is UIState.Error -> {}
                is UIState.Loading -> {}
                is UIState.Success -> {
                    showToastLong("$SEARCH ${it.data.location}")
                    mapUIEntity.location = it.data.location
                }
            }
        }
    }



    private fun responseCallback() {
        var list: List<RecyclerModel?>
        search = object : Session.SearchListener {
            override fun onSearchResponse(response: Response) {
                list = response.collection.children.take(7).map {
                    it.obj?.mapToUI(it?.obj?.name, it?.obj?.geometry?.get(0)?.point)
                }
                adapter.submitList(list)
            }
            override fun onSearchError(p0: Error) {
                showToastShort("Ошибка")
            }
        }
    }

    fun clickPosition(model: RecyclerModel) {
        mapUIEntity.location = model.name
        model.point?.let { moveCamera(it) }
    }

    private fun editText() {
        binding.search.addTextChangedListener {
            handler.postDelayed(1000L){
                submitQuery(it.toString())
                binding.rvMap.visibility = View.VISIBLE
            }
        }
    }

    private fun clickFindMe() {
        binding.nearme.setOnClickListener {
            if (checkPermission()) {
                find()
            } else {
                (activity as MainActivity).requestPermissions()
            }
        }
    }

    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }


    private fun moveCamera(point: Point) {
        binding.map.map.move(CameraPosition(point, 16F, 0F, 0F)
        )
    }

    fun submitQuery(query: String) {
        searchSession = searchManager.submit(
            query,
            VisibleRegionUtils.toPolygon(binding.map.map.visibleRegion),
            SearchOptions(),
            search
        )

    }

    override fun onStop() {
        viewModel.insertData(mapUIEntity)
        binding.map.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.map.onStart()
    }


companion object{
    const val SEARCH = "Последний поиск"
}
}

