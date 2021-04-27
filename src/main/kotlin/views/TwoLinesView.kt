package views

import controllers.LineAndPointController
import controllers.TwoLinesController
import tornadofx.View
import tornadofx.hbox

class TwoLinesView : View() {
	val controller = find<TwoLinesController>()
	override fun onDock() {
		super.onDock()
	}
	override val root = hbox {
		add(find<TwoLinesControlView>(mapOf(LineAndPointControlView::ctrl to controller)))
		add(find<CanvasView>(mapOf(CanvasView::ctrl to controller)))
	}
}