package action.dimens

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter

class GenerateDimen {
    companion object {

        const val PATH_DIMENS = "/app/src/main/res/values/dimens.xml"

        fun generate(basePath: String, numberMax: Int): Boolean {
            val countDimens = if (numberMax > 0) numberMax else 500
            val dimens = StringBuilder()
            val xmlStart = """
                    <?xml version="1.0" encoding="utf-8"?>
                    <resources>
                """.trimIndent()
            dimens.append(xmlStart)
            dimens.append("\n")
            for (i in 0..countDimens) {
                val dp = """
                        <dimen name="dimen_${i}dp">${i}dp</dimen>
                    """.trimIndent()

                val sp = """
                        <dimen name="dimen_${i}sp">${i}sp</dimen>
                    """.trimIndent()

                dimens.append("\t").append(dp).append("\n")
                dimens.append("\t").append(sp).append("\n")
            }
            dimens.append("</resources>")
            val fileDimens = "$basePath$PATH_DIMENS"
            writeFile(fileDimens, dimens.toString())
            return true
        }

        private fun writeFile(file: String, text: String) {
            val out = PrintWriter(BufferedWriter(FileWriter(file)))
            out.println(text)
            out.close()
        }
    }
}