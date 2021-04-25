import stylesheets.Styles
import tornadofx.*
import views.HullView
import views.LinesView
import views.PolygonView

const val canvasWidth: Double = 800.0
const val canvasHeight: Double = 600.0


fun main(args: Array<String>) {
	launch<CGApp>(args)
}

class CGApp : App(MainView::class, Styles::class)

class MainView : View() {
	override val root = vbox {
		text("Choose what do you want to do:")
		button("Open hull canvas") {
			action { find<HullView>().openWindow() }
		}
		button("Open lines canvas") {
			action { find<LinesView>().openWindow() }
		}
		button("Open polygon canvas") {
			action { find<PolygonView>().openWindow() }
		}
	}
}

