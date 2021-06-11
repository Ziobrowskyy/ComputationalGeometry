import stylesheets.Styles
import tornadofx.*
import views.QuadTreeView

var canvasWidth: Double = 800.0
var canvasHeight: Double = 600.0


fun main(args: Array<String>) {
	launch<CGApp>(args)
}

class CGApp : App(QuadTreeView::class, Styles::class)


