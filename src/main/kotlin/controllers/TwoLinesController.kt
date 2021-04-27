package controllers

import shapes.Line
import shapes.Point
import shapes.Shape

class TwoLinesController : CanvasController() {
	var crossingPoint: Point? = null
	
	init {
		mode = InputMode.MOVE
		disablePointRemove = true
		
		val l1 = Line(
			Point.randomFromCanvasSize(),
			Point.randomFromCanvasSize()
		)
		val l2 = Line(
			Point.randomFromCanvasSize(),
			Point.randomFromCanvasSize()
		)
		addLine(l1)
		addLine(l2)
		update()
	}
	
	private fun addLine(l: Line) {
		points.addAll(l.points)
		lines.add(l)
		shapes.add(l)
	}
	
	override fun update() {
		lines.clear()
		shapes.filterIsInstance<Line>().forEach {
			lines.add(it)
		}
		points.remove(crossingPoint)
		val l1 = lines[0]
		val l2 = lines[1]
		crossingPoint =Line.getCrossingPoint(l1, l2)
		if (l1.inBounds(crossingPoint!!) || l2.inBounds(crossingPoint!!))
			points.add(crossingPoint)
	}
}