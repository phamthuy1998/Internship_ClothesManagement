package com.n16dccn159.admin.widget

import android.graphics.PointF
import android.graphics.RectF
import kotlin.math.max
import kotlin.math.min

class CropWindowMoveHandler(
    private val type: Type?,
    cropWindowHandler: CropWindowHandler,
    touchX: Float,
    touchY: Float
) {

    private val minCropWidth: Float = cropWindowHandler.getMinCropWidth()
    private val minCropHeight: Float = cropWindowHandler.getMinCropHeight()
    private val maxCropWidth: Float = cropWindowHandler.getMaxCropWidth()
    private val maxCropHeight: Float = cropWindowHandler.getMaxCropHeight()
    private val touchOffset = PointF()

    init {
        calculateTouchOffset(cropWindowHandler.getRect(), touchX, touchY)
    }

    fun move(
        rect: RectF,
        x: Float,
        y: Float,
        bounds: RectF,
        viewWidth: Int,
        viewHeight: Int,
        snapMargin: Float,
        fixedAspectRatio: Boolean,
        aspectRatio: Float
    ) {
        val adjX = x + touchOffset.x
        val adjY = y + touchOffset.y

        when {
            type == Type.CENTER ->
                moveCenter(rect, adjX, adjY, bounds, viewWidth, viewHeight, snapMargin)
            fixedAspectRatio ->
                moveSizeWithFixedAspectRatio(rect, adjX, adjY, bounds, viewWidth, viewHeight, snapMargin, aspectRatio)
            !fixedAspectRatio ->
                moveSizeWithFreeAspectRatio(rect, adjX, adjY, bounds, viewWidth, viewHeight, snapMargin)
        }
    }

    private fun moveCenter(
        rect: RectF,
        x: Float,
        y: Float,
        bounds: RectF,
        viewWidth: Int,
        viewHeight: Int,
        snapRadius: Float
    ) {
        var dx = x - rect.centerX()
        var dy = y - rect.centerY()
        if (rect.left + dx < 0 ||
            rect.right + dx > viewWidth ||
            rect.left + dx < bounds.left ||
            rect.right + dx > bounds.right
        ) {
            dx /= 1.05f
            touchOffset.x -= dx / 2
        }
        if (rect.top + dy < 0 ||
            rect.bottom + dy > viewHeight ||
            rect.top + dy < bounds.top ||
            rect.bottom + dy > bounds.bottom
        ) {
            dy /= 1.05f
            touchOffset.y -= dy / 2
        }
        rect.offset(dx, dy)
        snapEdgesToBounds(rect, bounds, snapRadius)
    }

    private fun moveSizeWithFreeAspectRatio(
        rect: RectF,
        x: Float,
        y: Float,
        bounds: RectF,
        viewWidth: Int,
        viewHeight: Int,
        snapMargin: Float
    ) {
        when (type) {
            Type.TOP_LEFT -> {
                adjustTop(rect, y, bounds, snapMargin, 0f, leftMoves = false, rightMoves = false)
                adjustLeft(rect, x, bounds, snapMargin, 0f, topMoves = false, bottomMoves = false)
            }

            Type.TOP_RIGHT -> {
                adjustTop(rect, y, bounds, snapMargin, 0f, leftMoves = false, rightMoves = false)
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0f, topMoves = false, bottomMoves = false)
            }

            Type.BOTTOM_LEFT -> {
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0f, leftMoves = false, rightMoves = false)
                adjustLeft(rect, x, bounds, snapMargin, 0f, topMoves = false, bottomMoves = false)
            }

            Type.BOTTOM_RIGHT -> {
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0f, leftMoves = false, rightMoves = false)
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0f, topMoves = false, bottomMoves = false)
            }

            Type.LEFT ->
                adjustLeft(rect, x, bounds, snapMargin, 0f, topMoves = false, bottomMoves = false)

            Type.TOP ->
                adjustTop(rect, y, bounds, snapMargin, 0f, leftMoves = false, rightMoves = false)

            Type.RIGHT ->
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0f, topMoves = false, bottomMoves = false)

            Type.BOTTOM ->
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0f, leftMoves = false, rightMoves = false)
            else -> {
            }
        }
    }

    /** Check if edges have gone out of bounds (including snap margin), and fix if needed.  */
    private fun snapEdgesToBounds(edges: RectF, bounds: RectF, margin: Float) {
        if (edges.left < bounds.left + margin)
            edges.offset(bounds.left - edges.left, 0f)

        if (edges.top < bounds.top + margin)
            edges.offset(0f, bounds.top - edges.top)

        if (edges.right > bounds.right - margin)
            edges.offset(bounds.right - edges.right, 0f)

        if (edges.bottom > bounds.bottom - margin)
            edges.offset(0f, bounds.bottom - edges.bottom)
    }

    private fun calculateTouchOffset(rect: RectF, touchX: Float, touchY: Float) {
        var touchOffsetX = 0f
        var touchOffsetY = 0f

        // Calculate the offset from the appropriate handle.
        when (type) {
            Type.TOP_LEFT -> {
                touchOffsetX = rect.left - touchX
                touchOffsetY = rect.top - touchY
            }
            Type.TOP_RIGHT -> {
                touchOffsetX = rect.right - touchX
                touchOffsetY = rect.top - touchY
            }
            Type.BOTTOM_LEFT -> {
                touchOffsetX = rect.left - touchX
                touchOffsetY = rect.bottom - touchY
            }
            Type.BOTTOM_RIGHT -> {
                touchOffsetX = rect.right - touchX
                touchOffsetY = rect.bottom - touchY
            }
            Type.LEFT -> {
                touchOffsetX = rect.left - touchX
                touchOffsetY = 0f
            }
            Type.TOP -> {
                touchOffsetX = 0f
                touchOffsetY = rect.top - touchY
            }
            Type.RIGHT -> {
                touchOffsetX = rect.right - touchX
                touchOffsetY = 0f
            }
            Type.BOTTOM -> {
                touchOffsetX = 0f
                touchOffsetY = rect.bottom - touchY
            }
            Type.CENTER -> {
                touchOffsetX = rect.centerX() - touchX
                touchOffsetY = rect.centerY() - touchY
            }
        }

        touchOffset.x = touchOffsetX
        touchOffset.y = touchOffsetY
    }

    private fun moveSizeWithFixedAspectRatio(
        rect: RectF,
        x: Float,
        y: Float,
        bounds: RectF,
        viewWidth: Int,
        viewHeight: Int,
        snapMargin: Float,
        aspectRatio: Float
    ) = when (type) {
        Type.TOP_LEFT ->
            if (calculateAspectRatio(x, y, rect.right, rect.bottom) < aspectRatio) {
                adjustTop(rect, y, bounds, snapMargin, aspectRatio, leftMoves = true, rightMoves = false)
                adjustLeftByAspectRatio(rect, aspectRatio)
            } else {
                adjustLeft(rect, x, bounds, snapMargin, aspectRatio, topMoves = true, bottomMoves = false)
                adjustTopByAspectRatio(rect, aspectRatio)
            }

        Type.TOP_RIGHT ->
            if (calculateAspectRatio(rect.left, y, x, rect.bottom) < aspectRatio) {
                adjustTop(rect, y, bounds, snapMargin, aspectRatio, leftMoves = false, rightMoves = true)
                adjustRightByAspectRatio(rect, aspectRatio)
            } else {
                adjustRight(
                    rect, x, bounds, viewWidth, snapMargin, aspectRatio,
                    topMoves = true,
                    bottomMoves = false
                )
                adjustTopByAspectRatio(rect, aspectRatio)
            }

        Type.BOTTOM_LEFT ->
            if (calculateAspectRatio(x, rect.top, rect.right, y) < aspectRatio) {
                adjustBottom(
                    rect, y, bounds, viewHeight, snapMargin, aspectRatio,
                    leftMoves = true,
                    rightMoves = false
                )
                adjustLeftByAspectRatio(rect, aspectRatio)
            } else {
                adjustLeft(rect, x, bounds, snapMargin, aspectRatio, topMoves = false, bottomMoves = true)
                adjustBottomByAspectRatio(rect, aspectRatio)
            }

        Type.BOTTOM_RIGHT ->
            if (calculateAspectRatio(rect.left, rect.top, x, y) < aspectRatio) {
                adjustBottom(
                    rect, y, bounds, viewHeight, snapMargin, aspectRatio,
                    leftMoves = false,
                    rightMoves = true
                )
                adjustRightByAspectRatio(rect, aspectRatio)
            } else {
                adjustRight(
                    rect, x, bounds, viewWidth, snapMargin, aspectRatio,
                    topMoves = false,
                    bottomMoves = true
                )
                adjustBottomByAspectRatio(rect, aspectRatio)
            }

        Type.LEFT -> {
            adjustLeft(rect, x, bounds, snapMargin, aspectRatio, topMoves = true, bottomMoves = true)
            adjustTopBottomByAspectRatio(rect, bounds, aspectRatio)
        }

        Type.TOP -> {
            adjustTop(rect, y, bounds, snapMargin, aspectRatio, leftMoves = true, rightMoves = true)
            adjustLeftRightByAspectRatio(rect, bounds, aspectRatio)
        }

        Type.RIGHT -> {
            adjustRight(rect, x, bounds, viewWidth, snapMargin, aspectRatio, topMoves = true, bottomMoves = true)
            adjustTopBottomByAspectRatio(rect, bounds, aspectRatio)
        }

        Type.BOTTOM -> {
            adjustBottom(rect, y, bounds, viewHeight, snapMargin, aspectRatio, leftMoves = true, rightMoves = true)
            adjustLeftRightByAspectRatio(rect, bounds, aspectRatio)
        }
        else -> {
        }
    }

    private fun calculateAspectRatio(left: Float, top: Float, right: Float, bottom: Float): Float =
        (right - left) / (bottom - top)

    private fun adjustLeftByAspectRatio(rect: RectF, aspectRatio: Float) {
        rect.left = rect.right - rect.height() * aspectRatio
    }

    /**
     * Adjust top edge by current crop window width and the given aspect ratio, the bottom edge
     * remains in position while the top adjusts to keep aspect ratio to the width.
     */
    private fun adjustTopByAspectRatio(rect: RectF, aspectRatio: Float) {
        rect.top = rect.bottom - rect.width() / aspectRatio
    }

    /**
     * Adjust right edge by current crop window height and the given aspect ratio, the left edge
     * remains in position while the left adjusts to keep aspect ratio to the height.
     */
    private fun adjustRightByAspectRatio(rect: RectF, aspectRatio: Float) {
        rect.right = rect.left + rect.height() * aspectRatio
    }

    /**
     * Adjust bottom edge by current crop window width and the given aspect ratio, the top edge
     * remains in position while the top adjusts to keep aspect ratio to the width.
     */
    private fun adjustBottomByAspectRatio(rect: RectF, aspectRatio: Float) {
        rect.bottom = rect.top + rect.width() / aspectRatio
    }

    /**
     * Adjust left and right edges by current crop window height and the given aspect ratio, both
     * right and left edges adjusts equally relative to center to keep aspect ratio to the height.
     */
    private fun adjustLeftRightByAspectRatio(rect: RectF, bounds: RectF, aspectRatio: Float) {
        rect.inset((rect.width() - rect.height() * aspectRatio) / 2, 0f)
        if (rect.left < bounds.left)
            rect.offset(bounds.left - rect.left, 0f)

        if (rect.right > bounds.right)
            rect.offset(bounds.right - rect.right, 0f)
    }

    /**
     * Adjust top and bottom edges by current crop window width and the given aspect ratio, both top
     * and bottom edges adjusts equally relative to center to keep aspect ratio to the width.
     */
    private fun adjustTopBottomByAspectRatio(rect: RectF, bounds: RectF, aspectRatio: Float) {
        rect.inset(0f, (rect.height() - rect.width() / aspectRatio) / 2)
        if (rect.top < bounds.top)
            rect.offset(0f, bounds.top - rect.top)

        if (rect.bottom > bounds.bottom)
            rect.offset(0f, bounds.bottom - rect.bottom)
    }

    private fun adjustLeft(
        rect: RectF,
        left: Float,
        bounds: RectF,
        snapMargin: Float,
        aspectRatio: Float,
        topMoves: Boolean,
        bottomMoves: Boolean
    ) {
        var newLeft = left

        if (newLeft < 0) {
            newLeft /= 1.05f
            touchOffset.x -= newLeft / 1.1f
        }

        if (newLeft < bounds.left)
            touchOffset.x -= (newLeft - bounds.left) / 2f

        if (newLeft - bounds.left < snapMargin)
            newLeft = bounds.left

        if (rect.right - newLeft < minCropWidth)
            newLeft = rect.right - minCropWidth

        if (rect.right - newLeft > maxCropWidth)
            newLeft = rect.right - maxCropWidth

        if (newLeft - bounds.left < snapMargin)
            newLeft = bounds.left

        if (aspectRatio > 0) {
            var newHeight = (rect.right - newLeft) / aspectRatio

            // Checks if the window is too small vertically
            if (newHeight < minCropHeight) {
                newLeft = max(bounds.left, rect.right - minCropHeight * aspectRatio)
                newHeight = (rect.right - newLeft) / aspectRatio
            }

            if (newHeight > maxCropHeight) {
                newLeft = max(bounds.left, rect.right - maxCropHeight * aspectRatio)
                newHeight = (rect.right - newLeft) / aspectRatio
            }

            if (topMoves && bottomMoves) {
                newLeft = max(newLeft, max(bounds.left, rect.right - bounds.height() * aspectRatio))
            } else {
                if (topMoves && rect.bottom - newHeight < bounds.top) {
                    newLeft = max(bounds.left, rect.right - (rect.bottom - bounds.top) * aspectRatio)
                    newHeight = (rect.right - newLeft) / aspectRatio
                }

                if (bottomMoves && rect.top + newHeight > bounds.bottom) {
                    newLeft = max(newLeft, max(bounds.left, rect.right - (bounds.bottom - rect.top) * aspectRatio))
                }
            }
        }

        rect.left = newLeft
    }

    private fun adjustRight(
        rect: RectF,
        right: Float,
        bounds: RectF,
        viewWidth: Int,
        snapMargin: Float,
        aspectRatio: Float,
        topMoves: Boolean,
        bottomMoves: Boolean
    ) {

        var newRight = right

        if (newRight > viewWidth) {
            newRight = viewWidth + (newRight - viewWidth) / 1.05f
            touchOffset.x -= (newRight - viewWidth) / 1.1f
        }

        if (newRight > bounds.right)
            touchOffset.x -= (newRight - bounds.right) / 2f

        if (bounds.right - newRight < snapMargin)
            newRight = bounds.right

        if (newRight - rect.left < minCropWidth) {
            newRight = rect.left + minCropWidth
        }

        if (newRight - rect.left > maxCropWidth)
            newRight = rect.left + maxCropWidth

        if (bounds.right - newRight < snapMargin)
            newRight = bounds.right

        if (aspectRatio > 0) {
            var newHeight = (newRight - rect.left) / aspectRatio

            if (newHeight < minCropHeight) {
                newRight = min(bounds.right, rect.left + minCropHeight * aspectRatio)
                newHeight = (newRight - rect.left) / aspectRatio
            }

            if (newHeight > maxCropHeight) {
                newRight = min(bounds.right, rect.left + maxCropHeight * aspectRatio)
                newHeight = (newRight - rect.left) / aspectRatio
            }

            if (topMoves && bottomMoves) {
                newRight = min(newRight, min(bounds.right, rect.left + bounds.height() * aspectRatio))
            } else {
                if (topMoves && rect.bottom - newHeight < bounds.top) {
                    newRight = min(bounds.right, rect.left + (rect.bottom - bounds.top) * aspectRatio)
                    newHeight = (newRight - rect.left) / aspectRatio
                }

                if (bottomMoves && rect.top + newHeight > bounds.bottom) {
                    newRight = min(newRight, min(bounds.right, rect.left + (bounds.bottom - rect.top) * aspectRatio))
                }
            }
        }

        rect.right = newRight
    }

    private fun adjustTop(
        rect: RectF,
        top: Float,
        bounds: RectF,
        snapMargin: Float,
        aspectRatio: Float,
        leftMoves: Boolean,
        rightMoves: Boolean
    ) {

        var newTop = top

        if (newTop < 0) {
            newTop /= 1.05f
            touchOffset.y -= newTop / 1.1f
        }

        if (newTop < bounds.top)
            touchOffset.y -= (newTop - bounds.top) / 2f

        if (newTop - bounds.top < snapMargin)
            newTop = bounds.top

        if (rect.bottom - newTop < minCropHeight)
            newTop = rect.bottom - minCropHeight

        if (rect.bottom - newTop > maxCropHeight)
            newTop = rect.bottom - maxCropHeight

        if (newTop - bounds.top < snapMargin)
            newTop = bounds.top

        if (aspectRatio > 0) {
            var newWidth = (rect.bottom - newTop) * aspectRatio

            if (newWidth < minCropWidth) {
                newTop = max(bounds.top, rect.bottom - minCropWidth / aspectRatio)
                newWidth = (rect.bottom - newTop) * aspectRatio
            }

            if (newWidth > maxCropWidth) {
                newTop = max(bounds.top, rect.bottom - maxCropWidth / aspectRatio)
                newWidth = (rect.bottom - newTop) * aspectRatio
            }

            if (leftMoves && rightMoves) {
                newTop = max(newTop, max(bounds.top, rect.bottom - bounds.width() / aspectRatio))
            } else {
                if (leftMoves && rect.right - newWidth < bounds.left) {
                    newTop = max(bounds.top, rect.bottom - (rect.right - bounds.left) / aspectRatio)
                    newWidth = (rect.bottom - newTop) * aspectRatio
                }

                if (rightMoves && rect.left + newWidth > bounds.right) {
                    newTop = max(newTop, max(bounds.top, rect.bottom - (bounds.right - rect.left) / aspectRatio))
                }
            }
        }

        rect.top = newTop
    }

    /**
     * Get the resulting y-position of the bottom edge of the crop window given the handle's position
     * and the image's bounding box and snap radius.
     *
     * @param bottom the position that the bottom edge is dragged to
     * @param bounds the bounding box of the image that is being cropped
     * @param viewHeight
     * @param snapMargin the snap distance to the image edge (in pixels)
     */
    private fun adjustBottom(
        rect: RectF,
        bottom: Float,
        bounds: RectF,
        viewHeight: Int,
        snapMargin: Float,
        aspectRatio: Float,
        leftMoves: Boolean,
        rightMoves: Boolean
    ) {

        var newBottom = bottom

        if (newBottom > viewHeight) {
            newBottom = viewHeight + (newBottom - viewHeight) / 1.05f
            touchOffset.y -= (newBottom - viewHeight) / 1.1f
        }

        if (newBottom > bounds.bottom)
            touchOffset.y -= (newBottom - bounds.bottom) / 2f

        if (bounds.bottom - newBottom < snapMargin)
            newBottom = bounds.bottom

        if (newBottom - rect.top < minCropHeight)
            newBottom = rect.top + minCropHeight

        if (newBottom - rect.top > maxCropHeight)
            newBottom = rect.top + maxCropHeight

        if (bounds.bottom - newBottom < snapMargin)
            newBottom = bounds.bottom

        if (aspectRatio > 0) {
            var newWidth = (newBottom - rect.top) * aspectRatio

            if (newWidth < minCropWidth) {
                newBottom = min(bounds.bottom, rect.top + minCropWidth / aspectRatio)
                newWidth = (newBottom - rect.top) * aspectRatio
            }

            if (newWidth > maxCropWidth) {
                newBottom = min(bounds.bottom, rect.top + maxCropWidth / aspectRatio)
                newWidth = (newBottom - rect.top) * aspectRatio
            }

            if (leftMoves && rightMoves) {
                newBottom = min(newBottom, min(bounds.bottom, rect.top + bounds.width() / aspectRatio))
            } else {
                if (leftMoves && rect.right - newWidth < bounds.left) {
                    newBottom = min(bounds.bottom, rect.top + (rect.right - bounds.left) / aspectRatio)
                    newWidth = (newBottom - rect.top) * aspectRatio
                }

                if (rightMoves && rect.left + newWidth > bounds.right) {
                    newBottom = min(newBottom, min(bounds.bottom, rect.top + (bounds.right - rect.left) / aspectRatio))
                }
            }
        }

        rect.bottom = newBottom
    }

    enum class Type {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        CENTER
    }
}