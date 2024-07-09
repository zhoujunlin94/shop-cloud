package io.github.zhoujunlin94.server.product.controller.external;

import io.github.zhoujunlin94.server.product.dto.ProductReduceStockDTO;
import io.github.zhoujunlin94.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhoujunlin
 * @date 2024/6/6 22:52
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/external/api/v1/product")
public class ProductExternalController {

    private final ProductService productService;


    @PostMapping("/reduceStock")
    public void reduceStock(@RequestBody @Valid ProductReduceStockDTO reduceStockDTO) {
        productService.reduceStock(reduceStockDTO.getProductId(), reduceStockDTO.getAmount());
    }

}
