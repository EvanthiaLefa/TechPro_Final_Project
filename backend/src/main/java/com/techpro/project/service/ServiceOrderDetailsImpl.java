package com.techpro.project.service;

import com.techpro.project.entity.OrderDetails;
import com.techpro.project.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceOrderDetailsImpl implements ServiceOrderDetails{

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

   /**
   * Saves an orderDetails.
   * @param orderDetails The orderDetails to be saved.
   * @return The saved orderDetails object.
   */
    public  OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

   /** Retrieves a list of all orderDetails.
   * <p>
   * It retrieves all orderDetails using the OrderDetailsRepository's findAll() method.
   * @return A List of Order objects representing all the orderDetails.
   */
    public List<OrderDetails> findAll(){
        return orderDetailsRepository.findAll();
    }
}
