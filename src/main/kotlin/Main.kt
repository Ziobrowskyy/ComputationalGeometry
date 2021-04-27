import stylesheets.Styles
import tornadofx.*
import views.HullView
import views.LineAndPointView
import views.PolygonView
import views.TwoLinesView

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
		button("Open point and lines canvas") {
			action { find<LineAndPointView>().openWindow() }
		}
		button("Open lines canvas") {
			action {find<TwoLinesView>().openWindow()}
		}
		button("Open polygon canvas") {
			action { find<PolygonView>().openWindow() }
		}
	}
}

