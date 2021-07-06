import Constants.Files.Companion.FILE_DIMENS
import Constants.Plugin.Companion.NAME_PLUGIN

class Constants {

    class Plugin {
        companion object {
            const val NAME_PLUGIN = "HackFast"
        }
    }

    class Files {
        companion object {
            private const val DIMENS = "dimens"
            private const val EXTENSION_XML = "xml"
            const val FILE_DIMENS = "$DIMENS.$EXTENSION_XML"
        }
    }

    class Dialog {
        companion object {
            const val TITLE = "Enter a number"
            const val MESSAGE = "Enter a number for $FILE_DIMENS file"
        }
    }

    class Notification {
        companion object {
            const val DISPLAY_ID_DIMENS = "dimens"
            const val DISPLAY_ID_BOUNDS = "bounds"

            const val TITLE_NOTIFICATION = "$NAME_PLUGIN says:"

            const val MESSAGE_CREATE_FILE_ERROR = "The file could not be created $FILE_DIMENS."
            const val MESSAGE_CREATE_FILE_SUCCESS = "$FILE_DIMENS file created successfully."
            const val MESSAGE_CREATE_FILE_WRONG = "An error occurred while creating the file $FILE_DIMENS."
            const val MESSAGE_CREATE_FILE_EXISTS = "The $FILE_DIMENS file already exists."

            const val MESSAGE_NO_DEVICES_CONNECTED = "No devices connected."
        }
    }
}