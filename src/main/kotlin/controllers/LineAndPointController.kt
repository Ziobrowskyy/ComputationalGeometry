package controllers

import calc.Hull
import eq
import kotlinx.coroutines.selects.select
import shapes.Line
import shapes.Point
import shapes.Polygon
import shapes.Shape
import java.util.*

class LineAndPointController : MainController() {
	/*
i. Obrót półprostej względem punktu,
ii. Obliczenie punktu przecięcia dwóch linii,
iii. Wyznaczenie równania funkcji przechodzącej przez dwa punkty,
iv. Określenie położenia punktu względem linii,
	 */
	enum class LinesMode {
		ROTATE_LINE,
		CROSS_POINT,
		POINT_POSITION
	}
	
	var point: Point? = null
	
	init {
		changeSelectMode()
		point = Point.randomFromCanvasSize()
		points.add(point)
		val l = Line(
			Point.randomFromCanvasSize(),
			Point.randomFromCanvasSize()
		)
		addLine(l)
	}
	
	fun addLine(l: Line) {
		points.addAll(l.points)
		lines.add(l)
		shapes.add(l)
	}
	
	fun removeLine(l: Line) {
		points.removeIf { l.points.contains(it) }
		lines.remove(l)
		shapes.remove(l)
		update()
	}
	
	fun calculateLineEquation() {
	
	}
	
	override fun update() {
		lines.clear()
		shapes.filterIsInstance<Line>().forEach {
			lines.add(it)
		}
		point = points.first { p ->
			shapes.forEach {
				if (it.points.contains(p))
					return@first false
			}
			return@first true
		}
	}
	
	override fun updateShape(s: Shape, oldPoint: Point, newPoint: Point) {
//		points.removeIf { s.points.contains(it) && it != newPoint }
		points.removeIf { s.points.contains(it) }
		shapes.remove(s)
		Selection.selectedShape = s.replacePoint(oldPoint, newPoint)
		shapes.add(Selection.selectedShape)
//		points.addAll(Selection.selectedShape!!.points.filter { it != newPoint })
		points.addAll(Selection.selectedShape!!.points)
		
	}
	
	fun getSideOfLineString(): String {
		val line = lines.first()
		val p = line.p1
		val q = line.p2
		val r = point!!
		val angle = Point.angle(p, q, r)
		val angleTolerance = .1
		return when {
			(angle%Math.PI).eq(0.0, angleTolerance) || (angle%Math.PI).eq(Math.PI, angleTolerance)-> "on line"
			angle > Math.PI -> "left of line"
			else -> "right of line"
		}
	}
	
	fun getLineEquationString(): String {
		val line = lines.first()
		val (a, b) = line.getEquationCoof()
		return String.format(Locale.ENGLISH, "y = %.4f * x + %.4f", a, b)
	}
	
	fun rotateLineByPoint(angleString: String) {
		val angle = (angleString.toDoubleOrNull() ?: 90.0) / 180 * Math.PI
		val line = lines.first()
		val p = point!!
		val rotated = line.rotate(angle, p)
		removeLine(line)
		addLine(rotated)
	}
}
