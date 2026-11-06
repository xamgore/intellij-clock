package clock

import java.time.DateTimeException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DecimalStyle
import java.time.format.FormatStyle
import java.util.IllformedLocaleException
import java.util.Locale

private val twentyFourHours = DateTimeFormatter.ofPattern("HH:mm")

internal enum class ClockFormat(val label: String, private val formatter: DateTimeFormatter) {
    HOURS_24("24 hours", twentyFourHours),
    HOURS_12("12 hours", DateTimeFormatter.ofPattern("h:mm a")),
    LOCALE("By locale", DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
    CUSTOM("Custom", twentyFourHours);

    fun render(time: ZonedDateTime, localeTag: String, pattern: String): String {
        val locale = parseLocale(localeTag)
        if (this == CUSTOM && pattern.isNotBlank()) {
            try {
                return DateTimeFormatter.ofPattern(pattern).format(time, locale)
            } catch (_: IllegalArgumentException) {
                // An invalid Registry pattern falls back to 24-hour time.
            } catch (_: DateTimeException) {
                // A pattern that cannot format this time uses the same fallback.
            }
        }
        return formatter.format(time, locale)
    }

    private fun DateTimeFormatter.format(time: ZonedDateTime, locale: Locale): String =
        withLocale(locale).withDecimalStyle(DecimalStyle.of(locale)).format(time)

    private fun parseLocale(tag: String): Locale {
        val languageTag = tag.trim()
        if (languageTag.isNotEmpty() && !languageTag.equals("system", ignoreCase = true)) {
            try {
                return Locale.Builder().setLanguageTag(languageTag).build()
            } catch (_: IllformedLocaleException) {
                // Fall back to the runtime's formatting locale.
            }
        }
        return Locale.getDefault(Locale.Category.FORMAT)
    }
}
