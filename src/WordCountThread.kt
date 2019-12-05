import java.io.*
import java.util.*


class WordCountThread(
    private var memory: SharedMemory,
    private var filePath: String,
    private val isFirst: Boolean
) : Runnable {

    override fun run() {

        val scanner: Scanner
        try {
            scanner = Scanner(FileInputStream(filePath), "UTF-8")
        } catch (e: FileNotFoundException) {
            throw e
        }

        while (scanner.hasNextLine()) {
            val key = scanner.next()

            if (key == " " || key == "")
                break
            memory.countWord(key.toLowerCase(), isFirst)
        }
    }
}