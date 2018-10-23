package clock

import com.intellij.mock.MockProjectEx
import com.intellij.openapi.extensions.Extensions
import com.intellij.openapi.wm.WindowManager
import com.intellij.openapi.wm.impl.TestWindowManager
import com.intellij.testFramework.PlatformLiteFixture
import java.util.concurrent.TimeUnit
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class ClockTest : PlatformLiteFixture() {

    private var widget: Widget? = null

    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()
        initApplication()
        Extensions.registerAreaClass("IDEA_PROJECT", null)
        myProject = MockProjectEx(testRootDisposable)

        val windowManager: WindowManager = TestWindowManager()
        PlatformLiteFixture.getApplication().registerService(WindowManager::class.java, windowManager)
        val registration = Registration(myProject)
        registration.projectOpened()

        val statusBar = windowManager.getStatusBar(myProject)
        widget = statusBar.getWidget(Widget.ID) as Widget
        widget!!.install(statusBar)
    }

    fun testClockFormat() {
        val format = """\s*\d\d:\d\d\s*""".toRegex()
        assertTrue(widget!!.label.text.matches(format),
            message = "Widget's label is displayed in wrong format")
    }

    fun testTimeChanges() {
        with (widget!!) {
            val oldTime = time()
            TimeUnit.MINUTES.sleep(1)
            val currentTime = time()

            assertNotEquals(illegal = oldTime, actual = currentTime,
                message = "Widget's time function is constant")
        }
    }

}
