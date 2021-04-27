package views

import controllers.LineAndPointController
import javafx.scene.Parent
import stylesheets.Styles
import tornadofx.View
import tornadofx.addClass
import tornadofx.hbox

class TwoLinesControlView: View() {
	val ctrl: LineAndPointController by param()
	init {
	
	}
	override val root: Parent = hbox {
		spacing = 5.0
		addClass(Styles.controlsBox)
		
	}
}