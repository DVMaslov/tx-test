package maslov.txtest.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import kotlin.random.Random

@Service
class ProductService(
        private val productManager: ProductManager
) {

    @Transactional(propagation = Propagation.NEVER)
    fun calcSum(productId: Long): BigDecimal {
        // first fast db query
        val product = productManager.get(productId)

        // slow call
        val priceForProduct = getPriceForProduct()
//
//        // second fast db query
//        val product2 = productManager.get(productId)

        return priceForProduct.multiply(BigDecimal.valueOf(Random.nextLong(10, 50)))
    }

    fun getPriceForProduct(): BigDecimal {
        Thread.sleep(5000) // emulating slow external service call

        return BigDecimal.valueOf(Random.nextLong(50, 100))
    }
}