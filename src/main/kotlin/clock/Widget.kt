package clock

import com.intellij.openapi.wm.CustomStatusBarWidget
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import java.time.LocalDateTime
import javax.swing.JLabel
import javax.swing.Timer

class Widget : CustomStatusBarWidget {
    private val label = JLabel(time())

    private val timer = Timer(1000) { label.text = time() }

    private fun time() = LocalDateTime.now().run {
        "   %02d:%02d   ".format(hour, minute)
    }

    override fun ID() = "intellij-clock"

    override fun install(statusBar: StatusBar) = timer.start()

    override fun dispose() = timer.stop()

    override fun getComponent() = label

    override fun getPresentation(type: StatusBarWidget.PlatformType) = null
}
