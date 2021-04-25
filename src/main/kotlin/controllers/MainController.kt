package controllers

import calc.Hull
import canvasHeight
import canvasWidth
import javafx.collections.ListChangeListener
import javafx.scene.input.MouseEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import shapes.*
import tornadofx.Controller
import tornadofx.observableListOf
import tornadofx.runLater
import kotlin.math.abs

enum class InputMode {
	INSERT,
	MOVE
}

class MainController : Controller() {
	val points = observableListOf<Point>()
	val lines = observableListOf<Line>()
	val shapes = observableListOf<Shape>()
	
	var mode: InputMode = InputMode.INSERT
	var hullState: Boolean = false
	
	object Selection {
		var selectedPoint: Point? = null
	}
	
	init {
		points.add(Point(-100, -100))
		points.add(Point(-100, 100))
		points.add(Point(100, -100))
		points.add(Point(100, 100))
//		for (i in 0 until 10) {
//			points.add(Point.randomFromCanvasSize())
//		}
	}
	
	init {
		lines.addListener(ListChangeListener {
			println("Lines upated")
		})
	}
	
	fun reload() {
//		points.clear()
//		lines.clear()
//		shapes.clear()
//		Selection.selectedPoint = null
	}
	
	fun mousePressed(evt: MouseEvent) {
		when (mode) {
			InputMode.INSERT -> {
				if (evt.isPrimaryButtonDown)
					handleAddPoint(evt)
				if (evt.isSecondaryButtonDown)
					handleRemovePoint(evt)
			}
			InputMode.MOVE -> {
				handleSelectPoint(evt)
			}
		}
		update()
	}
	
	fun mouseMoved(evt: MouseEvent) {
		when (mode) {
			InputMode.MOVE -> {
				handleMovePoint(evt)
			}
		}
		update()
	}
	
	fun mouseReleased(evt: MouseEvent) {
		when (mode) {
			InputMode.MOVE -> {
				handleMovePointEnd(evt)
			}
		}
		update()
	}
	
	fun changeSelectMode() {
		mode = if (mode == InputMode.INSERT) {
			InputMode.MOVE
		} else {
			InputMode.INSERT
		}
	}
	
	fun changeHullMode() {
		hullState = !hullState
		updateHull()
	}
	
	fun updateHull() {
//		if (false) {
//
//			shapes.clear()
//			if (hullState && points.size >= 3) {
//				val hullPoints = Hull.graham(points)
//				val hullPoly = Polygon(hullPoints)
//				shapes.add(hullPoly)
//			}
//		} else {
		lines.clear()
		if (hullState && points.size >= 3) {
			val hullPoints = Hull.graham(points)
			val hullLines = Polygon(hullPoints).lines
			lines.addAll(hullLines)
			println("Lines size: " + lines.size)
			println("Points size: " + points.size)
		}
//		}
		
	}
	
	private fun update() {
		updateHull()
	}
	
	fun clearAll() {
		clearLines()
		clearPoints()
	}
	
	fun clearPoints() {
		points.clear()
	}
	
	fun clearLines() {
		lines.clear()
	}
	
	private fun handleSelectPoint(evt: MouseEvent) {
		val mousePos = getMousePos(evt)
		Selection.selectedPoint = points.firstOrNull { it.distTo(mousePos) <= it.radius }
	}
	
	private fun handleMovePoint(evt: MouseEvent) {
		val mousePos = getMousePos(evt)
		Selection.selectedPoint?.also {
			points.remove(it)
			val newPoint = mousePos.copy(radius = it.radius)
			points.add(newPoint)
			Selection.selectedPoint = newPoint
		}
		
	}
	
	private fun handleMovePointEnd(evt: MouseEvent) {
		Selection.selectedPoint?.also {
			if (abs(it.x) >= canvasWidth / 2 || abs(it.y) >= canvasHeight / 2) {
				
				points.remove(it)
			}
		}
		Selection.selectedPoint = null
	}
	
	private fun handleAddPoint(evt: MouseEvent) {
		val mousePos = getMousePos(evt)
		println(mousePos)
		println(points.size)
		//create circle correspondign  to this point, so that it can render
		points.add(mousePos.copy(radius = 10.0))
	}
	
	private fun handleRemovePoint(evt: MouseEvent) {
		val mousePoint = getMousePos(evt)
		points.removeIf { it.distTo(mousePoint) <= it.radius }
	}
	
	
	private fun getMousePos(evt: MouseEvent) = Point(evt.x - canvasWidth / 2, evt.y - canvasHeight / 2)
}