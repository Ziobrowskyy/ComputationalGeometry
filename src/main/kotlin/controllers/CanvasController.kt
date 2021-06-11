package controllers

import canvasHeight
import canvasWidth
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Dimension2D
import javafx.scene.input.MouseEvent
import shapes.*
import tornadofx.Controller
import tornadofx.observableListOf
import kotlin.math.abs


abstract class CanvasController : Controller() {
    enum class InputMode {
        INSERT,
        MOVE
    }
    var backgroundSrc: SimpleStringProperty = SimpleStringProperty("")
    val points = observableListOf<Point>()
    val lines = observableListOf<Line>()
    protected val shapes = observableListOf<Shape>()

    var pointMode: InputMode = InputMode.INSERT
    protected var disablePointRemove = false

    object Selection {
        var selectedPoint: Point? = null
        var selectedShape: Shape? = null
    }

    abstract fun update()
    fun addShape(shape: Shape) {
        shapes.add(shape)
        points.addAll(shape.points)
        lines.addAll(shape.lines)
    }
    open fun updateShape(s: Shape, oldPoint: Point, newPoint: Point) {
        points.removeIf { s.points.contains(it) }
        shapes.remove(s)
        Selection.selectedShape = s.replacePoint(oldPoint, newPoint)
        shapes.add(Selection.selectedShape)
        points.addAll(Selection.selectedShape!!.points)
    }

    fun mousePressed(evt: MouseEvent) {
        when (pointMode) {
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
        when (pointMode) {
            InputMode.MOVE -> {
                handleMovePoint(evt)
            }
            InputMode.INSERT -> {

            }
        }
        update()
    }

    fun mouseReleased(evt: MouseEvent) {
        when (pointMode) {
            InputMode.MOVE -> {
                handleMovePointEnd(evt)
            }
            InputMode.INSERT -> {

            }
        }
        update()
    }

    fun changeSelectMode() {
        pointMode = if (pointMode == InputMode.INSERT) {
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

        Selection.selectedPoint?.also {
            val oldPoint = it
            val newPoint = it.copy(x = mousePos.x, y = mousePos.y)
            if (Selection.selectedShape == null) {
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
        //create circle corresponding  to this point, so that it can render
        points.add(mousePos.copy(radius = 10.0))
    }

    private fun handleRemovePoint(evt: MouseEvent) {
        if (!disablePointRemove) {
            val mousePoint = getMousePos(evt)
            points.removeIf { it.distTo(mousePoint) <= it.radius }
        }
    }


    private fun getMousePos(evt: MouseEvent) = Point(evt.x - canvasWidth / 2, evt.y - canvasHeight / 2)
}