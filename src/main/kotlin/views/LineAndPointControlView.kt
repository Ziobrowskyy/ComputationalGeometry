package views

import controllers.LineAndPointController
import javafx.beans.property.SimpleStringProperty
import stylesheets.Styles
import tornadofx.*

class LineAndPointControlView : View() {
	//	val ctrl = find<HullController>()
	val ctrl: LineAndPointController by param()
	
	private val modeString = SimpleStringProperty(ctrl.mode.name)
	
	private val resultString = SimpleStringProperty("")
	private val angleField = SimpleStringProperty("10")
	
	private fun onSideOfLine() {
		resultString.value = ctrl.getSideOfLineString()
	}
	
	private fun onLineRation() {
		ctrl.rotateLineByPoint(angleField.value)
	}
	
	private fun onLineEquation() {
		resultString.value = ctrl.getLineEquationString()
	}
	
	override val root = vbox {
		spacing = 5.0
		addClass(Styles.controlsBox)
//		label("Current mode:")
//		text(modeString)
//
//		shortcut("T") {
//			toggleInsetMode()
//		}

//		button("Changle select mode [T]") {
//			action { toggleInsetMode() }
//		}
		label("Opeations:")
		button("Get line eqation") {
			action { onLineEquation() }
		}
		button("On what side of line lies point") {
			action { onSideOfLine() }
		}
		button("Rotate line over point by angle") {
			action { onLineRation() }
		}
		label("Rotation angle (deg):")
		textfield(angleField) {
		
		}
		label("Output of operation:")
		text(resultString)
	}
}