package shapes

data class Circle(val center: Point, val radius: Double = 5.0) : Shape() {
	override val points = listOf(center.copy(parent = this))
	
	override fun replacePoint(pOld: Point, pNew: Point) = copy(center = pNew)
}