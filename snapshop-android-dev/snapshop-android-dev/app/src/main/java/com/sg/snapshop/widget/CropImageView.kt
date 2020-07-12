package com.sg.snapshop.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.RectF
import android.net.Uri
import android.os.AsyncTask
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.sg.snapshop.R
import com.sg.snapshop.util.DisplayUtil
import java.lang.ref.WeakReference
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class CropImageView(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private val imageView: ImageView?
    private val cropOverlayView: CropOverlayView?

    /** The matrix used to transform the cropping image in the image view  */
    private var imageMatrix = Matrix()

    /** Reusing matrix instance for reverse matrix calculations.  */
    private val imageInverseMatrix = Matrix()

    /** Rectangle used in image matrix transformation calculation (reusing rect instance)  */
    private val imagePoints = FloatArray(8)

    /** Rectangle used in image matrix transformation for scale calculation (reusing rect instance)  */
    private val scaleImagePoints = FloatArray(8)

    private var bitmap: Bitmap? = null

    /** How much the image is rotated from original clockwise  */
    private var degreesRotated: Int = 0
    private var layoutWidth: Int = 0
    private var layoutHeight: Int = 0
    private var onEditImageCompleteListener: OnEditImageCompleteListener? = null
    private var imageUri: Uri? = null
    private var animation: CropImageAnimation? = null

    /** The sample size the image was loaded by if was loaded by URI  */
    private var loadedSampleSize = 1

    /** The current zoom level to to scale the cropping image  */
    private var zoom = 1f

    /** The X offset that the cropping image was translated after zooming  */
    private var zoomOffsetX: Float = 0f

    /** The Y offset that the cropping image was translated after zooming  */
    private var zoomOffsetY: Float = 0f

    // Used to detect size change to handle auto-zoom using [.handleCropWindowChanged] in [.layout].
    private var sizeChanged: Boolean = false

    /** Task used to crop bitmap async from UI thread  */
    private var bitmapCroppingWorkerTask: WeakReference<BitmapCroppingWorkerTask>? = null

    var scaleType: ScaleType? = ScaleType.CENTER_INSIDE
        set(scaleType) {
            field = scaleType
            zoom = 1f
            zoomOffsetY = 0f
            zoomOffsetX = zoomOffsetY
            cropOverlayView?.resetCropOverlayView()
            requestLayout()
        }

    private var cropShape: CropShape? = CropShape.RECTANGLE
        get() = cropOverlayView?.cropShape
        set(cropShape) {
            field = cropShape
            cropOverlayView?.cropShape = cropShape ?: CropShape.RECTANGLE
        }

    private var isAutoZoomEnabled: Boolean = true
        set(autoZoomEnabled) {
            field = autoZoomEnabled
            handleCropWindowChanged(false)
            cropOverlayView?.invalidate()
        }

    private var maxZoom: Int = 4
        set(maxZoom) {
            if (field != maxZoom && maxZoom > 0) {
                field = maxZoom
                handleCropWindowChanged(false)
                cropOverlayView?.invalidate()
            }
        }

    var rotatedDegrees: Int = 0
        set(degrees) {
            field = degrees
            rotateImage(degrees)
        }

    var isShowCropOverlay: Boolean = true
        set(showCropOverlay) {
            field = showCropOverlay
            setCropOverlayVisibility()
        }

    private val cropPoints: FloatArray
        get() {
            val cropWindowRect = cropOverlayView?.getCropWindowRect() ?: RectF()
            val points = floatArrayOf(
                cropWindowRect.left,
                cropWindowRect.top,
                cropWindowRect.right,
                cropWindowRect.top,
                cropWindowRect.right,
                cropWindowRect.bottom,
                cropWindowRect.left,
                cropWindowRect.bottom
            )

            imageMatrix.invert(imageInverseMatrix)
            imageInverseMatrix.mapPoints(points)

            for (i in points.indices)
                points[i] *= loadedSampleSize.toFloat()

            return points
        }

    init {

        val options = CropImageOptions()

        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.CropImageView, 0, 0)
            try {
                options.fixAspectRatio =
                    ta.getBoolean(R.styleable.CropImageView_cropFixAspectRatio, options.fixAspectRatio)

                options.aspectRatioX =
                    ta.getInteger(R.styleable.CropImageView_cropAspectRatioX, options.aspectRatioX)

                options.aspectRatioY =
                    ta.getInteger(R.styleable.CropImageView_cropAspectRatioY, options.aspectRatioY)

                options.scaleType =
                    ScaleType.values()[ta.getInt(
                        R.styleable.CropImageView_cropScaleType,
                        options.scaleType.ordinal
                    )]

                options.autoZoomEnabled =
                    ta.getBoolean(
                        R.styleable.CropImageView_cropAutoZoomEnabled,
                        options.autoZoomEnabled
                    )

                options.multiTouchEnabled =
                    ta.getBoolean(
                        R.styleable.CropImageView_cropMultiTouchEnabled,
                        options.multiTouchEnabled
                    )

                options.maxZoom = ta.getInteger(R.styleable.CropImageView_cropMaxZoom, options.maxZoom)

                options.cropShape =
                    CropShape.values()[ta.getInt(
                        R.styleable.CropImageView_cropShape,
                        options.cropShape.ordinal
                    )]

                options.guidelines = Guidelines.values()[ta.getInt(
                    R.styleable.CropImageView_cropGuidelines, options.guidelines.ordinal
                )]

                options.snapRadius =
                    ta.getDimension(R.styleable.CropImageView_cropSnapRadius, options.snapRadius)

                options.touchRadius =
                    ta.getDimension(R.styleable.CropImageView_cropTouchRadius, options.touchRadius)

                options.initialCropWindowPaddingRatio = ta.getFloat(
                    R.styleable.CropImageView_cropInitialCropWindowPaddingRatio,
                    options.initialCropWindowPaddingRatio
                )

                options.borderLineThickness = ta.getDimension(
                    R.styleable.CropImageView_cropBorderLineThickness, options.borderLineThickness
                )

                options.borderLineColor =
                    ta.getInteger(
                        R.styleable.CropImageView_cropBorderLineColor,
                        options.borderLineColor
                    )

                options.borderCornerThickness = ta.getDimension(
                    R.styleable.CropImageView_cropBorderCornerThickness,
                    options.borderCornerThickness
                )

                options.borderCornerOffset = ta.getDimension(
                    R.styleable.CropImageView_cropBorderCornerOffset, options.borderCornerOffset
                )

                options.borderCornerLength = ta.getDimension(
                    R.styleable.CropImageView_cropBorderCornerLength, options.borderCornerLength
                )

                options.borderCornerColor = ta.getInteger(
                    R.styleable.CropImageView_cropBorderCornerColor, options.borderCornerColor
                )

                options.guidelinesThickness = ta.getDimension(
                    R.styleable.CropImageView_cropGuidelinesThickness, options.guidelinesThickness
                )

                options.guidelinesColor =
                    ta.getInteger(
                        R.styleable.CropImageView_cropGuidelinesColor,
                        options.guidelinesColor
                    )

                options.backgroundColor =
                    ta.getInteger(
                        R.styleable.CropImageView_cropBackgroundColor,
                        options.backgroundColor
                    )

                options.showCropOverlay =
                    ta.getBoolean(
                        R.styleable.CropImageView_cropShowCropOverlay,
                        options.showCropOverlay
                    )

                options.borderCornerThickness = ta.getDimension(
                    R.styleable.CropImageView_cropBorderCornerThickness,
                    options.borderCornerThickness
                )

                options.minCropWindowWidth = ta.getDimension(
                    R.styleable.CropImageView_cropMinCropWindowWidth, options.minCropWindowWidth
                )

                options.minCropWindowHeight = ta.getDimension(
                    R.styleable.CropImageView_cropMinCropWindowHeight,
                    options.minCropWindowHeight
                )

                options.minCropResultWidth = ta.getInt(
                    R.styleable.CropImageView_cropMinCropResultWidthPX,
                    options.minCropResultWidth
                )

                options.minCropResultHeight = ta.getInt(
                    R.styleable.CropImageView_cropMinCropResultHeightPX,
                    options.minCropResultHeight
                )

                options.maxCropResultWidth = ta.getInt(
                    R.styleable.CropImageView_cropMaxCropResultWidthPX,
                    options.maxCropResultWidth
                )

                options.maxCropResultHeight = ta.getInt(
                    R.styleable.CropImageView_cropMaxCropResultHeightPX,
                    options.maxCropResultHeight
                )

                // if aspect ratio is set then set fixed to true
                if ((ta.hasValue(R.styleable.CropImageView_cropAspectRatioX) &&
                            ta.hasValue(R.styleable.CropImageView_cropAspectRatioX) &&
                            !ta.hasValue(R.styleable.CropImageView_cropFixAspectRatio))
                ) {
                    options.fixAspectRatio = true
                }
            } finally {
                ta.recycle()
            }
        }

        options.validate()

        scaleType = options.scaleType
        isAutoZoomEnabled = options.autoZoomEnabled
        maxZoom = options.maxZoom
        cropShape = options.cropShape
        isShowCropOverlay = options.showCropOverlay

        val v = LayoutInflater.from(context)?.inflate(R.layout.layout_crop_image_container, this, true)
        imageView = v?.findViewById(R.id.ivMain)
        imageView?.scaleType = ImageView.ScaleType.MATRIX

        cropOverlayView = v?.findViewById(R.id.cropOverlayView)
        cropOverlayView?.setInitialAttributeValues(options)
        cropOverlayView?.setCropWindowChangeListener(
            object : CropOverlayView.CropWindowChangeListener {
                override fun onCropWindowChanged(inProgress: Boolean) {
                    handleCropWindowChanged(inProgress, true)
                }

                override fun onZoom(scaleFactor: Float) {
                    zoom = scaleFactor
                    applyImageMatrix(width.toFloat(), height.toFloat(), center = true)
                    cropOverlayView.resetCropWindowRect()
                }
            })
    }

    fun getCroppedImageAsync(
        reqWidth: Int = 0,
        reqHeight: Int = 0,
        options: RequestSizeOptions = RequestSizeOptions.NONE
    ) {
        startCropWorkerTask(reqWidth, reqHeight, options)
    }

    fun setOnEditImageCompleteListener(listener: OnEditImageCompleteListener) {
        onEditImageCompleteListener = listener
    }

    fun setImageBitmap(bitmap: Bitmap?) {
        cropOverlayView?.initialCropWindowRect = null
        setBitmap(bitmap, null, 1, 0)
    }

    fun setImageUriAsync(uri: Uri) {
        imageView ?: return
        val metrics = imageView.resources.displayMetrics
        val densityAdj = if (metrics.density > 1) 1 / metrics.density else 1f
        val width = (metrics.widthPixels * densityAdj).toInt()
        val height = (metrics.heightPixels * densityAdj).toInt()
        val decodeResult = BitmapUtils.decodeSampledBitmap(context, uri, width, height)
        val newBm = invalidateBitmap(decodeResult.bitmap)

        cropOverlayView?.initialCropWindowRect = null
        setBitmap(newBm, uri, 1, 0)
    }

    private fun invalidateBitmap(bitmap: Bitmap?): Bitmap? {
        bitmap ?: return null
        if (scaleType != ScaleType.CENTER_CROP) return bitmap

        val reqWidth = DisplayUtil.getScreenWidth(context)
        var result = bitmap

        if (result.width < reqWidth)
            result = BitmapUtils.resizeBitmap(
                result,
                reqWidth,
                result.height * reqWidth / result.width,
                RequestSizeOptions.RESIZE_EXACT
            )

        result ?: return null

        if (result.height < reqWidth)
            result = BitmapUtils.resizeBitmap(
                result,
                result.width * reqWidth / result.height,
                reqWidth,
                RequestSizeOptions.RESIZE_EXACT
            )

        return result
    }

    /**
     * Rotates image by the specified number of degrees clockwise.<br></br>
     * Negative values represent counter-clockwise rotations.
     *
     * @param degrees Integer specifying the number of degrees to rotate.
     */
    private fun rotateImage(degrees: Int) {
        bitmap ?: return
        // Force degrees to be a non-zero value between 0 and 360 (inclusive)
        val newDegrees = if (degrees < 0)
            (degrees % 360) + 360
        else
            degrees % 360

        val flipAxes =
            cropOverlayView?.fixAspectRatio == false && ((newDegrees in 46..134) || (newDegrees in 216..304))

        BitmapUtils.RECT.set(cropOverlayView?.getCropWindowRect())
        var halfWidth = (if (flipAxes) BitmapUtils.RECT.height() else BitmapUtils.RECT.width()) / 2f
        var halfHeight = (if (flipAxes) BitmapUtils.RECT.width() else BitmapUtils.RECT.height()) / 2f

        imageMatrix.invert(imageInverseMatrix)

        BitmapUtils.POINTS[0] = BitmapUtils.RECT.centerX()
        BitmapUtils.POINTS[1] = BitmapUtils.RECT.centerY()
        BitmapUtils.POINTS[2] = 0f
        BitmapUtils.POINTS[3] = 0f
        BitmapUtils.POINTS[4] = 1f
        BitmapUtils.POINTS[5] = 0f
        imageInverseMatrix.mapPoints(BitmapUtils.POINTS)

        // This is valid because degrees is not negative.
        degreesRotated = (degreesRotated + degrees) % 360

        applyImageMatrix(width.toFloat(), height.toFloat(), true)

        // adjust the zoom so the crop window size remains the same even after image scale change
        imageMatrix.mapPoints(BitmapUtils.POINTS2, BitmapUtils.POINTS)
        zoom /= sqrt(((BitmapUtils.POINTS2[4] - BitmapUtils.POINTS2[2]).toDouble().pow(2.0) + (BitmapUtils.POINTS2[5] - BitmapUtils.POINTS2[3]).toDouble().pow(
            2.0
        ))).toFloat()
        zoom = max(zoom, 1f)

        applyImageMatrix(width.toFloat(), height.toFloat(), center = true)

        imageMatrix.mapPoints(BitmapUtils.POINTS2, BitmapUtils.POINTS)

        // adjust the width/height by the changes in scaling to the image
        val change = sqrt(((BitmapUtils.POINTS2[4] - BitmapUtils.POINTS2[2]).toDouble().pow(2.0) + (BitmapUtils.POINTS2[5] - BitmapUtils.POINTS2[3]).toDouble().pow(
            2.0
        )))
        halfWidth *= change.toFloat()
        halfHeight *= change.toFloat()

        // calculate the new crop window rectangle to center in the same location and have proper
        // width/height
        BitmapUtils.RECT.set(
            BitmapUtils.POINTS2[0] - halfWidth,
            BitmapUtils.POINTS2[1] - halfHeight,
            BitmapUtils.POINTS2[0] + halfWidth,
            BitmapUtils.POINTS2[1] + halfHeight
        )

        cropOverlayView?.resetCropOverlayView()
        cropOverlayView?.setCropWindowRect(BitmapUtils.RECT)
        applyImageMatrix(width.toFloat(), height.toFloat(), true)
        handleCropWindowChanged(false)
        cropOverlayView?.fixCurrentCropWindowRect()
    }

    internal fun onImageCroppingAsyncComplete(result: Bitmap?) {
        bitmapCroppingWorkerTask = null
        onEditImageCompleteListener?.onCropImageComplete(result)
    }

    /**
     * Set the given bitmap to be used in for cropping
     * Optionally clear full if the bitmap is new, or partial clear if the bitmap has been
     * manipulated.
     */
    private fun setBitmap(
        bitmap: Bitmap?,
        imageUri: Uri?,
        loadSampleSize: Int,
        degreesRotated: Int
    ) {
        imageView ?: return
        if (bitmap == null || this.bitmap == bitmap) return

        imageView.clearAnimation()
        imageView.setImageBitmap(bitmap)

        this.bitmap = bitmap
        this.imageUri = imageUri
        this.degreesRotated = degreesRotated
        loadedSampleSize = loadSampleSize

        applyImageMatrix(width.toFloat(), height.toFloat(), center = true, init = true)
        cropOverlayView?.resetCropOverlayView()
        setCropOverlayVisibility()

        if (bitmap.width < DisplayUtil.getScreenWidth(context) && zoom > 1 && scaleType != ScaleType.CENTER_CROP)
            handleCropWindowChanged(inProgress = false, animate = true)
    }

    private fun startCropWorkerTask(
        reqWidth: Int,
        reqHeight: Int,
        options: RequestSizeOptions
    ) {
        bitmap ?: return
        imageView?.clearAnimation()

        val currentTask = if (bitmapCroppingWorkerTask != null) bitmapCroppingWorkerTask?.get() else null
        currentTask?.cancel(true)

        val newReqWidth = if (options != RequestSizeOptions.NONE) reqWidth else 0
        val newReqHeight = if (options != RequestSizeOptions.NONE) reqHeight else 0

        val orgWidth = bitmap?.width ?: 0 * loadedSampleSize
        val orgHeight = bitmap?.height ?: 0 * loadedSampleSize
        if ((imageUri != null && (loadedSampleSize > 1 || options == RequestSizeOptions.SAMPLING))) {
            bitmapCroppingWorkerTask = WeakReference(
                BitmapCroppingWorkerTask(
                    WeakReference(this),
                    uri = imageUri,
                    cropPoints = cropPoints,
                    degreesRotated = degreesRotated,
                    orgWidth = orgWidth,
                    orgHeight = orgHeight,
                    fixAspectRatio = cropOverlayView?.fixAspectRatio,
                    aspectRatioX = 1,
                    aspectRatioY = 1,
                    reqWidth = newReqWidth,
                    reqHeight = newReqHeight,
                    reqSizeOptions = options
                )
            )
        } else {
            bitmapCroppingWorkerTask = WeakReference(
                BitmapCroppingWorkerTask(
                    WeakReference(this),
                    bitmap,
                    cropPoints = cropPoints,
                    degreesRotated = degreesRotated,
                    fixAspectRatio = cropOverlayView?.fixAspectRatio,
                    aspectRatioX = 1,
                    aspectRatioY = 1,
                    reqWidth = newReqWidth,
                    reqHeight = newReqHeight,
                    reqSizeOptions = options
                )
            )
        }
        bitmapCroppingWorkerTask?.get()?.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (bitmap == null) {
            setMeasuredDimension(widthSize, heightSize)
            return
        }

        // Bypasses a baffling bug when used within a ScrollView, where heightSize is set to 0.
        if (heightSize == 0)
            heightSize = bitmap?.height ?: 0

        val desiredWidth: Int
        val desiredHeight: Int

        var viewToBitmapWidthRatio = java.lang.Double.POSITIVE_INFINITY
        var viewToBitmapHeightRatio = java.lang.Double.POSITIVE_INFINITY

        // Checks if either width or height needs to be fixed
        if (widthSize < bitmap?.width ?: 0)
            viewToBitmapWidthRatio = widthSize.toDouble() / (bitmap?.width?.toDouble() ?: 1.0)

        if (heightSize < bitmap?.height ?: 0)
            viewToBitmapHeightRatio = heightSize.toDouble() / (bitmap?.height?.toDouble() ?: 1.0)

        // If either needs to be fixed, choose smallest ratio and calculate from there
        if ((viewToBitmapWidthRatio != java.lang.Double.POSITIVE_INFINITY || viewToBitmapHeightRatio != java.lang.Double.POSITIVE_INFINITY)) {
            if (viewToBitmapWidthRatio <= viewToBitmapHeightRatio) {
                desiredWidth = widthSize
                desiredHeight = ((bitmap?.height ?: 0) * viewToBitmapWidthRatio).toInt()
            } else {
                desiredHeight = heightSize
                desiredWidth = ((bitmap?.width ?: 0) * viewToBitmapHeightRatio).toInt()
            }
        } else {
            // Otherwise, the picture is within frame layout bounds. Desired width is simply picture
            // size
            desiredWidth = bitmap?.width ?: 0
            desiredHeight = bitmap?.height ?: 0
        }

        val width = getOnMeasureSpec(widthMode, widthSize, desiredWidth)
        val height = getOnMeasureSpec(heightMode, heightSize, desiredHeight)

        layoutWidth = width
        layoutHeight = height

        setMeasuredDimension(layoutWidth, layoutHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if (layoutWidth > 0 && layoutHeight > 0) {
            val origParams = layoutParams
            origParams.width = layoutWidth
            origParams.height = layoutHeight
            layoutParams = origParams

            if (bitmap != null) {
                applyImageMatrix((r - l).toFloat(), (b - t).toFloat(), true, init = true)
                if (sizeChanged) {
                    sizeChanged = false
                    handleCropWindowChanged(false)
                }
            } else
                updateImageBounds(true)
        } else
            updateImageBounds(true)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        sizeChanged = oldw > 0 && oldh > 0
    }

    private fun handleCropWindowChanged(inProgress: Boolean, animate: Boolean = false) {
        imageView ?: return

        val width = width
        val height = height

        if (bitmap != null && width > 0 && height > 0) {
            val cropRect = cropOverlayView?.getCropWindowRect() ?: RectF()
            if (inProgress) {
                if ((cropRect.left < 0 || cropRect.top < 0 || cropRect.right > width || cropRect.bottom > height))
                    applyImageMatrix(width.toFloat(), height.toFloat(), false)
            } else if (isAutoZoomEnabled || zoom > 1) {
                if (scaleType == ScaleType.CENTER_CROP && cropShape == CropShape.OVAL) {
                    initAnimation()
                    applyImageMatrix(width.toFloat(), height.toFloat(), true, animate)
                    return
                }

                var newZoom = 0f
                if ((zoom < maxZoom && cropRect.width() < width * 0.5f && cropRect.height() < height * 0.5f))
                    newZoom = min(maxZoom.toFloat(), min(width / (cropRect.width() / zoom / 0.64f), height / (cropRect.height() / zoom / 0.64f)))

                if (zoom > 1 && (cropRect.width() > width * 0.65f || cropRect.height() > height * 0.65f))
                    newZoom = max(1f, min(width / (cropRect.width() / zoom / 0.51f), height / (cropRect.height() / zoom / 0.51f)))

                if (!isAutoZoomEnabled)
                    newZoom = 1f

                if (newZoom > 0 && newZoom != zoom) {
                    if (animate)
                        initAnimation()
                    zoom = newZoom
                    applyImageMatrix(width.toFloat(), height.toFloat(), true, animate)
                }
            }
        }
    }

    private fun initAnimation() {
        imageView ?: return
        if (animation == null)
        // lazy create animation single instance
            animation = CropImageAnimation(imageView, cropOverlayView ?: return)
        // set the state for animation to start from
        animation?.setStartState(imagePoints, imageMatrix)
    }

    /**
     * Apply matrix to handle the image inside the image view.
     *
     * @param width the width of the image view
     * @param height the height of the image view
     */
    private fun applyImageMatrix(
        width: Float,
        height: Float,
        center: Boolean,
        animate: Boolean = false,
        init: Boolean = false
    ) {
        if (!(bitmap != null && width > 0 && height > 0)) return

        imageMatrix.invert(imageInverseMatrix)
        val cropRect = cropOverlayView?.getCropWindowRect() ?: RectF()
        imageInverseMatrix.mapRect(cropRect)
        imageMatrix.reset()

        // move the image to the center of the image view first so we can manipulate it from there
        imageMatrix.postTranslate((width - (bitmap?.width ?: 0)) / 2, (height - (bitmap?.height ?: 0)) / 2)
        mapImagePointsByImageMatrix()

        // rotate the image the required degrees from center of image
        if (degreesRotated > 0) {
            imageMatrix.postRotate(
                degreesRotated.toFloat(),
                BitmapUtils.getRectCenterX(imagePoints),
                BitmapUtils.getRectCenterY(imagePoints)
            )
            mapImagePointsByImageMatrix()
        }

        // scale the image to the image view, image rect transformed to know new width/height
        val scale = min(width / BitmapUtils.getRectWidth(imagePoints), height / BitmapUtils.getRectHeight(imagePoints))

        if ((scaleType == ScaleType.FIT_CENTER ||
                    (scaleType == ScaleType.CENTER_INSIDE && scale < 1))
        ) {
            imageMatrix.postScale(
                scale,
                scale,
                BitmapUtils.getRectCenterX(imagePoints),
                BitmapUtils.getRectCenterY(imagePoints)
            )
            mapImagePointsByImageMatrix()
        }

        imageMatrix.postScale(
                zoom,
                zoom,
                BitmapUtils.getRectCenterX(imagePoints),
                BitmapUtils.getRectCenterY(imagePoints)
            )
        mapImagePointsByImageMatrix()

        imageMatrix.mapRect(cropRect)

        if (center) {
            // set the zoomed area to be as to the center of cropping window as possible
            zoomOffsetX = if (width > BitmapUtils.getRectWidth(imagePoints))
                0f
            else
                (max(min(width / 2 - cropRect.centerX(), -BitmapUtils.getRectLeft(imagePoints)), getWidth() - BitmapUtils.getRectRight(imagePoints)) / zoom)
            zoomOffsetY = if (height > BitmapUtils.getRectHeight(imagePoints))
                0f
            else
                (max(min(height / 2 - cropRect.centerY(), -BitmapUtils.getRectTop(imagePoints)), getHeight() - BitmapUtils.getRectBottom(imagePoints)) / zoom)
        } else {
            // adjust the zoomed area so the crop window rectangle will be inside the area in case it
            // was moved outside
            zoomOffsetX =
                (min(max(zoomOffsetX * zoom, -cropRect.left), -cropRect.right + width) / zoom)
            zoomOffsetY =
                (min(max(zoomOffsetY * zoom, -cropRect.top), -cropRect.bottom + height) / zoom)
        }

        // apply to zoom offset translate and update the crop rectangle to offset correctly
        // not apply for the first time image view is drawn
        if (!init)
            imageMatrix.postTranslate(zoomOffsetX * zoom, zoomOffsetY * zoom)

        cropRect.offset(zoomOffsetX * zoom, zoomOffsetY * zoom)
        cropOverlayView?.setCropWindowRect(cropRect)
        mapImagePointsByImageMatrix()
        cropOverlayView?.invalidate()

        if (animate) {
            // set the state for animation to end in, start animation now
            animation?.setEndState(imagePoints, imageMatrix)
            imageView?.startAnimation(animation)
        } else
            imageView?.imageMatrix = imageMatrix

        // update the image rectangle in the crop overlay
        updateImageBounds(false)
    }

    /**
     * Adjust the given image rectangle by image transformation matrix to know the final rectangle of
     * the image.
     * To get the proper rectangle it must be first reset to original image rectangle.
     */
    private fun mapImagePointsByImageMatrix() {
        imagePoints[0] = 0f
        imagePoints[1] = 0f
        imagePoints[2] = bitmap?.width?.toFloat() ?: 0f
        imagePoints[3] = 0f
        imagePoints[4] = bitmap?.width?.toFloat() ?: 0f
        imagePoints[5] = bitmap?.height?.toFloat() ?: 0f
        imagePoints[6] = 0f
        imagePoints[7] = bitmap?.height?.toFloat() ?: 0f
        imageMatrix.mapPoints(imagePoints)

        scaleImagePoints[0] = 0f
        scaleImagePoints[1] = 0f
        scaleImagePoints[2] = 100f
        scaleImagePoints[3] = 0f
        scaleImagePoints[4] = 100f
        scaleImagePoints[5] = 100f
        scaleImagePoints[6] = 0f
        scaleImagePoints[7] = 100f
        imageMatrix.mapPoints(scaleImagePoints)
    }

    private fun getOnMeasureSpec(measureSpecMode: Int, measureSpecSize: Int, desiredSize: Int): Int =
        when (measureSpecMode) {
            MeasureSpec.EXACTLY -> measureSpecSize
            MeasureSpec.AT_MOST -> min(desiredSize, measureSpecSize)
            else -> desiredSize
        }

    private fun setCropOverlayVisibility() {
        cropOverlayView?.visibility = if (isShowCropOverlay && bitmap != null) View.VISIBLE else View.INVISIBLE
    }

    /** Update the scale factor between the actual image bitmap and the shown image.<br></br>  */
    private fun updateImageBounds(clear: Boolean) {
        if (bitmap != null && !clear)
            cropOverlayView?.setCropWindowLimits(width.toFloat(), height.toFloat(), 1f, 1f)
        cropOverlayView?.setBounds(if (clear) null else imagePoints, width, height)
    }

    enum class CropShape {
        RECTANGLE,
        OVAL
    }

    enum class ScaleType {
        FIT_CENTER,
        CENTER,
        CENTER_CROP,
        CENTER_INSIDE
    }

    enum class Guidelines {
        OFF,
        ON_TOUCH,
        ON
    }

    enum class RequestSizeOptions {

        /** No resize/sampling is done unless required for memory management (OOM).  */
        NONE,

        /**
         * Only sample the image during loading (if image set using URI) so the smallest of the image
         * dimensions will be between the requested size and x2 requested size.
         * NOTE: resulting image will not be exactly requested width/height see: [Loading
 * Large Bitmaps Efficiently](http://developer.android.com/training/displaying-bitmaps/load-bitmap.html).
         */
        SAMPLING,

        /**
         * Resize the image uniformly (maintain the image's aspect ratio) so that both dimensions (width
         * and height) of the image will be equal to or **less** than the corresponding requested
         * dimension.
         * If the image is smaller than the requested size it will NOT change.
         */
        RESIZE_INSIDE,

        /**
         * Resize the image uniformly (maintain the image's aspect ratio) to fit in the given
         * width/height.
         * The largest dimension will be equals to the requested and the second dimension will be
         * smaller.
         * If the image is smaller than the requested size it will enlarge it.
         */
        RESIZE_FIT,

        /**
         * Resize the image to fit exactly in the given width/height.
         * This resize method does NOT preserve aspect ratio
         * If the image is smaller than the requested size it will enlarge it.
         */
        RESIZE_EXACT
    }

    interface OnEditImageCompleteListener {
        fun onCropImageComplete(result: Bitmap?)
    }
}