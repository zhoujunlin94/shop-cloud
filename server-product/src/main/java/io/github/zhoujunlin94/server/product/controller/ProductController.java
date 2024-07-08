package io.github.zhoujunlin94.server.product.controller;

import io.github.zhoujunlin94.server.product.dto.ProductDTO;
import io.github.zhoujunlin94.server.product.dto.ProductReduceStockDTO;
import io.github.zhoujunlin94.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zhoujunlin
 * @date 2024/6/6 22:52
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/external/api/v1/product")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/findById")
    public ProductDTO findById(@RequestParam @NotNull(message = "productId不可为空") Integer productId) {
        return productService.findById(productId);
    }

    @PostMapping("/reduceStock")
    public void reduceStock(@RequestBody @Valid ProductReduceStockDTO reduceStockDTO) {
        productService.reduceStock(reduceStockDTO.getProductId(), reduceStockDTO.getAmount());
    }

}
