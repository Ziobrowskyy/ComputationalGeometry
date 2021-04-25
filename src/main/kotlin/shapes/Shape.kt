package shapes

abstract class Shape {
	abstract val points: List<Point>
	open val lines: List<Line> = emptyList()
	
	protected fun joinPointsWithLines(): List<Line> {
		val tmp = mutableListOf<Line>()
		for (i in points.indices) {
//            println("$i, ${(i + 1) % (points.size)}")
//            println("${points[i]}, ${points[(i + 1) % (points.size)]}")
			tmp.add(Line(points[i], points[(i + 1) % (points.size)]))
		}
		return tmp
	}
}