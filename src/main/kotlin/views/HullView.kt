package views

import controllers.HullController
import tornadofx.View
import tornadofx.hbox

class HullView : View() {
	val controller = find<HullController>()
	override val root = hbox {
//		val controller = HullControlsView()
		add(find<HullControlsView>(mapOf(HullControlsView::ctrl to controller)))
		add(find<CanvasView>(mapOf(CanvasView::ctrl to controller)))
	}
}