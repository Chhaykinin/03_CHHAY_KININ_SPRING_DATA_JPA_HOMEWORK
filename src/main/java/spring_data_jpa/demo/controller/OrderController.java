package spring_data_jpa.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_data_jpa.demo.model.Order;
import spring_data_jpa.demo.model.Product;
import spring_data_jpa.demo.model.dto.request.OrderRequest;
import spring_data_jpa.demo.model.enums.OrderStatus;
import spring_data_jpa.demo.model.response.APIResponse;
import spring_data_jpa.demo.model.response.OrderResponse;
import spring_data_jpa.demo.service.OrderService;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{customerId}")
    public ResponseEntity<APIResponse<OrderResponse>> createOrder(@PathVariable Long customerId, @RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse= orderService.createOrder(customerId,orderRequest);
        APIResponse<OrderResponse> response= APIResponse.<OrderResponse> builder()
                .message("Create order successfully")
                .payload(orderResponse)
                .status(HttpStatus.CREATED)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Order>> findOrderById(@PathVariable Long id){
        Order order= orderService.findOrderById(id);
        APIResponse<Order> response= APIResponse.<Order> builder()
                .message("Find order by "+ id +" successfully")
                .payload(order)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<APIResponse<Order>> findOrderByCustomerId(@PathVariable Long customerId){
        Order order= orderService.findOrderByCustomerId(customerId);
        APIResponse<Order> response= APIResponse.<Order> builder()
                .message("Find order by "+ customerId +" successfully")
                .payload(order)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/status")
    public ResponseEntity<APIResponse<Order>> updateStatus(
            @RequestParam Long orderId,
            @RequestParam OrderStatus status) {

        Order updatedOrder = orderService.updateStatus(orderId, status);

        APIResponse<Order> response = APIResponse.<Order> builder()
                .message("Order status updated successfully")
                .payload(updatedOrder)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
