package shapes

abstract class Shape {
	abstract val points: List<Point>
	open val lines: List<Line> = emptyList()
	open val parent: Shape? = null
	
	protected fun joinPointsWithLines(): List<Line> {
		val tmp = mutableListOf<Line>()
		for (i in points.indices) {
			tmp.add(Line(points[i], points[(i + 1) % (points.size)]))
		}
		return tmp
	}
	abstract fun replacePoint(pOld: Point, pNew: Point): Shape
}