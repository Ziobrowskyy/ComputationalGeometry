package controllers

import calc.Hull
import shapes.Point
import shapes.Polygon
import shapes.Shape

class HullController: CanvasController() {
	var hullState: Boolean = false
	
	init {
		points.add(Point(-100, -100))
		points.add(Point(-100, 100))
		points.add(Point(100, -100))
		points.add(Point(100, 100))
	}
	
	fun changeHullMode() {
		hullState = !hullState
		updateHull()
	}
	override fun update() {
		updateHull()
	}
	
	private fun updateHull() {
		lines.clear()
		if (hullState && points.size >= 2) {
			val hullPoints = Hull.graham(points)
			val hullLines = Polygon(hullPoints as MutableList<Point>).lines
			lines.addAll(hullLines)
			println("Lines size: " + lines.size)
			println("Points size: " + points.size)
		}
	}
}