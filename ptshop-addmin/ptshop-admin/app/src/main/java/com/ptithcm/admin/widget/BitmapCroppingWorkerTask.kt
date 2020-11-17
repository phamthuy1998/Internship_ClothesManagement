package com.ptithcm.admin.widget

import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import java.lang.ref.WeakReference

internal class BitmapCroppingWorkerTask(
    private val cropImageViewReference: WeakReference<CropImageView>,
    private val bitmap: Bitmap? = null,
    private val uri: Uri? = null,
    private val cropPoints: FloatArray?,
    private val degreesRotated: Int,
    private val orgWidth: Int = 0,
    private val orgHeight: Int = 0,
    private val fixAspectRatio: Boolean?,
    private val aspectRatioX: Int,
    private val aspectRatioY: Int,
    private val reqWidth: Int = 0,
    private val reqHeight: Int = 0,
    private val reqSizeOptions: CropImageView.RequestSizeOptions?
) : AsyncTask<Void, Void, Bitmap>() {

    override fun doInBackground(vararg params: Void): Bitmap? {
        try {
            if (!isCancelled) {
                val bitmapSampled =
                    when {
                        uri != null -> BitmapUtils.cropBitmap(
                            cropImageViewReference.get()?.context,
                            uri,
                            cropPoints ?: floatArrayOf(),
                            degreesRotated,
                            orgWidth,
                            orgHeight,
                            fixAspectRatio ?: false,
                            aspectRatioX,
                            aspectRatioY,
                            reqWidth,
                            reqHeight
                        )
                        bitmap != null -> BitmapUtils.cropBitmapObjectHandleOOM(
                            bitmap,
                            cropPoints ?: floatArrayOf(),
                            degreesRotated,
                            fixAspectRatio ?: false,
                            aspectRatioX,
                            aspectRatioY
                        )
                        else -> null
                    } ?: return null

                return BitmapUtils.resizeBitmap(bitmapSampled.bitmap, reqWidth, reqHeight, reqSizeOptions)
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }

    /**
     * Once complete, see if ImageView is still around and set bitmap.
     *
     * @param bitmap the result of bitmap cropping
     */
    override fun onPostExecute(bitmap: Bitmap?) {
        if (!isCancelled)
            cropImageViewReference.get()?.onImageCroppingAsyncComplete(bitmap)
    }
}
