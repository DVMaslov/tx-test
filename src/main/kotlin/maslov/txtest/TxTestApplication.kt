package maslov.txtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TxTestApplication

fun main(args: Array<String>) {
	runApplication<TxTestApplication>(*args)
}
