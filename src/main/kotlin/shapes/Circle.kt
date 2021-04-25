package shapes

data class Circle(val center: Point, val radius: Double = 5.0) : Shape() {
	init {
	}
	override val points = listOf(center)
}