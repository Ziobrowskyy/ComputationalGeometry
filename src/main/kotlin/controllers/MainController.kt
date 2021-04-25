package controllers

import shapes.Point
import tornadofx.Controller
import tornadofx.observableListOf

class MainController: Controller() {
   val points = observableListOf<Point>()

   init {
      for(i in 0 until 10) {
         points.add(Point.randomFromCanvasSize())
      }
   }
}