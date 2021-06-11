package calc

import canvasHeight
import canvasWidth
import shapes.Point
import shapes.Rectangle
import kotlin.math.pow

class QuadTree(val p0: Point, initPoints: MutableList<Point>, val depth: Int = 0) {
    val width = canvasWidth / (2.0.pow(depth))
    val height = canvasHeight / (2.0.pow(depth))
    val boundingBox = Rectangle(p0, width, height)
    val points = mutableListOf<Point>()

    var hasChildren = false
    val children = arrayOfNulls<QuadTree>(4)

    init {
        initPoints.filter { boundingBox.isInside(it) }.map(::add)
    }

    companion object {
        const val pointThreshold = 4
        const val maxDepth = 5
    }

    fun getShapes(): List<Rectangle> {
        val shapes = mutableListOf(boundingBox)
        if (hasChildren) {
            children.forEach { if (it != null) shapes.addAll(it.getShapes()) }
        }
        return shapes
    }

    fun add(point: Point) {
        if (!boundingBox.isInside(point))
            return
        points.add(point)

        if (points.size < pointThreshold || depth >= maxDepth)
            return

        if (!hasChildren) {
            hasChildren = true
            //NW child
            children[0] = QuadTree(p0, points, depth + 1)
            //NE child
            children[1] = QuadTree(p0.add(width / 2, 0.0), points, depth + 1)
            //SW child
            children[2] = QuadTree(p0.add(0.0, height / 2), points, depth + 1)
            //SE child
            children[3] = QuadTree(p0.add(width / 2, height / 2), points, depth + 1)
        } else {
            children.forEach { it!!.add(point) }
        }
    }
}
