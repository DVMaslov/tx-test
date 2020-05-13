package maslov.txtest.controller

import maslov.txtest.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(
        private val productService: ProductService
) {

    @GetMapping("{id}/calc")
    fun calc(@PathVariable("id") id: Long) = productService.calcSum(id)
}