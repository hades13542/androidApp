package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceHolder.Callback
import android.view.SurfaceView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_qr_code.*


class QrCodeActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.VIBRATE), 1)
        setContentView(R.layout.activity_qr_code)
        val surfaceView = findViewById<SurfaceView>(R.id.cameraPreview)
        val textView = findViewById<TextView>(R.id.textView)

        val barcodeDetector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build()
        val cameraSource =
            CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480).build()

        surfaceView.holder.addCallback(object : Callback {
            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                cameraSource.stop()
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Log.d("SURFACE", "Surface Created and pemission granted")
                    cameraSource.start(holder)
                }
            }
        })

        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
                val qrCodes = detections?.detectedItems
                if(qrCodes!!.isNotEmpty()) {
                    textView.post {
                        val vibrator =
                            applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
                        textView.setText(qrCodes.valueAt(0).displayValue)
                        DataContainer.qrCodes.add(qrCodes.valueAt(0).displayValue)
                        finish()
                    }
                }

            }
        })
//        val message = intent.getStringExtra(EXTRA_MESSAGE)
//        val textView = findViewById<TextView>(R.id.textView).apply { text = message }
    }


}
