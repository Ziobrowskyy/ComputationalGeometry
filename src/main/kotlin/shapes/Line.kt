package shapes

data class Line(val p1: Point = Point(), val p2: Point = Point(), override val parent: Shape? = null): Shape() {
	override val points = listOf(p1.copy(parent = this), p2.copy(parent = this))

	override fun replacePoint(pOld: Point, pNew: Point): Line {
		val nPoints = points.map { if(it == pOld) pNew else it }
		assert(nPoints.size == 2)
		return Line(nPoints[0], nPoints[1])
	}
	
	infix operator fun plus(other: Line) = Line(p1 + other.p1, p2 + other.p2)
	infix operator fun plus(other: Point) = Line(p1 + other, p2 + other)
	
	fun rotate(angle: Double, p: Point = Point()) = Line(p1.rotate(angle, p), p2.rotate(angle, p))
	
	fun getEquationCoof(): Pair<Double,Double> {
		val a = (p1.y - p2.y) / (p1.x-p2.x)
		val b = p1.y - a * p1.x
		return Pair(a,b);
	}
}