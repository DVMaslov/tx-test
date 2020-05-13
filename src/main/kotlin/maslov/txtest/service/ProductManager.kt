package maslov.txtest.service

import maslov.txtest.persistance.ProductEntity
import maslov.txtest.persistance.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductManager(
        private val productRepository: ProductRepository
) {

    @Transactional
    fun get(productId: Long): ProductEntity =
            productRepository.findById(productId)
                    .orElseThrow { IllegalStateException("Product#$productId not found") }
}