package shapes

data class Polygon(override val points: MutableList<Point>): Shape() {
	init {
		points.replaceAll { it.copy(parent = this) }
	}
	override val lines: List<Line> = joinPointsWithLines()
	override fun replacePoint(pOld: Point, pNew: Point): Polygon {
		return copy(points = points.map { if(it == pOld) pNew else it} as MutableList<Point>)
	}
	
}