package clock

import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import java.awt.Component
import java.time.LocalTime
import javax.swing.Timer

class ClockWidget : StatusBarWidget, StatusBarWidget.TextPresentation {
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

    override fun getPresentation(): StatusBarWidget.TextPresentation = this

    override fun getTooltipText(): String? = null

    override fun getAlignment(): Float = Component.CENTER_ALIGNMENT

    override fun getText(): String {
        val time = LocalTime.now()
        return "%02d:%02d".format(time.hour, time.minute)
    }

    override fun ID() = ID

    companion object {
        const val ID = "intellij-clock"
    }
}
