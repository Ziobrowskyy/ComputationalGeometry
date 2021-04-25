package views

import controllers.MainController
import canvasHeight
import canvasWidth
import javafx.scene.paint.Color
import tornadofx.*
import kotlin.random.Random

class CanvasView : View() {
   private val ctrl = find<MainController>()
   override val root = stackpane {
      setPrefSize(canvasWidth, canvasHeight)
      //axis draw
      group {
         //center point
         circle {
            centerX = 0.0
            centerY = 0.0
            radius = 2.0
            fill = Color.BLACK
         }
         //axis
         line {
            startX = -canvasWidth / 2
            startY = 0.0
            endX = canvasWidth / 2
            endY = 0.0
            stroke = Color.GRAY
         }
         line {
            startX = 0.0
            startY = -canvasHeight / 2
            endX = 0.0
            endY = canvasHeight / 2
            stroke = Color.GRAY
         }

         group {
            bindChildren(ctrl.points) {
               circle {
                  centerX = it.x
                  centerY = it.y
                  radius = it.radius
                  fill = Color.BLACK
                     fill = Color.rgb(
                        Random.nextInt(0, 255),
                        Random.nextInt(0, 255),
                        Random.nextInt(0, 255),
                     )
               }
            }
//            bindChildren(ctrl.lines) {
//               line {
//                  startX = it.p1.x
//                  startY = it.p1.y
//                  endX = it.p2.x
//                  endY = it.p2.y
//               }
//            }
         }

      }
   }
}