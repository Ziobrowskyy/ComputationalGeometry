package shapes

class Rectangle(val p0: Point, val width: Double, val height: Double) : Shape() {
    override val lines: List<Line> = joinPointsWithLines(
        listOf(p0, p0.add(width, 0.0), p0.add(width, height), p0.add(0.0, height))
    )

    override fun replacePoint(pOld: Point, pNew: Point): Shape = this

    fun isInside(point: Point): Boolean {
        with(point) {
            return x >= p0.x && x < p0.x + width && y >= p0.y && y < p0.y + height
        }
    }
}