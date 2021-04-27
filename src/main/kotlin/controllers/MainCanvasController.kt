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

abstract class MainController : Controller() {
	val points = observableListOf<Point>()
	val lines = observableListOf<Line>()
	val shapes = observableListOf<Shape>()
	
	var mode: InputMode = InputMode.INSERT
	
	object Selection {
		var selectedPoint: Point? = null
		var selectedShape: Shape? = null
	}
	
	abstract fun update()
	abstract fun updateShape(s: Shape, oldPoint: Point, newPoint: Point)
	
	fun mousePressed(evt: MouseEvent) {
		when (mode) {
			InputMode.INSERT -> {
				if (evt.isPrimaryButtonDown)
					handleAddPoint(evt)
				if (evt.isSecondaryButtonDown)
					handleRemovePoint(evt)
			}
			InputMode.MOVE -> {
				if (evt.isPrimaryButtonDown)
					handleSelectPoint(evt)
				if (evt.isSecondaryButtonDown)
					handleRemovePoint(evt)
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
		Selection.selectedPoint?.parent?.also {
			Selection.selectedShape = it
		}
	}
	
	private fun handleMovePoint(evt: MouseEvent) {
		val mousePos = getMousePos(evt)
		/*
		Selection.selectedShape?.also { selectedShape ->
			Selection.selectedPoint?.also {
				val oldPoint = it
				val newPoint = it.copy(x = mousePos.x, y = mousePos.y)
				points.remove(it)

				shapes.remove(selectedShape)
				val replaced = selectedShape.replacePoint(oldPoint, newPoint)
				shapes.add(replaced)
				Selection.selectedPoint = replaced.points.find { it.x == newPoint.x && it.y == newPoint.y }
				Selection.selectedShape = replaced
			}

			return
		}

		 */
		
		Selection.selectedPoint?.also {
			val oldPoint = it
			val newPoint = it.copy(x = mousePos.x, y = mousePos.y)
			if(Selection.selectedShape == null) {
				points.remove(it)
				points.add(newPoint)
			}
			Selection.selectedPoint = newPoint
			//check if point has parent shape and if so update it
			Selection.selectedShape?.also {
				updateShape(it, oldPoint, newPoint)
			}
		}
	}
	
	private fun handleMovePointEnd(evt: MouseEvent) {
		Selection.selectedPoint?.also {
			if (abs(it.x) >= canvasWidth / 2 || abs(it.y) >= canvasHeight / 2) {
				if (it.parent == null)
					points.remove(it)
			}
		}
		Selection.selectedPoint = null
		Selection.selectedShape = null
	}
	
	private fun handleAddPoint(evt: MouseEvent) {
		val mousePos = getMousePos(evt)
		//create circle correspondign  to this point, so that it can render
		points.add(mousePos.copy(radius = 10.0))
	}
	
	private fun handleRemovePoint(evt: MouseEvent) {
		val mousePoint = getMousePos(evt)
		points.removeIf { it.distTo(mousePoint) <= it.radius }
	}
	
	
	private fun getMousePos(evt: MouseEvent) = Point(evt.x - canvasWidth / 2, evt.y - canvasHeight / 2)
}