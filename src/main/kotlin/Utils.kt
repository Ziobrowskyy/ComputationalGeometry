import javafx.scene.image.PixelReader
import kotlin.math.abs

fun Double.eq(other: Double, eps: Double = 0.0001): Boolean {
    return if (abs(this - other) < eps)
        true
    else
        abs(this / other - 1) < eps
}

fun PixelReader.averageBrightness(x0: Int, y0: Int, width: Int, height: Int): Double {
    var sum = 0.0
    for (x in 0 until width) {
        for (y in 0 until height) {
            sum += getColor(x0 + x, y0 + y).brightness
        }
    }
    return sum / (width * height)
}