package com.makescreenshot.deletethis.data.model

import android.graphics.*

class ImageProcessor {

    fun applyFilter(bitmap: Bitmap, onImageColored: (Bitmap) -> Unit) {
        val filteredBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(filteredBitmap)
        val paint = Paint().apply {
            colorFilter = ColorMatrixColorFilter(createGrayscaleColorMatrix())
        }
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        onImageColored(filteredBitmap)
    }

    private fun createGrayscaleColorMatrix(): ColorMatrix {
        val matrix = ColorMatrix()
        matrix.setSaturation(0f) // Встановлюємо насиченість в 0 для чорно-білого ефекту
        return matrix
    }
}