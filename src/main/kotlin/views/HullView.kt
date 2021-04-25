package views

import tornadofx.View
import tornadofx.hbox

class HullView : View() {
	override val root = hbox {
		add(find<HullControlsView>())
		add(find<CanvasView>())
	}
}