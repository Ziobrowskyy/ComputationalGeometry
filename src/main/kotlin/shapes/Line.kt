package shapes

data class Line(val p1: Point = Point(), val p2: Point = Point(), val parent: Shape? = null) {
	infix operator fun plus(other: Line) = Line(p1 + other.p1, p2 + other.p2)
	infix operator fun plus(other: Point) = Line(p1 + other, p2 + other)
}