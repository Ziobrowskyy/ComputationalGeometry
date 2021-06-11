package controllers

import calc.QuadTreePicture
import canvasHeight
import canvasWidth
import javafx.scene.image.Image
import shapes.Point

class QuadTreeController : CanvasController() {

    val pointCount = 500

    //    val quadTree: QuadTree
    val quadTree: QuadTreePicture

    init {
        pointMode = InputMode.MOVE
        disablePointRemove = true

        backgroundSrc.value = "bg.jpg"
        val image = Image(backgroundSrc.value)
        canvasWidth = image.width
        canvasHeight = image.height

        quadTree = QuadTreePicture(Point.zero, image)


//        for(i in 0 until pointCount) {
//            points.add(Point.randomFromCanvasSize(2.0))
//        }
//        quadTree = QuadTree(Point.canvasTopLeft(), points)
        quadTree.getShapes().forEach(::addShape)
    }

    override fun update() {

    }


}