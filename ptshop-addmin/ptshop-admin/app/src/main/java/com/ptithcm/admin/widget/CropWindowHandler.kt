package com.ptithcm.admin.widget

import android.graphics.RectF
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CropWindowHandler(
    var edges: RectF = RectF(),
    private var getEdges: RectF = RectF(),
    private var minCropWindowWidth: Float = 0f,
    private var minCropWindowHeight: Float = 0f,
    private var maxCropWindowWidth: Float = 0f,
    private var maxCropWindowHeight: Float = 0f,
    private var minCropResultWidth: Float = 0f,
    private var minCropResultHeight: Float = 0f,
    private var maxCropResultWidth: Float = 0f,
    private var maxCropResultHeight: Float = 0f,
    var scaleFactorWidth: Float = 1f,
    var scaleFactorHeight: Float = 1f
) {

    fun getMinCropWidth() = max(minCropWindowWidth, minCropResultWidth / scaleFactorWidth)
    fun getMinCropHeight() = max(minCropWindowHeight, minCropResultHeight / scaleFactorHeight)
    fun getMaxCropWidth() = min(maxCropWindowWidth, maxCropResultWidth / scaleFactorWidth)
    fun getMaxCropHeight() = min(maxCropWindowHeight, maxCropResultHeight / scaleFactorHeight)

    fun getRect(): RectF {
        getEdges.set(edges)
        return getEdges
    }

    fun setCropWindowLimits(maxWidth: Float, maxHeight: Float, scaleFactorWidth: Float, scaleFactorHeight: Float) {
        maxCropWindowWidth = maxWidth
        maxCropWindowHeight = maxHeight
        this.scaleFactorWidth = scaleFactorWidth
        this.scaleFactorHeight = scaleFactorHeight
    }

    fun setInitialAttributeValues(options: CropImageOptions) {
        minCropWindowWidth = options.minCropWindowWidth
        minCropWindowHeight = options.minCropWindowHeight
        minCropResultWidth = options.minCropResultWidth.toFloat()
        minCropResultHeight = options.minCropResultHeight.toFloat()
        maxCropResultWidth = options.maxCropResultWidth.toFloat()
        maxCropResultHeight = options.maxCropResultHeight.toFloat()
    }

    fun getMoveHandler(
        x: Float,
        y: Float,
        targetRadius: Float,
        cropShape: CropImageView.CropShape
    ): CropWindowMoveHandler? {
        val type =
            if (cropShape == CropImageView.CropShape.RECTANGLE)
                getRectanglePressedMoveType(x, y, targetRadius)
            else getOvalPressedMoveType()
        return if (type != null) CropWindowMoveHandler(type, this, x, y) else null
    }

    private fun getOvalPressedMoveType(): CropWindowMoveHandler.Type {
        return CropWindowMoveHandler.Type.CENTER

//        val cellLength = edges.width() / 6
//        val leftCenter = edges.left + cellLength
//        val rightCenter = edges.left + 5 * cellLength
//
//        val cellHeight = edges.height() / 6
//        val topCenter = edges.top + cellHeight
//        val bottomCenter = edges.top + 5 * cellHeight
//
//        val moveType: CropWindowMoveHandler.Type
//        when {
//            x < leftCenter -> moveType = when {
//                y < topCenter -> CropWindowMoveHandler.Type.TOP_LEFT
//                y < bottomCenter -> CropWindowMoveHandler.Type.LEFT
//                else -> CropWindowMoveHandler.Type.BOTTOM_LEFT
//            }
//            x < rightCenter -> moveType = when {
//                y < topCenter -> CropWindowMoveHandler.Type.TOP
//                y < bottomCenter -> CropWindowMoveHandler.Type.CENTER
//                else -> CropWindowMoveHandler.Type.BOTTOM
//            }
//            else -> moveType = when {
//                y < topCenter -> CropWindowMoveHandler.Type.TOP_RIGHT
//                y < bottomCenter -> CropWindowMoveHandler.Type.RIGHT
//                else -> CropWindowMoveHandler.Type.BOTTOM_RIGHT
//            }
//        }
//
//        return moveType
    }

    private fun getRectanglePressedMoveType(
        x: Float,
        y: Float,
        targetRadius: Float
    ): CropWindowMoveHandler.Type? =
        when {
            isInCornerTargetZone(x, y, edges.left, edges.top, targetRadius) ->
                CropWindowMoveHandler.Type.TOP_LEFT

            isInCornerTargetZone(x, y, edges.right, edges.top, targetRadius) ->
                CropWindowMoveHandler.Type.TOP_RIGHT

            isInCornerTargetZone(x, y, edges.left, edges.bottom, targetRadius) ->
                CropWindowMoveHandler.Type.BOTTOM_LEFT

            isInCornerTargetZone(x, y, edges.right, edges.bottom, targetRadius) ->
                CropWindowMoveHandler.Type.BOTTOM_RIGHT

            isInHorizontalTargetZone(x, y, edges.left, edges.right, edges.top, targetRadius) ->
                CropWindowMoveHandler.Type.TOP

            isInHorizontalTargetZone(x, y, edges.left, edges.right, edges.bottom, targetRadius) ->
                CropWindowMoveHandler.Type.BOTTOM

            isInVerticalTargetZone(x, y, edges.left, edges.top, edges.bottom, targetRadius) ->
                CropWindowMoveHandler.Type.LEFT

            isInVerticalTargetZone(x, y, edges.right, edges.top, edges.bottom, targetRadius) ->
                CropWindowMoveHandler.Type.RIGHT

            isInCenterTargetZone(x, y, edges.left, edges.top, edges.right, edges.bottom) ->
                CropWindowMoveHandler.Type.CENTER

            else -> null
        }

    private fun isInHorizontalTargetZone(
        x: Float,
        y: Float,
        handleXStart: Float,
        handleXEnd: Float,
        handleY: Float,
        targetRadius: Float
    ) =
        x > handleXStart && x < handleXEnd && abs(y - handleY) <= targetRadius

    private fun isInCornerTargetZone(
        x: Float,
        y: Float,
        handleX: Float,
        handleY: Float,
        targetRadius: Float
    ) = abs(x - handleX) <= targetRadius && abs(y - handleY) <= targetRadius

    private fun isInVerticalTargetZone(
        x: Float,
        y: Float,
        handleX: Float,
        handleYStart: Float,
        handleYEnd: Float,
        targetRadius: Float
    ) =
        abs(x - handleX) <= targetRadius && y > handleYStart && y < handleYEnd

    private fun isInCenterTargetZone(x: Float, y: Float, left: Float, top: Float, right: Float, bottom: Float) =
        x > left && x < right && y > top && y < bottom
}