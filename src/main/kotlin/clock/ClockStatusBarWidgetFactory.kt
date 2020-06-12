package clock

import com.intellij.ide.lightEdit.LightEditCompatible
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory

class ClockStatusBarWidgetFactory : StatusBarWidgetFactory, LightEditCompatible {
    override fun getId(): String = ClockWidget.ID

    override fun getDisplayName(): String = "Clock"

    override fun createWidget(project: Project): StatusBarWidget = ClockWidget()

    override fun disposeWidget(widget: StatusBarWidget) {
        Disposer.dispose(widget)
    }

    override fun isAvailable(project: Project): Boolean = true

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true
}