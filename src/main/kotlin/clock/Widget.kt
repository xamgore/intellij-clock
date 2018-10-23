package clock

import com.intellij.openapi.wm.CustomStatusBarWidget
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import java.time.LocalDateTime
import javax.swing.JLabel
import javax.swing.Timer

class Widget : CustomStatusBarWidget {
    val label = JLabel(time())

    val timer = Timer(1000) { label.text = time() }

    fun time() = LocalDateTime.now().run {
        "   %02d:%02d   ".format(hour, minute)
    }

    override fun install(statusBar: StatusBar) = timer.start()

    override fun dispose() = timer.stop()

    override fun getComponent() = label

    override fun getPresentation(type: StatusBarWidget.PlatformType) = null

    override fun ID() = ID

    companion object {
        const val ID = "intellij-clock"
    }
}
