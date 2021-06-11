package shapes

import canvasHeight
import canvasWidth
import java.util.*
import kotlin.math.*
import kotlin.random.Random

typealias Vector = Point

data class Point(val x: Double = 0.0, val y: Double = 0.0, val radius: Double = 10.0, val parent: Shape? = null) {
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())


    companion object {
        fun angle(p: Point, q: Point, r: Point): Double {
            val a = p.vectorTo(q)
            val b = p.vectorTo(r)
            val alfa = acos((a dot b) / (a.len * b.len))
            return if (det(p, q, r) < 0) 2 * Math.PI - alfa else alfa
        }

        fun det(p: Point, q: Point, r: Point): Double {
            return p.x * q.y + p.y * r.x + q.x * r.y - (r.x * q.y + r.y * p.x + q.x * p.y)
        }

        fun randomFromCanvasSize(radius: Double = 10.0) = Point(
            x = Random.nextDouble(-canvasWidth / 2, canvasWidth / 2),
            y = Random.nextDouble(-canvasHeight / 2, canvasHeight / 2),
            radius
        )

        fun canvasTopLeft() = Point(-canvasWidth / 2, -canvasHeight / 2)

        val zero = Point(0,0)
    }

    override fun toString(): String {
        return String.format(Locale.ENGLISH, "Point(x=%.2f, y=%.2f)", x, y)
    }

    val len: Double
        get() = sqrt(x.pow(2) + y.pow(2))

    fun add(x: Double = 0.0, y: Double = 0.0) = Point(this.x + x, this.y + y)

    infix operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)

    infix operator fun minus(other: Point) = Point(this.x - other.x, this.y - other.y)

    override infix operator fun equals(other: Any?) = (other is Point) && this.x == other.x && this.y == other.y

    infix fun dot(other: Point) = this.x * other.x + this.y * other.y

    infix fun cross(other: Point) = this.x * other.y - this.y * other.x

    fun distTo(other: Point) = sqrt((this.x - other.x).pow(2) + (this.y - other.y).pow(2))

    fun vectorTo(other: Point) = Vector(other.x - this.x, other.y - this.y)

    fun rotate(angle: Double, other: Point = Point()): Point {
        return this.copy(
            x = (x - other.x) * cos(angle) - (y - other.y) * sin(angle) + other.x,
            y = (x - other.x) * sin(angle) + (y - other.y) * cos(angle) + other.y
        )
    }
}


