package celiocausjunior.DSCommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import celiocausjunior.DSCommerce.models.OrderItem;
import celiocausjunior.DSCommerce.models.OrderModel;
import celiocausjunior.DSCommerce.models.ProductModel;
import celiocausjunior.DSCommerce.models.UserModel;
import celiocausjunior.DSCommerce.models.dtos.OrderDTO;
import celiocausjunior.DSCommerce.models.dtos.OrderItemDTO;
import celiocausjunior.DSCommerce.models.enums.OrderStatus;
import celiocausjunior.DSCommerce.repositories.OrderItemRepository;
import celiocausjunior.DSCommerce.repositories.OrderRepository;
import celiocausjunior.DSCommerce.repositories.ProductRepository;
import celiocausjunior.DSCommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

  @Autowired
  private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

      @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        return orderRepository.findById(id).map(order -> new OrderDTO(order))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        OrderModel order = new OrderModel();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
       
        UserModel user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            ProductModel product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        order = orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }

    
}
