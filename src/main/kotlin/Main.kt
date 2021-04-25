import views.CanvasView
import views.ControlsView
import tornadofx.App
import tornadofx.View
import tornadofx.hbox
import tornadofx.launch

const val canvasWidth: Double = 800.0
const val canvasHeight: Double = 600.0


fun main(args: Array<String>) {
   launch<CGApp>(args)
}

class CGApp : App(MainView::class)

class MainView : View() {
   override val root = hbox {
      add(find<ControlsView>())
      add(find<CanvasView>())
   }
}

