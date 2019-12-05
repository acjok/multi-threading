import java.util.concurrent.ConcurrentHashMap

fun main() {
    val sharedMemory = SharedMemory(ConcurrentHashMap())
    //Path to file inside project
    val firstFilePath = "file1.txt"
    val secondFilePath = "file2.txt"
    val thread1 = Thread(WordCountThread(sharedMemory, firstFilePath, true))
    val thread2 = Thread(WordCountThread(sharedMemory, secondFilePath, false))

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    val sorted = sharedMemory.wordCount.toList().sortedByDescending {
        it.second.firstFile + it.second.secondFile
    }

    sorted.forEach {
        println(it.first + " " + (it.second.firstFile + it.second.secondFile) + " = " + it.second.firstFile + " + " + it.second.secondFile)
    }
}