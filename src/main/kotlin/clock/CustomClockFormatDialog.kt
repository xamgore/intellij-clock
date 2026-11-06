package clock

import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import java.awt.Component
import java.time.format.DateTimeFormatter
import java.util.IllformedLocaleException
import java.util.Locale
import javax.swing.JComponent

internal class CustomClockFormatDialog(
    parent: Component,
    initialPattern: String,
    initialLocale: String,
) : DialogWrapper(parent, true) {
    private val patternField = JBTextField(initialPattern, 24).apply {
        emptyText.text = "EEE d MMM, HH:mm"
    }

    private val system = LocaleChoice("system", "System default")
    private val localeBox = ComboBox<LocaleChoice>()

    val pattern: String get() = patternField.text
    val localeTag: String get() = (localeBox.selectedItem as LocaleChoice).tag

    init {
        val locales = Locale.getAvailableLocales().asSequence()
            .filter { it.language.isNotEmpty() && it.country.isNotEmpty() }
            .map(::localeChoice)
            .distinctBy { it.tag }
            .sortedBy { it.label }
            .toList()
        localeBox.addItem(system)
        locales.forEach(localeBox::addItem)

        val selected = savedLocale(initialLocale)
        if (selected != system && locales.none { it.tag == selected.tag }) {
            localeBox.insertItemAt(selected, 1)
        }
        localeBox.selectedItem = selected

        title = "Custom Clock Format"
        init()
    }

    override fun createCenterPanel(): JComponent = panel {
        row("Locale") {
            cell(localeBox).comment(
                """<a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Locale.html">Java locale reference</a>""",
            )
        }
        row("Format") {
            cell(patternField).comment(
                """<a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/format/DateTimeFormatter.html#patterns">Java date and time patterns</a>""",
            )
        }
    }

    override fun getPreferredFocusedComponent(): JComponent = patternField

    override fun doValidate(): ValidationInfo? {
        if (pattern.isBlank()) return null
        return try {
            DateTimeFormatter.ofPattern(pattern)
            null
        } catch (error: IllegalArgumentException) {
            ValidationInfo(error.message ?: "Invalid format pattern", patternField)
        }
    }

    private fun savedLocale(tag: String): LocaleChoice {
        if (tag.isBlank() || tag.equals(system.tag, ignoreCase = true)) return system
        return try {
            localeChoice(Locale.Builder().setLanguageTag(tag.trim()).build())
        } catch (_: IllformedLocaleException) {
            system
        }
    }

    private fun localeChoice(locale: Locale): LocaleChoice {
        val tag = locale.toLanguageTag()
        return LocaleChoice(tag, "${locale.getDisplayName(Locale.ENGLISH)} ($tag)")
    }

    private data class LocaleChoice(val tag: String, val label: String) {
        override fun toString(): String = label
    }
}
