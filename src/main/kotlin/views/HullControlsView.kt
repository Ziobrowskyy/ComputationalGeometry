package views

import canvasHeight
import controllers.MainController
import javafx.beans.property.SimpleStringProperty
import stylesheets.Styles
import tornadofx.*
import java.awt.Color

class HullControlsView : View() {
	val ctrl = find<MainController>()
	val modeString = SimpleStringProperty(ctrl.mode.name)
	val hullStateString = SimpleStringProperty(ctrl.hullState.toString())
	
	override val root = vbox {
		spacing = 5.0
		addClass(Styles.controlsBox)
		label("Current mode:")
		label(modeString)
		
		button("Changle select mode") {
			action {
				ctrl.changeSelectMode()
				modeString.value = ctrl.mode.name
			}
		}
		button("Clear all points") {
			action { ctrl.clearPoints() }
		}
		
		button("Clear all lines") {
			action { ctrl.clearLines() }
		}
		
		button("Clear ALL") {
			action { ctrl.clearAll() }
		}
		label("Hull status")
		label(hullStateString)
		button("Toggle hull") {
			action {
				ctrl.changeHullMode()
				hullStateString.value = ctrl.hullState.toString()
			}
		}
		button("Update hull") {
			action { ctrl.updateHull() }
		}
		
		button("Back to menu") {
		
		}
	}
}