package clock

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep
import com.intellij.openapi.util.registry.Registry
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.ui.awt.RelativePoint
import com.intellij.util.Consumer
import java.awt.Component
import java.awt.event.MouseEvent
import java.time.ZonedDateTime
import javax.swing.SwingUtilities
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

    override fun getTooltipText(): String = "Choose clock format"

    override fun getAlignment(): Float = Component.CENTER_ALIGNMENT

    override fun getText(): String = selectedFormat().render(
        ZonedDateTime.now(),
        Registry.stringValue(LOCALE_KEY),
        Registry.stringValue(PATTERN_KEY),
    )

    override fun getClickConsumer(): Consumer<MouseEvent> = Consumer { event ->
        if (SwingUtilities.isLeftMouseButton(event)) {
            val step = object : BaseListPopupStep<ClockFormat>("Clock Format", ClockFormat.entries) {
                override fun getTextFor(value: ClockFormat): String = value.label

                override fun onChosen(selectedValue: ClockFormat, finalChoice: Boolean): PopupStep<*>? {
                    PropertiesComponent.getInstance().setValue(FORMAT_KEY, selectedValue.name, ClockFormat.HOURS_24.name)
                    statusBar?.updateWidget(ID())
                    return PopupStep.FINAL_CHOICE
                }
            }
            step.setDefaultOptionIndex(selectedFormat().ordinal)
            JBPopupFactory.getInstance().createListPopup(step).show(RelativePoint(event))
        }
    }

    override fun ID() = ID

    private fun selectedFormat(): ClockFormat {
        val savedFormat = PropertiesComponent.getInstance().getValue(FORMAT_KEY)
        return ClockFormat.entries.firstOrNull { it.name == savedFormat } ?: ClockFormat.HOURS_24
    }

    companion object {
        const val ID = "intellij-clock"
        private const val FORMAT_KEY = "$ID.format"
        private const val LOCALE_KEY = "$ID.format.locale"
        private const val PATTERN_KEY = "$ID.format.pattern"
    }
}
