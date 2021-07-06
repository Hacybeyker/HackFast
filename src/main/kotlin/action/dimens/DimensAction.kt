package action.dimens

import Constants.Dialog.Companion.MESSAGE
import Constants.Dialog.Companion.TITLE
import Constants.Notification.Companion.DISPLAY_ID_DIMENS
import Constants.Notification.Companion.MESSAGE_CREATE_FILE_ERROR
import Constants.Notification.Companion.MESSAGE_CREATE_FILE_EXISTS
import Constants.Notification.Companion.MESSAGE_CREATE_FILE_SUCCESS
import Constants.Notification.Companion.MESSAGE_CREATE_FILE_WRONG
import Constants.Notification.Companion.TITLE_NOTIFICATION
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFileManager
import java.io.File
import java.util.regex.Pattern

class DimensAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        event.project?.let { project ->
            val basePath = project.basePath.toString()
            if (isNotExistsFile(basePath)) {
                val inputNumber = showDialogInputNumber(project)
                val statusText: Boolean = isAnNumber(inputNumber)
                if (inputNumber.isEmpty() || !statusText) {
                    event.project?.showNotification(MESSAGE_CREATE_FILE_ERROR, NotificationType.ERROR)
                } else {
                    if (GenerateDimen.generate(basePath, inputNumber.toInt())) {
                        event.project?.showNotification(MESSAGE_CREATE_FILE_SUCCESS, NotificationType.INFORMATION)
                        VirtualFileManager.getInstance().refreshWithoutFileWatcher(false)
                    } else {
                        event.project?.showNotification(MESSAGE_CREATE_FILE_WRONG, NotificationType.ERROR)
                    }
                }
            } else {
                event.project?.showNotification(MESSAGE_CREATE_FILE_EXISTS, NotificationType.WARNING)
            }
        }
    }

    private fun showDialogInputNumber(project: Project): String {
        return Messages.showInputDialog(project, MESSAGE, TITLE, null) ?: ""
    }

    internal fun isAnNumber(inputNumber: String): Boolean {
        val regex = "[0-9]+"
        val pattern = Pattern.compile(regex)
        return pattern.matcher(inputNumber).matches()
    }

    private fun isNotExistsFile(basePath: String): Boolean {
        val fileName = "$basePath${GenerateDimen.PATH_DIMENS}"
        val file = File(fileName)
        return !file.exists()
    }

    private fun Project.showNotification(message: String, notificationType: NotificationType) {
        NotificationGroup(DISPLAY_ID_DIMENS, NotificationDisplayType.BALLOON)
            .createNotification(
                TITLE_NOTIFICATION,
                message,
                notificationType,
                null
            ).notify(this)
    }
}