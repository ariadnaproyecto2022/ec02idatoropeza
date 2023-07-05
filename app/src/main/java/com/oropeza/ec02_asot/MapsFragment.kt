package com.oropeza.ec02_asot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapsFragment : Fragment() {

    private lateinit var googleMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.fcv_map) as SupportMapFragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment.getMapAsync { map ->
            googleMap = map

            val cineplanet = LatLng(-12.0136563,-77.0737349)
            val cinepolis = LatLng(-11.9851192,-77.0359362)
            val cinemark = LatLng(-12.0541176,-77.0527922)

            googleMap.addMarker(MarkerOptions().position(cineplanet).title("Cineplanet Norte"))
            googleMap.addMarker(MarkerOptions().position(cinepolis).title("Cinemark"))
            googleMap.addMarker(MarkerOptions().position(cinemark).title("Cinépolis-Plaza Norte"))

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cineplanet))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cinepolis))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cinemark))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}