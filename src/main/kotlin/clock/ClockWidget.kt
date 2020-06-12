package clock

import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.util.Consumer
import java.awt.Component
import java.awt.event.MouseEvent
import java.time.LocalDateTime
import javax.swing.Timer

class ClockWidget : StatusBarWidget {
    private var statusBar: StatusBar? = null
    private val timer = Timer(1000) { statusBar?.updateWidget(ID()) }

    override fun install(statusBar: StatusBar) {
        this.statusBar = statusBar
        statusBar.updateWidget(ID())
        timer.start()
    }

    override fun dispose() {
        timer.stop()
        statusBar = null
    }

    override fun getPresentation() = object: StatusBarWidget.TextPresentation {
        override fun getTooltipText(): String? = null
        override fun getClickConsumer(): Consumer<MouseEvent>? = null

        override fun getAlignment(): Float = Component.CENTER_ALIGNMENT

        override fun getText(): String {
            return LocalDateTime.now().run {
                "%02d:%02d".format(hour, minute)
            }
        }
    }

    override fun ID() = ID

    companion object {
        const val ID = "intellij-clock"
    }
}
