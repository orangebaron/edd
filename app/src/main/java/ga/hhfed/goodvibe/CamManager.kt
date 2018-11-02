import android.Manifest
import android.app.Activity
import android.content.Context
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat.requestPermissions

class CamManager(context: Context) {
    private val manager: CameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private var device: CameraDevice? = null
    fun takePicture(callback: (Message)->Boolean) = device?.createCaptureSession(mutableListOf(),
        object: CameraCaptureSession.StateCallback() {
            override fun onConfigureFailed(session: CameraCaptureSession) { TODO("configure failed") }
            override fun onConfigured(session: CameraCaptureSession) {
                //DO THINGS!
                println("UWE RUUERE $session")
            }
        }, Handler(Handler.Callback(callback)))
    init {
        for (x in manager.cameraIdList)
            println("UWE $x")
        try {
            manager.openCamera("0", {
                println("UWE UWE UWE UWE $it")
                /*TODO device = it as CameraDeviceImpl
                takePicture {
                    println("UWE - --- $it")
                    true
                }*/
            } ,object : CameraDevice.StateCallback() {
                override fun onDisconnected(d: CameraDevice) {
                    TODO("not implemented")
                }
                override fun onError(camera: CameraDevice, error: Int) {
                    TODO("not implemented")
                }
                override fun onOpened(d: CameraDevice) {
                    device = d
                    println("UWE  A A A $device")
                    takePicture {
                        println("UWE - --- $it")
                        true
                    }
                }
            })
            println("UWE  pt 1 done")
        } catch (e: SecurityException) {
            requestPermissions((context as Activity),arrayOf(Manifest.permission.CAMERA),0)
        }
    }
}