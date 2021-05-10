package shapes

abstract class Shape {
	open val points: List<Point> = emptyList()
	open val lines: List<Line> = emptyList()
	open val parent: Shape? = null

	protected fun joinPointsWithLines(points:List<Point> = this.points): List<Line> {
		val tmp = mutableListOf<Line>()
		for (i in points.indices) {
			tmp.add(Line(points[i], points[(i + 1) % (points.size)]))
		}
		return tmp
	}

	abstract fun replacePoint(pOld: Point, pNew: Point): Shape
}