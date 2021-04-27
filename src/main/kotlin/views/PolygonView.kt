package views

import controllers.PolygonController
import tornadofx.View
import tornadofx.hbox

class PolygonView() : View() {
	/*
v. Określi położenie punktu względem trójkąta,
vi. Określi położenie punktu względem wielokąta,
vii. Wyznaczy punkty przecięcia półprostej z okręgiem,
viii. Obliczy pole trójkąta.

	 */
	val controller = find<PolygonController>()
	override val root = hbox {
		add(find<PolygonControlsView>(mapOf(PolygonControlsView::ctrl to controller)))
		add(find<CanvasView>(mapOf(CanvasView::ctrl to controller)))
	}
}