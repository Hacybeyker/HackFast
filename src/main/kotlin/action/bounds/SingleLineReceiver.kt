package action.bounds

import com.android.ddmlib.MultiLineReceiver

class SingleLineReceiver(private val processFirstLine: (response: String) -> Unit) : MultiLineReceiver() {

    private var cancelled: Boolean = false

    override fun processNewLines(lines: Array<out String>?) {
        lines?.getOrNull(0)?.let { firstLine ->
            processFirstLine(firstLine)
            cancelled = true
        }
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }
}