package com.guliash.quizzes.core.io

import java.io.InputStream
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileUtils @Inject constructor() {
    fun readWhole(stream: InputStream): String {
        val buffer = ByteArray(1024)
        var read = 0
        val bytes = ArrayList<Byte>()

        while (read != -1) {
            read = stream.read(buffer)
            if (read != -1) {
                var current = 0
                while (current < read) {
                    bytes.add(buffer[current++])
                }
            }
        }
        return String(bytes.toByteArray())
    }
}
