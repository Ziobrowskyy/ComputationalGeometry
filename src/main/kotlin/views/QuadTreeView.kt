package views

import controllers.QuadTreeController
import tornadofx.View
import tornadofx.hbox

class QuadTreeView : View() {
	val controller = find<QuadTreeController>()
	override val root = hbox {
//		val controller = HullControlsView()
		add(find<QuadTreeContolsView>(mapOf(QuadTreeContolsView::ctrl to controller)))
		add(find<CanvasView>(mapOf(CanvasView::ctrl to controller)))
	}
}