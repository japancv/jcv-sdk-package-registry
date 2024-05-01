package mobile.japancv.jcvfacesdk.sample

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mobile.japancv.jcvfacesdk.DualImageAgent
import mobile.japancv.jcvfacesdk.InternalFaceSdkApi
import mobile.japancv.jcvfacesdk.internal.BitmapImageImpl
import mobile.japancv.jcvfacesdk.model.FaceInfo
import mobile.japancv.jcvfacesdk.toImageAgent
import mobile.japancv.sdk.fa.FaceAttributes
import mobile.japancv.sdk.fd.FaceDetection
import mobile.japancv.sdk.fl.FaceLiveness
import mobile.japancv.sdk.fp.FacePose

@OptIn(InternalFaceSdkApi::class)
class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Quick Start
         * How to use JCV SDK - Face Detection
         */
        scope.launch {
            // 1. Get the Face Detection instance
            val fd = FaceDetection.getInstance()
            // 2. Load your image as [android.graphics.Bitmap]
            val bitmap = BitmapFactory.decodeStream(assets.open("vic.jpg"))
            // 3. Initiate ImageAgent
            val imageAgent = bitmap.toImageAgent()
            // 4. Predict the face
            val info = fd.predict(imageAgent)
            // 5. Result
            info.forEach {
                Log.i("Sample", "${it.trackId}")
            }
        }

        /**
         * Demo
         * How you can detect Face on an image and
         * extract the additional information such like Gender, Age, and Liveness
         */
        scope.launch {
            showDemoFaceDetection(
                fd = FaceDetection.getInstance {
                    faceMinWidth = 100
                    faceMinHeight = 100
                },
                fa = FaceAttributes.getInstance(),
                fp = FacePose.getInstance(),
                fl = FaceLiveness.getInstance(),
            )
        }
    }

    /**
     * In this method, it demonstrates the result of drawing the face bounding box on the image
     */
    private suspend fun showDemoFaceDetection(
        fd: FaceDetection,
        fa: FaceAttributes,
        fp: FacePose,
        fl: FaceLiveness
    ) {
        suspend fun FaceImageView.applyFaceImage(assetFileName: String): Array<FaceInfo> {
            val bitmap = BitmapFactory.decodeStream(assets.open(assetFileName))
            setImageBitmap(bitmap)
            val imageAgent = bitmap.toImageAgent()
            val info = fd.predict(imageAgent)
            val attributes = fa.predict(imageAgent, info)
            val liveness = fl.predict(imageAgent, info)
            val binocularLiveness = fl.predict(DualImageAgent(imageAgent, imageAgent), info)

            Log.i("Sample", "Attributes: ${attributes.joinToString(",")}")
            Log.i("Sample", "Liveness: ${liveness.joinToString(",")}")
            Log.i("Sample", "BinocularLiveness: ${binocularLiveness.joinToString(",")}")
            faceInfo = info
            invalidate()

            return info
        }

        findViewById<FaceImageView>(R.id.img_face1)?.applyFaceImage("vic.jpg")?.let {infos ->
            infos.forEach { info ->
                (info.face as? BitmapImageImpl)?.bitmap?.let {
                    val iv = findViewById<ImageView?>(R.id.crop_face1)
                    iv?.setImageBitmap(it)
                }
            }

        }
        findViewById<FaceImageView>(R.id.img_face2)?.applyFaceImage("letVic.jpg")?.let {infos ->
            infos.forEach { info ->
                (info.face as? BitmapImageImpl)?.bitmap?.let {
                    val iv = findViewById<ImageView?>(R.id.crop_face2)
                    iv?.setImageBitmap(it)
                }
            }
        }
        fd.setVerifyOption {
            faceMaxWidth = 500
            faceMaxHeight = 1000
        }
        findViewById<FaceImageView>(R.id.img_face3)?.applyFaceImage("test_blazeface.jpg")?.let {infos ->
            infos.forEach { info ->
                (info.face as? BitmapImageImpl)?.bitmap?.let {
                    val iv = findViewById<ImageView?>(R.id.crop_face3)
                    iv?.setImageBitmap(it)
                }
            }
        }
    }
}
