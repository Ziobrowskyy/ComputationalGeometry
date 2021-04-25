package shapes

data class Polygon(override val points: List<Point>): Shape() {
	
	override val lines: List<Line> = joinPointsWithLines()
}