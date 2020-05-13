package maslov.txtest

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.random.Random


@SpringBootTest(webEnvironment = RANDOM_PORT)
class TransactionTest {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun test() {
        val executor = Executors.newFixedThreadPool(10)
        val futures: List<Future<ResponseEntity<String>>> = (0..10).map {
            executor.submit(Callable {
                restTemplate.getForEntity(
                        "http://localhost:$port/products/${Random.nextInt(1, 4)}/calc",
                        String::class.java
                )
            })
        }.toList()

        futures.forEach {
            Assertions.assertThat(it.get().statusCode).isEqualTo(HttpStatus.OK)
        }
    }
}