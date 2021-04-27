package views

import tornadofx.View
import tornadofx.hbox

class PolygonView() : View() {
	/*
v. Określi położenie punktu względem trójkąta,
vi. Określi położenie punktu względem wielokąta,
vii. Wyznaczy punkty przecięcia półprostej z okręgiem,
viii. Obliczy pole trójkąta.

	 */
	override val root = hbox {
		add(find<HullControlsView>())
		add(find<CanvasView>())
	}
}