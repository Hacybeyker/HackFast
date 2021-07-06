package action.bounds

import Constants.Notification.Companion.DISPLAY_ID_BOUNDS
import Constants.Notification.Companion.MESSAGE_NO_DEVICES_CONNECTED
import Constants.Notification.Companion.TITLE_NOTIFICATION
import com.android.ddmlib.IDevice
import com.android.ddmlib.NullOutputReceiver
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils

class LayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let { project ->
            val devices = AndroidSdkUtils.getDebugBridge(project)?.devices
            devices?.takeIf { it.isNotEmpty() }?.forEach { device ->
                device.executeShellCommand("getprop debug.layout",
                    SingleLineReceiver { firstLine ->
                        val enabled = firstLine.toBoolean().not()
                        device.setLayoutBounds(enabled)

                    })
            } ?: kotlin.run { project.showNotification(MESSAGE_NO_DEVICES_CONNECTED, NotificationType.ERROR) }
        }
    }

    private fun IDevice.setLayoutBounds(enable: Boolean) {
        executeShellCommand("setprop debug.layout $enable ; service call activity 1599295570", NullOutputReceiver())
    }

    private fun Project.showNotification(message: String, notificationType: NotificationType) {
        NotificationGroup(DISPLAY_ID_BOUNDS, NotificationDisplayType.BALLOON)
            .createNotification(
                TITLE_NOTIFICATION,
                message,
                notificationType,
                null
            ).notify(this)
    }
}