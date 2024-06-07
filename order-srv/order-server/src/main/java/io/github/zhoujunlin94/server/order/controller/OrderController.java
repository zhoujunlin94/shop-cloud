package io.github.zhoujunlin94.server.order.controller;

import io.github.zhoujunlin94.api.order.dto.OrderDTO;
import io.github.zhoujunlin94.server.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/createOrder")
    public OrderDTO createOrder(@RequestParam @NotNull(message = "productId不可为空") Integer productId,
                                @RequestParam @NotNull(message = "userId不可为空") Integer userId) {
        return orderService.createOrder(productId, userId);
    }

}
