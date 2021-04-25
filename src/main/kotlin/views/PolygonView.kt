package views

import tornadofx.View
import tornadofx.hbox

class PolygonView() : View() {
	override val root = hbox {
		add(find<HullControlsView>())
		add(find<CanvasView>())
	}
}