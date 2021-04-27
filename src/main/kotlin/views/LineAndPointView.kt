package views

import controllers.LineAndPointController
import tornadofx.View
import tornadofx.hbox

class LineAndPointView : View() {
	val controller = find<LineAndPointController>()
	override val root = hbox {
		add(find<LineAndPointControlView>(mapOf(LineAndPointControlView::ctrl to controller)))
		add(find<CanvasView>(mapOf(CanvasView::ctrl to controller)))
	}
}