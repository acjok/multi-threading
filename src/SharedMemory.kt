import java.util.concurrent.ConcurrentHashMap

data class SharedMemory(
    var wordCount: ConcurrentHashMap<String, Occurrences>
) {

    fun countWord(key: String, isFirst: Boolean) {
        var occurrences: Occurrences
        synchronized(wordCount) {
            occurrences = if (!wordCount.containsKey(key)) {
                Occurrences(0, 0).also { wordCount[key] = it }
            } else
                wordCount[key]!!
        }
        if (isFirst) {
            occurrences.firstFile++
        } else
            occurrences.secondFile++
    }
}