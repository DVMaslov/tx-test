package maslov.txtest.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StopWatch
import java.math.BigDecimal
import kotlin.random.Random

@Service
class ProductService(
        private val productManager: ProductManager
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional(propagation = Propagation.NEVER)
    fun calcSum(productId: Long): BigDecimal {
        // first fast db query
        val stopWatch = StopWatch()
        stopWatch.start("load product from local db")
        val product = productManager.get(productId)
        stopWatch.stop()

        stopWatch.start("load price from external service")
        // slow call
        val priceForProduct = getPriceForProduct()
        stopWatch.stop()

        logger.info("calcSum for product ${product.name}. time: ${stopWatch.prettyPrint()}")
        return priceForProduct.multiply(BigDecimal.valueOf(Random.nextLong(10, 50)))
    }

    fun getPriceForProduct(): BigDecimal {
        Thread.sleep(5000) // emulating slow external service call

        return BigDecimal.valueOf(Random.nextLong(50, 100))
    }
}