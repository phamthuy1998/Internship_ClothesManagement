package com.n16dccn159.admin.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CropOverlayView constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val cropWindowHandler = CropWindowHandler()
    private val drawRect = RectF()
    private var borderPaint: Paint? = null
    private var backgroundPaint: Paint? = null
    private val boundsPoints = FloatArray(8)
    private val calcBounds = RectF()
    private var viewWidth: Int = 0
    private var viewHeight: Int = 0
    private var initialCropWindowPaddingRatio: Float = 0f
    private var touchRadius: Float = 0f
    private var snapRadius: Float = 0f
    private var scaleFactor: Float = 1f
    private var scaleDetector: ScaleGestureDetector? = null
    private var moveHandler: CropWindowMoveHandler? = null
    private var initializedCropWindow: Boolean = false
    private var originalLayerType: Int? = null
    private var targetAspectRatio = 1f
    private var cropWindowChangeListener: CropWindowChangeListener? = null
    val path = Path()
    var fixAspectRatio: Boolean = false

    private var multiTouchEnable: Boolean = true
        set(value) {
            field = value
            scaleDetector = ScaleGestureDetector(context, ScaleListener())
        }

    var cropShape: CropImageView.CropShape? = null
        set(cropShape) {
            if (this.cropShape !== cropShape) {
                field = cropShape
                if (originalLayerType != null) {
                    setLayerType(originalLayerType ?: return, null)
                    originalLayerType = null
                    fixAspectRatio = cropShape == CropImageView.CropShape.OVAL
                }
            }
            invalidate()
        }

    var initialCropWindowRect: Rect? = Rect()
        set(rect) {
            initialCropWindowRect?.set(rect ?: BitmapUtils.EMPTY_RECT)
            if (initializedCropWindow) {
                initCropWindow()
                invalidate()
            }
            field = rect
        }

    private val isNonStraightAngleRotated: Boolean
        get() = boundsPoints[0] != boundsPoints[6] && boundsPoints[1] != boundsPoints[7]

    fun setCropWindowChangeListener(listener: CropWindowChangeListener) {
        cropWindowChangeListener = listener
    }

    fun getCropWindowRect(): RectF {
        return cropWindowHandler.edges
    }

    fun setCropWindowRect(rect: RectF) {
        cropWindowHandler.edges = rect
    }

    fun fixCurrentCropWindowRect() {
        val rect = getCropWindowRect()
        fixCropWindowRectByRules(rect)
        setCropWindowRect(rect)
    }

    fun setBounds(boundsPoints: FloatArray?, viewWidth: Int, viewHeight: Int) {
        if (boundsPoints == null || !Arrays.equals(this.boundsPoints, boundsPoints)) {
            if (boundsPoints == null) {
                Arrays.fill(this.boundsPoints, 0f)
            } else {
                System.arraycopy(boundsPoints, 0, this.boundsPoints, 0, boundsPoints.size)
            }
            this.viewWidth = viewWidth
            this.viewHeight = viewHeight
            val cropRect = cropWindowHandler.edges
            if (cropRect.width() == 0f || cropRect.height() == 0f) {
                initCropWindow()
            }
        }
    }

    fun resetCropOverlayView() {
        if (initializedCropWindow) {
            setCropWindowRect(BitmapUtils.EMPTY_RECT_F)
            initCropWindow()
            invalidate()
        }
    }

    fun setCropWindowLimits(
        maxWidth: Float,
        maxHeight: Float,
        scaleFactorWidth: Float,
        scaleFactorHeight: Float
    ) {
        cropWindowHandler.setCropWindowLimits(
            maxWidth, maxHeight, scaleFactorWidth, scaleFactorHeight
        )
    }

    fun resetCropWindowRect() {
        if (initializedCropWindow) {
            initCropWindow()
            invalidate()
            callOnCropWindowChanged(false)
        }
    }

    fun setInitialAttributeValues(options: CropImageOptions) {
        cropWindowHandler.setInitialAttributeValues(options)
        cropShape = options.cropShape
        snapRadius = options.snapRadius
        touchRadius = options.touchRadius
        multiTouchEnable = options.multiTouchEnabled
        initialCropWindowPaddingRatio = options.initialCropWindowPaddingRatio
        borderPaint = getNewPaintOrNull(options.borderLineThickness, options.borderLineColor)
        backgroundPaint = getNewPaint(options.backgroundColor)
    }

    private fun initCropWindow() {
        fixAspectRatio = cropShape == CropImageView.CropShape.OVAL
        val leftLimit = max(BitmapUtils.getRectLeft(boundsPoints), 0f)
        val topLimit = max(BitmapUtils.getRectTop(boundsPoints), 0f)
        val rightLimit = min(BitmapUtils.getRectRight(boundsPoints), width.toFloat())
        val bottomLimit = min(BitmapUtils.getRectBottom(boundsPoints), height.toFloat())

        if (rightLimit <= leftLimit || bottomLimit <= topLimit) {
            return
        }

        val rect = RectF()

        initializedCropWindow = true

        if (initialCropWindowRect?.width() ?: 0 > 0 && initialCropWindowRect?.height() ?: 0 > 0) {
            rect.left = leftLimit + (initialCropWindowRect?.left ?: 0) / cropWindowHandler.scaleFactorWidth
            rect.top = topLimit + (initialCropWindowRect?.top ?: 0) / cropWindowHandler.scaleFactorHeight
            rect.right = rect.left + (initialCropWindowRect?.width() ?: 0) / cropWindowHandler.scaleFactorWidth
            rect.bottom = rect.top + (initialCropWindowRect?.height() ?: 0) / cropWindowHandler.scaleFactorHeight
            rect.left = max(leftLimit, rect.left)
            rect.top = max(topLimit, rect.top)
            rect.right = min(rightLimit, rect.right)
            rect.bottom = min(bottomLimit, rect.bottom)
        } else {
            rect.left = leftLimit
            rect.top = topLimit
            rect.right = rightLimit
            rect.bottom = bottomLimit
        }

        fixCropWindowRectByRules(rect)
        setCropWindowRect(rect)
    }

    private fun callOnCropWindowChanged(inProgress: Boolean) {
        cropWindowChangeListener?.onCropWindowChanged(inProgress)
    }

    private fun fixCropWindowRectByRules(rect: RectF) {
        if (rect.width() < cropWindowHandler.getMinCropWidth()) {
            val adj = (cropWindowHandler.getMinCropWidth() - rect.width()) / 2
            rect.left -= adj
            rect.right += adj
        }
        if (rect.height() < cropWindowHandler.getMinCropHeight()) {
            val adj = (cropWindowHandler.getMinCropHeight() - rect.height()) / 2
            rect.top -= adj
            rect.bottom += adj
        }
        if (rect.width() > cropWindowHandler.getMaxCropWidth()) {
            val adj = (rect.width() - cropWindowHandler.getMaxCropWidth()) / 2
            rect.left += adj
            rect.right -= adj
        }
        if (rect.height() > cropWindowHandler.getMaxCropHeight()) {
            val adj = (rect.height() - cropWindowHandler.getMaxCropHeight()) / 2
            rect.top += adj
            rect.bottom -= adj
        }

        calculateBounds(rect)
        if (calcBounds.width() > 0 && calcBounds.height() > 0) {
            val leftLimit = max(calcBounds.left, 0f)
            val topLimit = max(calcBounds.top, 0f)
            val rightLimit = min(calcBounds.right, width.toFloat())
            val bottomLimit = min(calcBounds.bottom, height.toFloat())
            if (rect.left < leftLimit)
                rect.left = leftLimit

            if (rect.top < topLimit)
                rect.top = topLimit

            if (rect.right > rightLimit)
                rect.right = rightLimit

            if (rect.bottom > bottomLimit)
                rect.bottom = bottomLimit
        }

        if (fixAspectRatio && abs(rect.width() - rect.height() * targetAspectRatio) > 0.1) {
            if (rect.width() > rect.height() * targetAspectRatio) {
                val adj = abs(rect.height() * targetAspectRatio - rect.width()) / 2
                rect.left += adj
                rect.right -= adj
            } else {
                val adj = abs(rect.width() / targetAspectRatio - rect.height()) / 2
                rect.top += adj
                rect.bottom -= adj
            }
        }
    }

    /**
     * Draw crop overview by drawing background over image not in the cropping area and borders
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawBorders(canvas)
    }

    /** Draw shadow background over the image not including the crop area.  */
    private fun drawBackground(canvas: Canvas) {

        val rect = cropWindowHandler.getRect()

        val left = max(BitmapUtils.getRectLeft(boundsPoints), 0f)
        val top = max(BitmapUtils.getRectTop(boundsPoints), 0f)
        val right = min(BitmapUtils.getRectRight(boundsPoints), width.toFloat())
        val bottom = min(BitmapUtils.getRectBottom(boundsPoints), height.toFloat())

        if (cropShape == CropImageView.CropShape.RECTANGLE) {
            if (!isNonStraightAngleRotated) {
                canvas.drawRect(left, top, right, rect.top, backgroundPaint!!)
                canvas.drawRect(left, rect.bottom, right, bottom, backgroundPaint!!)
                canvas.drawRect(left, rect.top, rect.left, rect.bottom, backgroundPaint!!)
                canvas.drawRect(rect.right, rect.top, right, rect.bottom, backgroundPaint!!)
            } else {
                path.reset()
                path.moveTo(boundsPoints[0], boundsPoints[1])
                path.lineTo(boundsPoints[2], boundsPoints[3])
                path.lineTo(boundsPoints[4], boundsPoints[5])
                path.lineTo(boundsPoints[6], boundsPoints[7])
                path.close()

                canvas.save()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    canvas.clipOutPath(path)
                } else {
                    canvas.clipPath(path, Region.Op.INTERSECT)
                }
                canvas.clipRect(rect, Region.Op.XOR)
                canvas.drawRect(left, top, right, bottom, backgroundPaint ?: return)
                canvas.restore()
            }
        } else {
            path.reset()

            if (cropShape === CropImageView.CropShape.OVAL)
                drawRect.set(rect.left + 1, rect.top + 1, rect.right - 1, rect.bottom - 1)
            else
                drawRect.set(rect.left, rect.top, rect.right, rect.bottom)

            path.addOval(drawRect, Path.Direction.CW)
            canvas.save()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                canvas.clipOutPath(path)
            else
                canvas.clipPath(path, Region.Op.XOR)

            canvas.drawRect(left, top, right, bottom, backgroundPaint ?: return)
            canvas.restore()
        }
    }

    /** Draw borders of the crop area.  */
    private fun drawBorders(canvas: Canvas) {
        val w = borderPaint?.strokeWidth ?: 0f
        val rect = cropWindowHandler.getRect()
        rect.inset(w / 2, w / 2)

        if (cropShape === CropImageView.CropShape.RECTANGLE)
            canvas.drawRect(rect, borderPaint ?: return)
        else
            canvas.drawOval(rect, borderPaint ?: return)
    }

    /** Creates the Paint object for drawing.  */
    private fun getNewPaint(color: Int): Paint {
        val paint = Paint()
        paint.color = color
        return paint
    }

    /** Creates the Paint object for given thickness and color, if thickness < 0 return null.  */
    private fun getNewPaintOrNull(thickness: Float, color: Int): Paint? {
        return if (thickness > 0) {
            val borderPaint = Paint()
            borderPaint.color = color
            borderPaint.strokeWidth = thickness
            borderPaint.style = Paint.Style.STROKE
            borderPaint.isAntiAlias = true
            borderPaint
        } else {
            null
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (multiTouchEnable)
            scaleDetector?.onTouchEvent(event)

        return if (isEnabled) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    onActionDown(event.x, event.y)
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    parent.requestDisallowInterceptTouchEvent(false)
                    onActionUp()
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    onActionMove(event.x, event.y)
                    parent.requestDisallowInterceptTouchEvent(true)
                    true
                }
                else -> false
            }
        } else {
            false
        }
    }

    /**
     * On press down start crop window movement depending on the location of the press.<br></br>
     * if press is far from crop window then no move handler is returned (null).
     */
    private fun onActionDown(x: Float, y: Float) {
        moveHandler =
            cropWindowHandler.getMoveHandler(x, y, touchRadius, cropShape ?: CropImageView.CropShape.RECTANGLE)

        if (moveHandler != null)
            invalidate()
    }

    private fun onActionUp() {
        if (moveHandler != null) {
            moveHandler = null
            invalidate()
            callOnCropWindowChanged(false)
        }
    }

    private fun onActionMove(x: Float, y: Float) {
        var snapRadius = this.snapRadius
        val rect = getCropWindowRect()

        if (calculateBounds(rect))
            snapRadius = 0f

        moveHandler?.move(
            rect,
            x,
            y,
            calcBounds,
            viewWidth,
            viewHeight,
            snapRadius,
            cropShape == CropImageView.CropShape.OVAL,
            targetAspectRatio
        )
        setCropWindowRect(rect)
        callOnCropWindowChanged(true)
        invalidate()
    }

    private fun calculateBounds(rect: RectF): Boolean {
        var left = BitmapUtils.getRectLeft(boundsPoints)
        var top = BitmapUtils.getRectTop(boundsPoints)
        var right = BitmapUtils.getRectRight(boundsPoints)
        var bottom = BitmapUtils.getRectBottom(boundsPoints)

        if (!isNonStraightAngleRotated) {
            calcBounds.set(left, top, right, bottom)
            return false
        } else {
            var x0 = boundsPoints[0]
            var y0 = boundsPoints[1]
            var x2 = boundsPoints[4]
            var y2 = boundsPoints[5]
            var x3 = boundsPoints[6]
            var y3 = boundsPoints[7]

            if (boundsPoints[7] < boundsPoints[1]) {
                if (boundsPoints[1] < boundsPoints[3]) {
                    x0 = boundsPoints[6]
                    y0 = boundsPoints[7]
                    x2 = boundsPoints[2]
                    y2 = boundsPoints[3]
                    x3 = boundsPoints[4]
                    y3 = boundsPoints[5]
                } else {
                    x0 = boundsPoints[4]
                    y0 = boundsPoints[5]
                    x2 = boundsPoints[0]
                    y2 = boundsPoints[1]
                    x3 = boundsPoints[2]
                    y3 = boundsPoints[3]
                }
            } else if (boundsPoints[1] > boundsPoints[3]) {
                x0 = boundsPoints[2]
                y0 = boundsPoints[3]
                x2 = boundsPoints[6]
                y2 = boundsPoints[7]
                x3 = boundsPoints[0]
                y3 = boundsPoints[1]
            }

            val a0 = (y3 - y0) / (x3 - x0)
            val a1 = -1f / a0
            val b0 = y0 - a0 * x0
            val b1 = y0 - a1 * x0
            val b2 = y2 - a0 * x2
            val b3 = y2 - a1 * x2

            val c0 = (rect.centerY() - rect.top) / (rect.centerX() - rect.left)
            val c1 = -c0
            val d0 = rect.top - c0 * rect.left
            val d1 = rect.top - c1 * rect.right

            left = max(left, if ((d0 - b0) / (a0 - c0) < rect.right) (d0 - b0) / (a0 - c0) else left)
            left = max(left, if ((d0 - b1) / (a1 - c0) < rect.right) (d0 - b1) / (a1 - c0) else left)
            left = max(left, if ((d1 - b3) / (a1 - c1) < rect.right) (d1 - b3) / (a1 - c1) else left)
            right = min(right, if ((d1 - b1) / (a1 - c1) > rect.left) (d1 - b1) / (a1 - c1) else right)
            right = min(right, if ((d1 - b2) / (a0 - c1) > rect.left) (d1 - b2) / (a0 - c1) else right)
            right = min(right, if ((d0 - b2) / (a0 - c0) > rect.left) (d0 - b2) / (a0 - c0) else right)

            top = max(top, max(a0 * left + b0, a1 * right + b1))
            bottom = min(bottom, min(a1 * left + b3, a0 * right + b2))

            calcBounds.left = left
            calcBounds.top = top
            calcBounds.right = right
            calcBounds.bottom = bottom
            return true
        }
    }

    /** Handle scaling the rectangle based on two finger input  */
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = max(1f, min(scaleFactor, 4f))
            cropWindowChangeListener?.onZoom(scaleFactor)
            return true
        }
    }

    interface CropWindowChangeListener {
        fun onCropWindowChanged(inProgress: Boolean)
        fun onZoom(scaleFactor: Float)
    }
}
