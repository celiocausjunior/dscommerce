package celiocausjunior.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import celiocausjunior.DSCommerce.models.dtos.OrderDTO;
import celiocausjunior.DSCommerce.repositories.OrderRepository;
import celiocausjunior.DSCommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

      @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        return orderRepository.findById(id).map(order -> new OrderDTO(order))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    
}
