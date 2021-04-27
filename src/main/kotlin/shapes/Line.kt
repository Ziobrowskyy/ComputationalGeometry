package shapes

import kotlin.math.max
import kotlin.math.min

data class Line(val p1: Point = Point(), val p2: Point = Point(), override val parent: Shape? = null) : Shape() {
	override val points = listOf(p1.copy(parent = this), p2.copy(parent = this))
	
	companion object {
		
		fun getCrossingPoint(l1: Line, l2: Line): Point {
			data class LineStandardForm(val a: Double, val b: Double, val c: Double)
			
			val ls1 = with(l1.getEquationCoof()) {
				LineStandardForm(this.first, -1.0, this.second)
			}
			val ls2 = with(l2.getEquationCoof()) {
				LineStandardForm(this.first, -1.0, this.second)
			}
			val w = ls1.a * ls2.b - ls2.a * ls1.b
			val x = (-ls1.c * ls2.b + ls2.c * ls1.b) / w
			val y = (-ls1.a * ls2.c + ls2.a * ls1.c) / w
			return Point(x, y)
		}
		
	}
	
	override fun replacePoint(pOld: Point, pNew: Point): Line {
		val nPoints = points.map { if (it == pOld) pNew else it }
		assert(nPoints.size == 2)
		return Line(nPoints[0], nPoints[1])
	}
	
	infix operator fun plus(other: Line) = Line(p1 + other.p1, p2 + other.p2)
	infix operator fun plus(other: Point) = Line(p1 + other, p2 + other)
	
	fun rotate(angle: Double, p: Point = Point()) = Line(p1.rotate(angle, p), p2.rotate(angle, p))
	
	fun getEquationCoof(): Pair<Double, Double> {
		val a = (p1.y - p2.y) / (p1.x - p2.x)
		val b = p1.y - a * p1.x
		return Pair(a, b);
	}
	
	fun inBounds(p: Point): Boolean {
		return p.x <= max(p1.x, p2.x) && p.x >= min(p1.x, p2.x) &&
				p.y <= max(p1.y, p2.y) && p.y >= min(p1.y, p2.y)
	}
}