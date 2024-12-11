package com.example.w13_hw

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                initMap()
            } else {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            initMap()
        }
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Check permissions again (Kotlin requires explicit null/permission checks)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        // Enable my location layer
        googleMap.isMyLocationEnabled = true

        // Taipei 101 marker
        val taipei101 = LatLng(25.033611, 121.565000)
        googleMap.addMarker(
            MarkerOptions()
                .position(taipei101)
                .title("Taipei 101")
                .draggable(true)
        )

        // Taipei Station marker
        val taipeiStation = LatLng(25.047924, 121.517081)
        googleMap.addMarker(
            MarkerOptions()
                .position(taipeiStation)
                .title("Taipei Station")
                .draggable(true)
        )

        // Create polyline
        val polylineOptions = PolylineOptions()
            .add(taipei101)
            .add(LatLng(25.032728, 121.564137))
            .add(taipeiStation)
            .color(Color.BLUE)
            .width(10f)

        googleMap.addPolyline(polylineOptions)

        // Move camera
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(25.034, 121.545),
                13f
            )
        )
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}