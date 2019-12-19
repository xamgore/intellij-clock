package clock

import com.intellij.diagnostic.IdeMessagePanel
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.WindowManager

class Registration(private val project: Project) : ProjectComponent {
    override fun getComponentName() = "intellij-clock"

    override fun projectOpened() {
        WindowManager
            .getInstance()
            .getStatusBar(project)
            ?.addWidget(
                Widget(),
                StatusBar.Anchors.before(IdeMessagePanel.FATAL_ERROR),
                project
            )
    }
}
