package com.ptithcm.ptshop.widget

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.os.Parcelable
import android.util.TypedValue
import kotlinx.android.parcel.Parcelize

@Parcelize
class CropImageOptions(
    var cropShape: CropImageView.CropShape = CropImageView.CropShape.RECTANGLE,

    /**
     * An edge of the crop window will snap to the corresponding edge of a specified bounding box when
     * the crop window edge is less than or equal to this distance (in pixels) away from the bounding
     * box edge. (in pixels)
     */
    var snapRadius: Float = 0f,

    /**
     * The radius of the touchable area around the handle. (in pixels)<br></br>
     * We are basing this value off of the recommended 48dp Rhythm.<br></br>
     * See: http://developer.android.com/design/style/metrics-grids.html#48dp-rhythm
     */
    var touchRadius: Float = 0f,
    var guidelines: CropImageView.Guidelines = CropImageView.Guidelines.OFF,
    var scaleType: CropImageView.ScaleType = CropImageView.ScaleType.FIT_CENTER,
    var showCropOverlay: Boolean = true,
    var autoZoomEnabled: Boolean = true,
    var multiTouchEnabled: Boolean = true,
    var maxZoom: Int = 4,
    var initialCropWindowPaddingRatio: Float = .1f,
    var fixAspectRatio: Boolean = false,
    var aspectRatioX: Int = 1,
    var aspectRatioY: Int = 1,
    var borderLineThickness: Float = 0f,
    var borderLineColor: Int = Color.argb(170, 255, 255, 255),
    var borderCornerThickness: Float = 0f,
    /** the offset of corner line from crop window border. (in pixels)  */
    var borderCornerOffset: Float = 0f,
    var borderCornerLength: Float = 0f,
    var borderCornerColor: Int = Color.WHITE,
    var guidelinesThickness: Float = 0f,
    var guidelinesColor: Int = Color.argb(170, 255, 255, 255),
    var backgroundColor: Int = Color.argb(119, 0, 0, 0),
    var minCropWindowWidth: Float = 0f,
    var minCropWindowHeight: Float = 0f,
    var minCropResultWidth: Int = 40,
    var minCropResultHeight: Int = 40,
    var maxCropResultWidth: Int = 99999,
    var maxCropResultHeight: Int = 99999,
    var outputCompressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    var outputCompressQuality: Int = 90,
    var outputRequestWidth: Int = 0,
    var outputRequestHeight: Int = 0,
    var outputRequestSizeOptions: CropImageView.RequestSizeOptions = CropImageView.RequestSizeOptions.NONE,
    var initialCropWindowRectangle: Rect? = null,
    var initialRotation: Int = -1,
    var allowRotation: Boolean = true,
    var rotationDegrees: Int = 90
) : Parcelable {

    init {

        val dm = Resources.getSystem().displayMetrics
        snapRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, dm)
        touchRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, dm)

        borderLineThickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, dm)
        borderCornerThickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, dm)
        borderCornerOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, dm)
        borderCornerLength = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14f, dm)

        guidelinesThickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, dm)

        minCropWindowWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42f, dm)
        minCropWindowHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42f, dm)
    }

    fun validate() {
        if (maxZoom < 0) {
            throw IllegalArgumentException("Cannot set max zoom to a number < 1")
        }
        if (touchRadius < 0) {
            throw IllegalArgumentException("Cannot set touch radius value to a number <= 0 ")
        }
        if (initialCropWindowPaddingRatio < 0 || initialCropWindowPaddingRatio >= 0.5) {
            throw IllegalArgumentException(
                "Cannot set initial crop window padding value to a number < 0 or >= 0.5"
            )
        }
        if (aspectRatioX <= 0) {
            throw IllegalArgumentException(
                "Cannot set aspect ratio value to a number less than or equal to 0."
            )
        }
        if (aspectRatioY <= 0) {
            throw IllegalArgumentException(
                "Cannot set aspect ratio value to a number less than or equal to 0."
            )
        }
        if (borderLineThickness < 0) {
            throw IllegalArgumentException(
                "Cannot set line thickness value to a number less than 0."
            )
        }
        if (borderCornerThickness < 0) {
            throw IllegalArgumentException(
                "Cannot set corner thickness value to a number less than 0."
            )
        }
        if (guidelinesThickness < 0) {
            throw IllegalArgumentException(
                "Cannot set guidelines thickness value to a number less than 0."
            )
        }
        if (minCropWindowHeight < 0) {
            throw IllegalArgumentException(
                "Cannot set min crop window height value to a number < 0 "
            )
        }
        if (minCropResultWidth < 0) {
            throw IllegalArgumentException("Cannot set min crop result width value to a number < 0 ")
        }
        if (minCropResultHeight < 0) {
            throw IllegalArgumentException(
                "Cannot set min crop result height value to a number < 0 "
            )
        }
        if (maxCropResultWidth < minCropResultWidth) {
            throw IllegalArgumentException(
                "Cannot set max crop result width to smaller value than min crop result width"
            )
        }
        if (maxCropResultHeight < minCropResultHeight) {
            throw IllegalArgumentException(
                "Cannot set max crop result height to smaller value than min crop result height"
            )
        }
        if (outputRequestWidth < 0) {
            throw IllegalArgumentException("Cannot set request width value to a number < 0 ")
        }
        if (outputRequestHeight < 0) {
            throw IllegalArgumentException("Cannot set request height value to a number < 0 ")
        }
        if (rotationDegrees < 0 || rotationDegrees > 360) {
            throw IllegalArgumentException(
                "Cannot set rotation degrees value to a number < 0 or > 360"
            )
        }
    }
}
