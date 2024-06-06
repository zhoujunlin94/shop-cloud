package io.github.zhoujunlin94.server.product.controller;

import io.github.zhoujunlin94.api.product.ProductDTO;
import io.github.zhoujunlin94.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author zhoujunlin
 * @date 2024/6/6 22:52
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/findById")
    public ProductDTO findById(@RequestParam @NotNull(message = "productId不可为空") Integer productId) {
        return productService.findById(productId);
    }

}
