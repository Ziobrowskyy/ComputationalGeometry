package calc

import averageBrightness
import javafx.scene.image.Image
import shapes.Point
import shapes.Rectangle
import kotlin.math.ceil
import kotlin.math.floor

class QuadTreePicture(
    val p0: Point,
    val image: Image,
    val depth: Int = 0,
    val width: Double = image.width,
    val height: Double = image.height
) {
    //        private val width = image.width / (2.0.pow(depth))
//        private val height = image.height / (2.0.pow(depth))
    val boundingBox = Rectangle(p0 - Point(image.width / 2, image.height / 2), width, height)
    var hasChildren = false
    val children = arrayOfNulls<QuadTreePicture>(4)

    companion object {
        val maxDepth = 7
    }

    init {
        val averageColor =
            image.pixelReader.averageBrightness(p0.x.toInt(), p0.y.toInt(), width.toInt(), height.toInt())
        if (averageColor != 0.0 && averageColor != 1.0) {
            divide()
        }
    }

    fun getShapes(): List<Rectangle> {
        return if (hasChildren)
            children.flatMap { it!!.getShapes() }
        else
            listOf(boundingBox)
    }

    private fun divide() {
        if (width < 5 || height < 5)
        if (depth >= maxDepth)
            return

        hasChildren = true
        //NW child
        children[0] = QuadTreePicture(
            p0,
            image, depth + 1,
            floor(width / 2), floor(height / 2)
        )
        //NE child
        children[1] = QuadTreePicture(
            p0.add(floor(width / 2), 0.0),
            image, depth + 1,
            ceil(width / 2), floor(height / 2)
        )
        //SW child
        children[2] = QuadTreePicture(
            p0.add(0.0, floor(height / 2)),
            image, depth + 1,
            floor(width / 2), ceil(height / 2)
        )
        //SE child
        children[3] = QuadTreePicture(
            p0.add(floor(width / 2), floor(height / 2)),
            image, depth + 1,
            ceil(width / 2), ceil(height / 2)
        )
    }

}
