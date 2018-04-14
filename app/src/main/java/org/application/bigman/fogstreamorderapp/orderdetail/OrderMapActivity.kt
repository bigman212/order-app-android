package org.application.bigman.fogstreamorderapp.orderdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.application.bigman.fogstreamorderapp.R

class OrderMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var endCoords: Pair<Double, Double>
    private lateinit var startCoords: Pair<Double, Double>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_map)

        startCoords = Pair(intent.getDoubleExtra("endLat", 0.0),
                intent.getDoubleExtra("endLng", 0.0))

        endCoords = Pair(intent.getDoubleExtra("startLat", 0.0),
                intent.getDoubleExtra("startLng", 0.0))

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val fromLocation = LatLng(startCoords.first, startCoords.second)
        val toLocation = LatLng(endCoords.first, endCoords.second)
        mMap.addMarker(MarkerOptions().position(fromLocation).title("Откуда"))
        mMap.addMarker(MarkerOptions().position(toLocation).title("Куда"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromLocation, 14.0F))

    }
}
