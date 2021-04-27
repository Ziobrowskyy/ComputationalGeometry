package views

import controllers.HullController
import javafx.beans.property.SimpleStringProperty
import stylesheets.Styles
import tornadofx.*

class HullControlsView: View() {
	val ctrl: HullController by param()
	
	private val modeString = SimpleStringProperty(ctrl.mode.name)
	private val hullStateString = SimpleStringProperty(ctrl.hullState.toString())
	fun toggleInsetMode() {
		ctrl.changeSelectMode()
		modeString.value = ctrl.mode.name
	}
	fun toggleHull() {
		ctrl.changeHullMode()
		hullStateString.value = ctrl.hullState.toString()
	}
	
	override val root = vbox {
		spacing = 5.0
		addClass(Styles.controlsBox)
		label("Current mode:")
		label(modeString)
		
		shortcut("T") {
			toggleInsetMode()
		}
		shortcut("H") {
			toggleHull()
		}
		
		button("Changle select mode [T]") {
			action { toggleInsetMode() }
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
		button("Toggle hull [H]") {
			action {toggleHull()}
		}
		button("Back to menu (not working yet)") {
		
		}
	}
}