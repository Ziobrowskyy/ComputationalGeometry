package views

import canvasHeight
import canvasWidth
import controllers.CanvasController
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import tornadofx.*

class CanvasView : View() {
	val ctrl: CanvasController by param()
	
	private fun onMouseClicked(evt: MouseEvent) {
		ctrl.mousePressed(evt)
	}
	
	private fun onMouseDragged(evt: MouseEvent) {
		ctrl.mouseMoved(evt)
	}
	
	private fun onMouseReleased(evt: MouseEvent) {
		ctrl.mouseReleased(evt)
	}
	
	override val root = stackpane {
		setPrefSize(canvasWidth, canvasHeight)
		
		//set listeners on canvas
		addEventFilter(MouseEvent.MOUSE_PRESSED, ::onMouseClicked)
		addEventFilter(MouseEvent.MOUSE_DRAGGED, ::onMouseDragged)
		addEventFilter(MouseEvent.MOUSE_RELEASED, ::onMouseReleased)
		
		group {
			
			//axis draw
			group {
				//center point
				circle {
					centerX = 0.0
					centerY = 0.0
					radius = 2.0
					fill = Color.BLACK
				}
				//horizontal axis
				line {
					startX = -canvasWidth / 2
					startY = 0.0
					endX = canvasWidth / 2
					endY = 0.0
					stroke = Color.GRAY
				}
				//vertical axis
				line {
					startX = 0.0
					startY = -canvasHeight / 2
					endX = 0.0
					endY = canvasHeight / 2
					stroke = Color.GRAY
				}
			}
			
			//rest of shapes/points/lines draw
			group {
				// draw lines
				bindChildren(ctrl.lines) {
					line {
						startX = it.p1.x
						startY = it.p1.y
						endX = it.p2.x
						endY = it.p2.y
						stroke = Color.RED
					}
				}
			}
			group {
				// draw points
				bindChildren(ctrl.points) {
					circle {
						centerX = it.x
						centerY = it.y
						radius = it.radius
						fill = Color.BLACK
//						fill = Color.rgb(
//							Random.nextInt(0, 255),
//							Random.nextInt(0, 255),
//							Random.nextInt(0, 255),
//						)
					}
				}
			}
				// draw shapes, currently unused
//				bindChildren(ctrl.shapes) {
//					when (it) {
//						is Polygon -> {
//							println("POLY POLY")
//							val list = mutableListOf<Double>()
//							it.points.forEach {
//								list.add(it.x)
//								list.add(it.y)
//							}
//							polygon(*list.toTypedArray())
//						}
//						else -> circle {
//							centerX = 0.0
//							centerY = 0.0
//							radius = 0.0
//						}
//
//					}
//				}
//			}
		
		
		}
	}
}