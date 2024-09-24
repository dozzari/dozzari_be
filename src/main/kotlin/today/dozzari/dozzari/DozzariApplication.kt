package today.dozzari.dozzari

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DozzariApplication

fun main(args: Array<String>) {
    runApplication<DozzariApplication>(*args)
}
