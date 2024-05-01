package mobile.japancv.jcvfacesdk.sample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView
import mobile.japancv.jcvfacesdk.model.FaceInfo
import kotlin.properties.Delegates

/**
 * Note: This is for the demonstration purpose
 * Based on ImageView to draw the face bounding box on the image
 */
@SuppressLint("AppCompatCustomView")
class FaceImageView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ImageView(context, attrs, defStyleAttr, defStyleRes) {

    private fun toRect(faceInfo: FaceInfo): RectF = RectF(
        faceInfo.x1 * width,
        faceInfo.y1 * height,
        faceInfo.x2 * width,
        faceInfo.y2 * height,
    )

    init {
        addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            rects = faceInfo?.map(::toRect)
            landmarks = faceInfo?.map {
                it.landmarks.map { mark ->
                    mark.first * width to mark.second * height
                }
            }
        }
    }

    var faceInfo: Array<FaceInfo>? by Delegates.observable(null) { _, _, new ->
        rects = new?.map(::toRect)
        landmarks = new?.map {
            it.landmarks.map { mark ->
                mark.first * width to mark.second * height
            }
        }
    }

    private var rects: List<RectF>? = null
    private var landmarks: List<List<Pair<Float, Float>>>? = null

    private val paint = Paint().apply {
        color = Color.RED
        strokeWidth = 2F
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rects?.forEach { rect ->
            canvas.drawRect(rect, paint)
        }
        landmarks?.forEach {
            it.forEachIndexed { index, mark ->
                if (index <= 32) { // Face contour
                    paint.color = Color.BLUE
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 37) { // Left eyebrow
                    paint.color = Color.CYAN
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 42) { // Right eyebrow
                    paint.color = Color.GREEN
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 51) { // Nose
                    paint.color = Color.MAGENTA
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 57) { // Left eye
                    paint.color = Color.WHITE
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 63) { // Right eye
                    paint.color = Color.YELLOW
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 67) { // Left eyebrow
                    paint.color = Color.CYAN
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 71) { // Right eyebrow
                    paint.color = Color.GREEN
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 74) { // Left eye
                    paint.color = Color.WHITE
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 77) { // Right eye
                    paint.color = Color.YELLOW
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 79) { //
                    paint.color = Color.RED
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 83) { // Nose
                    paint.color = Color.MAGENTA
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                } else if (index <= 103) { // Mouth
                    paint.color = Color.BLUE
                    canvas.drawCircle(mark.first, mark.second, 1f, paint)
                }
            }
        }
    }
}
