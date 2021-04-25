package views

import controllers.MainController
import tornadofx.View
import tornadofx.vbox

class ControlsView: View() {
   val ctrl = find<MainController>()
   override val root = vbox  {

   }
}