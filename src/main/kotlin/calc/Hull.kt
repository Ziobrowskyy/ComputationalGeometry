package calc

import shapes.Point

object Hull {
	fun graham(l: List<Point>): List<Point> {
		val list = l.toList()
		assert(list.size >= 3)
		val (min, rest) = with(list.sortedWith { p1: Point, p2: Point ->
			if (p1.y == p2.y)
				p1.x.compareTo(p2.x)
			else
				p1.y.compareTo(p2.y)
		}) {
			Pair(this.first(), this.drop(1).toMutableList())
		}
		
		val minDownUnit = min.add(y = -1.0)
		
		rest.sortWith { p1: Point, p2: Point ->
			val cmp = Point.angle(min, minDownUnit, p1).compareTo(Point.angle(min, minDownUnit, p2))
			if (cmp == 0)
				min.distTo(p1).compareTo(min.distTo(p2))
			else
				cmp
		}
		
		val stack = arrayListOf(min)
		
		for (point in rest) {
			while (stack.size > 1 && Point.det(stack[stack.size - 2], point, stack[stack.size - 1]) >= 0) {
				stack.removeAt(stack.size - 1)
			}
			stack.add(point)
		}
		
		return stack
	}
}