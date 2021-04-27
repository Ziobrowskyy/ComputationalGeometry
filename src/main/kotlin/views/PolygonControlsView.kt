package views

import controllers.PolygonController
import javafx.beans.property.SimpleStringProperty
import stylesheets.Styles
import tornadofx.*

class PolygonControlsView : View() {
	val ctrl: PolygonController by param()
	private val resultString = SimpleStringProperty("")
	private val modeString = SimpleStringProperty(ctrl.mode.name)
	private fun toggleInsetMode() {
		ctrl.changeSelectMode()
		modeString.value = ctrl.mode.name
	}
	
	override val root = vbox {
		spacing = 5.0
		addClass(Styles.controlsBox)
		
		shortcut("T") {
			toggleInsetMode()
		}
		button("Changle select mode [T]") {
			action { toggleInsetMode() }
		}
		button("Calculate area of given polygon") {
			action {}
		}
		button("Check if point is inside polygon") {
			action {}
		}
		label("Result:")
		text(resultString)
	}
}