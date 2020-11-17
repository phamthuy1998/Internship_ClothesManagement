package com.ptithcm.admin.widget

import android.content.ContentResolver
import android.content.Context
import android.graphics.*
import android.net.Uri
import android.util.Log
import java.io.Closeable
import java.io.FileNotFoundException
import java.io.InputStream
import javax.microedition.khronos.egl.EGL10
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.egl.EGLContext
import kotlin.math.*

object BitmapUtils {

    val EMPTY_RECT = Rect()

    val EMPTY_RECT_F = RectF()

    /** Reusable rectangle for general internal usage  */
    val RECT = RectF()

    /** Reusable point for general internal usage  */
    val POINTS = FloatArray(6)

    /** Reusable point for general internal usage  */
    val POINTS2 = FloatArray(6)

    private const val IMAGE_MAX_BITMAP_DIMENSION = 2048

    /** Used to know the max texture size allowed to be rendered  */
    private var mMaxTextureSize: Int = 0

    // Safe minimum default size
    // Get EGL Display
    // Initialise
    // Query total number of configurations
    // Query actual list configurations
    // Iterate through all the configurations to located the maximum texture size
    // Only need to check for width since opengl textures are always squared
    // Keep track of the maximum texture size
    // Release
    // Return largest texture size found, or default
    private val maxTextureSize: Int
        get() {
            try {
                val egl = EGLContext.getEGL() as EGL10
                val display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY)
                val version = IntArray(2)
                egl.eglInitialize(display, version)
                val totalConfigurations = IntArray(1)
                egl.eglGetConfigs(display, null, 0, totalConfigurations)
                val configurationsList = arrayOfNulls<EGLConfig>(totalConfigurations[0])
                egl.eglGetConfigs(display, configurationsList, totalConfigurations[0], totalConfigurations)

                val textureSize = IntArray(1)
                var maximumTextureSize = 0
                for (i in 0 until totalConfigurations[0]) {
                    egl.eglGetConfigAttrib(
                        display, configurationsList[i], EGL10.EGL_MAX_PBUFFER_WIDTH, textureSize
                    )
                    if (maximumTextureSize < textureSize[0]) {
                        maximumTextureSize = textureSize[0]
                    }
                }
                egl.eglTerminate(display)
                return max(maximumTextureSize, IMAGE_MAX_BITMAP_DIMENSION)
            } catch (e: Exception) {
                return IMAGE_MAX_BITMAP_DIMENSION
            }
        }

    /**
     * Create a new bitmap that has all pixels beyond the oval shape transparent. Old bitmap is
     * recycled.
     */
    fun toOvalBitmap(bitmap: Bitmap?): Bitmap? {
        bitmap ?: return null

        val width = bitmap.width
        val height = bitmap.height
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(output)

        val color = -0xbdbdbe
        val paint = Paint()

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color

        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawOval(rect, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return output
    }

    /** Decode bitmap from stream using sampling to get bitmap with the requested limit.  */
    fun decodeSampledBitmap(context: Context, uri: Uri, reqWidth: Int, reqHeight: Int): BitmapSampled {
        try {
            val resolver = context.contentResolver

            // First decode with inJustDecodeBounds=true to check dimensions
            val options = decodeImageForOption(resolver, uri)

            if (options.outWidth == -1 && options.outHeight == -1)
                throw RuntimeException("File is not a picture")

            // Calculate inSampleSize
            options.inSampleSize = max(calculateInSampleSizeByRequestedSize(
                    options.outWidth, options.outHeight, reqWidth, reqHeight
                ), calculateInSampleSizeByMaxTextureSize(options.outWidth, options.outHeight))

            // Decode bitmap with inSampleSize set
            val bitmap = decodeImage(resolver, uri, options)

            return BitmapSampled(bitmap, options.inSampleSize)
        } catch (e: Exception) {
            throw RuntimeException(
                "Failed to load sampled bitmap: " + uri + "\r\n" + e.message, e
            )
        }
    }

    /**
     * Crop image bitmap from given bitmap using the given points in the original bitmap and the given
     * rotation.<br></br>
     * if the rotation is not 0,90,180 or 270 degrees then we must first crop a larger area of the
     * image that contains the requires rectangle, rotate and then crop again a sub rectangle.<br></br>
     * If crop fails due to OOM we scale the cropping image by 0.5 every time it fails until it is
     * small enough.
     */
    fun cropBitmapObjectHandleOOM(
        bitmap: Bitmap,
        points: FloatArray,
        degreesRotated: Int,
        fixAspectRatio: Boolean,
        aspectRatioX: Int,
        aspectRatioY: Int
    ): BitmapSampled {
        var scale = 1
        while (true) {
            try {
                val cropBitmap = cropBitmapObjectWithScale(
                    bitmap,
                    points,
                    degreesRotated,
                    fixAspectRatio,
                    aspectRatioX,
                    aspectRatioY,
                    1 / scale.toFloat()
                )
                return BitmapSampled(cropBitmap, scale)
            } catch (e: OutOfMemoryError) {
                scale *= 2
                if (scale > 8) {
                    throw e
                }
            }
        }
    }

    /**
     * Crop image bitmap from given bitmap using the given points in the original bitmap and the given
     * rotation.<br></br>
     * if the rotation is not 0,90,180 or 270 degrees then we must first crop a larger area of the
     * image that contains the requires rectangle, rotate and then crop again a sub rectangle.
     *
     * @param scale how much to scale the cropped image part, use 0.5 to lower the image by half (OOM
     * handling)
     */
    private fun cropBitmapObjectWithScale(
        bitmap: Bitmap,
        points: FloatArray,
        degreesRotated: Int,
        fixAspectRatio: Boolean,
        aspectRatioX: Int,
        aspectRatioY: Int,
        scale: Float
    ): Bitmap? {

        // get the rectangle in original image that contains the required cropped area (larger for non
        // rectangular crop)
        val rect = getRectFromPoints(
            points,
            bitmap.width,
            bitmap.height,
            fixAspectRatio,
            aspectRatioX,
            aspectRatioY
        )

        // crop and rotate the cropped image in one operation
        val matrix = Matrix()
        matrix.setRotate(degreesRotated.toFloat(), (bitmap.width / 2).toFloat(), (bitmap.height / 2).toFloat())
        matrix.postScale(scale, scale)
        var result: Bitmap? =
            Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height(), matrix, true)

        if (result == bitmap) {
            // corner case when all bitmap is selected, no worth optimizing for it
            result = bitmap.copy(bitmap.config, false)
        }

        // rotating by 0, 90, 180 or 270 degrees doesn't require extra cropping
        if (degreesRotated % 90 != 0) {

            // extra crop because non rectangular crop cannot be done directly on the image without
            // rotating first
            result = cropForRotatedImage(
                result, points, rect, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY
            )
        }

        return result
    }

    /**
     * Crop image bitmap from URI by decoding it with specific width and height to down-sample if
     * required.<br></br>
     * Additionally if OOM is thrown try to increase the sampling (2,4,8).
     */
    fun cropBitmap(
        context: Context?,
        loadedImageUri: Uri,
        points: FloatArray,
        degreesRotated: Int,
        orgWidth: Int,
        orgHeight: Int,
        fixAspectRatio: Boolean,
        aspectRatioX: Int,
        aspectRatioY: Int,
        reqWidth: Int,
        reqHeight: Int
    ): BitmapSampled {
        var sampleMulti = 1
        while (true) {
            try {
                // if successful, just return the resulting bitmap
                return cropBitmap(
                    context,
                    loadedImageUri,
                    points,
                    degreesRotated,
                    orgWidth,
                    orgHeight,
                    fixAspectRatio,
                    aspectRatioX,
                    aspectRatioY,
                    reqWidth,
                    reqHeight,
                    sampleMulti
                )
            } catch (e: OutOfMemoryError) {
                // if OOM try to increase the sampling to lower the memory usage
                sampleMulti *= 2
                if (sampleMulti > 16) {
                    throw RuntimeException(
                        "Failed to handle OOM by sampling (" +
                                sampleMulti +
                                "): " +
                                loadedImageUri +
                                "\r\n" +
                                e.message,
                        e
                    )
                }
            }
        }
    }

    /** Get left value of the bounding rectangle of the given points.  */
    fun getRectLeft(points: FloatArray): Float {
        return min(min(min(points[0], points[2]), points[4]), points[6])
    }

    /** Get top value of the bounding rectangle of the given points.  */
    fun getRectTop(points: FloatArray): Float {
        return min(min(min(points[1], points[3]), points[5]), points[7])
    }

    /** Get right value of the bounding rectangle of the given points.  */
    fun getRectRight(points: FloatArray): Float {
        return max(max(max(points[0], points[2]), points[4]), points[6])
    }

    /** Get bottom value of the bounding rectangle of the given points.  */
    fun getRectBottom(points: FloatArray): Float {
        return max(max(max(points[1], points[3]), points[5]), points[7])
    }

    /** Get width of the bounding rectangle of the given points.  */
    fun getRectWidth(points: FloatArray): Float {
        return getRectRight(points) - getRectLeft(points)
    }

    /** Get height of the bounding rectangle of the given points.  */
    fun getRectHeight(points: FloatArray): Float {
        return getRectBottom(points) - getRectTop(points)
    }

    /** Get horizontal center value of the bounding rectangle of the given points.  */
    fun getRectCenterX(points: FloatArray): Float {
        return (getRectRight(points) + getRectLeft(points)) / 2f
    }

    /** Get vertical center value of the bounding rectangle of the given points.  */
    fun getRectCenterY(points: FloatArray): Float {
        return (getRectBottom(points) + getRectTop(points)) / 2f
    }

    /**
     * Get a rectangle for the given 4 points (x0,y0,x1,y1,x2,y2,x3,y3) by finding the min/max 2
     * points that contains the given 4 points and is a straight rectangle.
     */
    private fun getRectFromPoints(
        points: FloatArray,
        imageWidth: Int,
        imageHeight: Int,
        fixAspectRatio: Boolean,
        aspectRatioX: Int,
        aspectRatioY: Int
    ): Rect {
        val left = max(0f, getRectLeft(points)).roundToInt()
        val top = max(0f, getRectTop(points)).roundToInt()
        val right = min(imageWidth.toFloat(), getRectRight(points)).roundToInt()
        val bottom = min(imageHeight.toFloat(), getRectBottom(points)).roundToInt()

        val rect = Rect(left, top, right, bottom)
        if (fixAspectRatio) {
            fixRectForAspectRatio(rect, aspectRatioX, aspectRatioY)
        }

        return rect
    }

    /**
     * Fix the given rectangle if it doesn't confirm to aspect ration rule.<br></br>
     * Make sure that width and height are equal if 1:1 fixed aspect ratio is requested.
     */
    private fun fixRectForAspectRatio(rect: Rect, aspectRatioX: Int, aspectRatioY: Int) {
        if (aspectRatioX == aspectRatioY && rect.width() != rect.height()) {
            if (rect.height() > rect.width()) {
                rect.bottom -= rect.height() - rect.width()
            } else {
                rect.right -= rect.width() - rect.height()
            }
        }
    }

    /** Resize the given bitmap to the given width/height by the given option.<br></br>  */
    fun resizeBitmap(
        bitmap: Bitmap?,
        reqWidth: Int,
        reqHeight: Int,
        options: CropImageView.RequestSizeOptions?
    ): Bitmap? {
        bitmap ?: return null
        try {
            if ((reqWidth > 0 &&
                        reqHeight > 0 &&
                        ((options == CropImageView.RequestSizeOptions.RESIZE_FIT ||
                                options == CropImageView.RequestSizeOptions.RESIZE_INSIDE ||
                                options == CropImageView.RequestSizeOptions.RESIZE_EXACT)))
            ) {

                var resized: Bitmap? = null
                if (options == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                    resized = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, false)
                } else {
                    val width = bitmap.width
                    val height = bitmap.height
                    val scale = max(width / reqWidth.toFloat(), height / reqHeight.toFloat())
                    if (scale > 1 || options == CropImageView.RequestSizeOptions.RESIZE_FIT) {
                        resized = Bitmap.createScaledBitmap(
                            bitmap, (width / scale).toInt(), (height / scale).toInt(), false
                        )
                    }
                }
                if (resized != null) {
                    if (resized != bitmap)
                        bitmap.recycle()
                    return resized
                }
            }
        } catch (e: Exception) {
            Log.w("AIC", "Failed to resize cropped image, return bitmap before resize", e)
        }

        return bitmap
    }

    /**
     * Crop image bitmap from URI by decoding it with specific width and height to down-sample if
     * required.
     *
     * @param orgWidth used to get rectangle from points (handle edge cases to limit rectangle)
     * @param orgHeight used to get rectangle from points (handle edge cases to limit rectangle)
     * @param sampleMulti used to increase the sampling of the image to handle memory issues.
     */
    private fun cropBitmap(
        context: Context?,
        loadedImageUri: Uri,
        points: FloatArray,
        degreesRotated: Int,
        orgWidth: Int,
        orgHeight: Int,
        fixAspectRatio: Boolean,
        aspectRatioX: Int,
        aspectRatioY: Int,
        reqWidth: Int,
        reqHeight: Int,
        sampleMulti: Int
    ): BitmapSampled {

        val rect = getRectFromPoints(points, orgWidth, orgHeight, fixAspectRatio, aspectRatioX, aspectRatioY)
        val width = if (reqWidth > 0) reqWidth else rect.width()
        val height = if (reqHeight > 0) reqHeight else rect.height()

        var result: Bitmap? = null
        var sampleSize = 1
        try {
            // decode only the required image from URI, optionally sub-sampling if reqWidth/reqHeight is
            // given.
            val bitmapSampled = decodeSampledBitmapRegion(context, loadedImageUri, rect, width, height, sampleMulti)
            result = bitmapSampled.bitmap
            sampleSize = bitmapSampled.sampleSize ?: 1
        } catch (ignored: Exception) {
        }

        if (result != null) {
            try {
                // rotate the decoded region by the required amount
                if (degreesRotated % 90 != 0) {
                    // extra crop because non rectangular crop cannot be done directly on the image without
                    // rotating first
                    result = cropForRotatedImage(
                        result,
                        points,
                        rect,
                        degreesRotated,
                        fixAspectRatio,
                        aspectRatioX,
                        aspectRatioY
                    )
                }
            } catch (e: OutOfMemoryError) {
                result.recycle()
                throw e
            }

            return BitmapSampled(result, sampleSize)
        } else {
            return cropBitmap(
                context,
                loadedImageUri,
                points,
                degreesRotated,
                fixAspectRatio,
                aspectRatioX,
                aspectRatioY,
                sampleMulti,
                rect,
                width,
                height
            )
        }
    }

    /**
     * Crop bitmap by fully loading the original and then cropping it, fallback in case cropping
     * region failed.
     */
    private fun cropBitmap(
        context: Context?,
        loadedImageUri: Uri,
        points: FloatArray,
        degreesRotated: Int,
        fixAspectRatio: Boolean,
        aspectRatioX: Int,
        aspectRatioY: Int,
        sampleMulti: Int,
        rect: Rect,
        width: Int,
        height: Int
    ): BitmapSampled {
        var result: Bitmap? = null
        val sampleSize: Int
        try {
            val options = BitmapFactory.Options()
            sampleSize =
                (sampleMulti * calculateInSampleSizeByRequestedSize(rect.width(), rect.height(), width, height))
            options.inSampleSize = sampleSize

            val fullBitmap = decodeImage(context?.contentResolver, loadedImageUri, options)
            if (fullBitmap != null) {
                try {
                    // adjust crop points by the sampling because the image is smaller
                    val points2 = FloatArray(points.size)
                    System.arraycopy(points, 0, points2, 0, points.size)
                    for (i in points2.indices) {
                        points2[i] = points2[i] / options.inSampleSize
                    }

                    result = cropBitmapObjectWithScale(
                        fullBitmap,
                        points2,
                        degreesRotated,
                        fixAspectRatio,
                        aspectRatioX,
                        aspectRatioY,
                        1f
                    )
                } finally {
                    if (result != fullBitmap) {
                        fullBitmap.recycle()
                    }
                }
            }
        } catch (e: OutOfMemoryError) {
            result?.recycle()
            throw e
        } catch (e: Exception) {
            throw RuntimeException(
                "Failed to load sampled bitmap: " + loadedImageUri + "\r\n" + e.message, e
            )
        }

        return BitmapSampled(result, sampleSize)
    }

    /** Decode image from uri using "inJustDecodeBounds" to get the image dimensions.  */
    @Throws(FileNotFoundException::class)
    private fun decodeImageForOption(resolver: ContentResolver, uri: Uri): BitmapFactory.Options {
        var stream: InputStream? = null
        try {
            stream = resolver.openInputStream(uri)
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(stream, EMPTY_RECT, options)
            options.inJustDecodeBounds = false
            return options
        } finally {
            closeSafe(stream)
        }
    }

    /**
     * Decode image from uri using given "inSampleSize", but if failed due to out-of-memory then raise
     * the inSampleSize until success.
     */
    private fun decodeImage(
        resolver: ContentResolver?,
        uri: Uri,
        options: BitmapFactory.Options
    ): Bitmap? {
        do {
            var stream: InputStream? = null
            try {
                stream = resolver?.openInputStream(uri)
                return BitmapFactory.decodeStream(stream, EMPTY_RECT, options)
            } catch (e: OutOfMemoryError) {
                options.inSampleSize *= 2
            } finally {
                closeSafe(stream)
            }
        } while (options.inSampleSize <= 512)
        throw RuntimeException("Failed to decode image: $uri")
    }

    private fun decodeSampledBitmapRegion(
        context: Context?,
        uri: Uri,
        rect: Rect,
        reqWidth: Int,
        reqHeight: Int,
        sampleMulti: Int
    ): BitmapSampled {
        var stream: InputStream? = null
        var decoder: BitmapRegionDecoder? = null
        try {
            val options = BitmapFactory.Options()
            options.inSampleSize = (sampleMulti * calculateInSampleSizeByRequestedSize(
                rect.width(), rect.height(), reqWidth, reqHeight
            ))

            stream = context?.contentResolver?.openInputStream(uri)
            decoder = BitmapRegionDecoder.newInstance(stream, false)
            do {
                try {
                    return BitmapSampled(decoder!!.decodeRegion(rect, options), options.inSampleSize)
                } catch (e: OutOfMemoryError) {
                    options.inSampleSize *= 2
                }
            } while (options.inSampleSize <= 512)
        } catch (e: Exception) {
            throw RuntimeException(
                "Failed to load sampled bitmap: " + uri + "\r\n" + e.message, e
            )
        } finally {
            closeSafe(stream)
            decoder?.recycle()
        }
        return BitmapSampled(null, 1)
    }

    /**
     * Special crop of bitmap rotated by not stright angle, in this case the original crop bitmap
     * contains parts beyond the required crop area, this method crops the already cropped and rotated
     * bitmap to the final rectangle.<br></br>
     * Note: rotating by 0, 90, 180 or 270 degrees doesn't require extra cropping.
     */
    private fun cropForRotatedImage(
        bitmap: Bitmap?,
        points: FloatArray,
        rect: Rect,
        degreesRotated: Int,
        fixAspectRatio: Boolean,
        aspectRatioX: Int,
        aspectRatioY: Int
    ): Bitmap? {

        bitmap ?: return null

        if (degreesRotated % 90 != 0) {
            var adjLeft = 0
            var adjTop = 0
            var width = 0
            var height = 0
            val rads = Math.toRadians(degreesRotated.toDouble())
            val compareTo = if (degreesRotated < 90 || (degreesRotated in 181..269))
                rect.left
            else
                rect.right
            var i = 0
            while (i < points.size) {
                if (points[i] >= compareTo - 1 && points[i] <= compareTo + 1) {
                    adjLeft = abs(sin(rads) * (rect.bottom - points[i + 1])).toInt()
                    adjTop = abs(cos(rads) * (points[i + 1] - rect.top)).toInt()
                    width = abs((points[i + 1] - rect.top) / sin(rads)).toInt()
                    height = abs((rect.bottom - points[i + 1]) / cos(rads)).toInt()
                    break
                }
                i += 2
            }

            rect.set(adjLeft, adjTop, adjLeft + width, adjTop + height)
            if (fixAspectRatio)
                fixRectForAspectRatio(rect, aspectRatioX, aspectRatioY)

            val newBitmap = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height())
            if (newBitmap != bitmap)
                newBitmap?.recycle()
            return newBitmap
        }

        return null
    }

    /**
     * Calculate the largest inSampleSize value that is a power of 2 and keeps both height and width
     * larger than the requested height and width.
     */
    private fun calculateInSampleSizeByRequestedSize(
        width: Int,
        height: Int,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            while ((height / 2 / inSampleSize) > reqHeight && (width / 2 / inSampleSize) > reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    /**
     * Calculate the largest inSampleSize value that is a power of 2 and keeps both height and width
     * smaller than max texture size allowed for the device.
     */
    private fun calculateInSampleSizeByMaxTextureSize(width: Int, height: Int): Int {
        var inSampleSize = 1
        if (mMaxTextureSize == 0) {
            mMaxTextureSize = maxTextureSize
        }
        if (mMaxTextureSize > 0) {
            while (((height / inSampleSize) > mMaxTextureSize || (width / inSampleSize) > mMaxTextureSize)) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    /**
     * Close the given closeable object (Stream) in a safe way: check if it is null and catch-log
     * exception thrown.
     *
     * @param closeable the closable object to close
     */
    private fun closeSafe(closeable: Closeable?) {
        closeable?.close()
    }

    class BitmapSampled(
        val bitmap: Bitmap? = null,
        val sampleSize: Int? = 1
    )
}
