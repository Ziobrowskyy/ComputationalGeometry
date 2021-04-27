package stylesheets

import tornadofx.Stylesheet
import tornadofx.c
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px
import java.awt.Color


class Styles: Stylesheet() {
	companion object {
		val controlsBox by cssclass()
	}
	init {
		controlsBox {
			backgroundColor += c("#ddd")
			padding = box(5.px)
			wrapText = true
			button {
				padding = box(5.px)
				backgroundColor += c("yellow")
				minWidth = 100.px
				maxWidth = 100.px
				wrapText = true
			}
		}
	}
}