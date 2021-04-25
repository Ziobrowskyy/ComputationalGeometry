import kotlin.math.abs

fun Double.eq(other: Double, eps: Double = 0.0001): Boolean {
	return if (abs(this - other) < eps)
		true
	else
		abs(this / other - 1) < eps
}

