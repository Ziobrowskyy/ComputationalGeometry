import stylesheets.Styles
import tornadofx.*
import views.QuadTreeView

const val canvasWidth: Double = 800.0
const val canvasHeight: Double = 600.0


fun main(args: Array<String>) {
	launch<CGApp>(args)
}

class CGApp : App(QuadTreeView::class, Styles::class)


