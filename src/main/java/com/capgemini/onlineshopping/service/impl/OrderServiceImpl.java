package com.capgemini.onlineshopping.service.impl;




import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlineshopping.entity.LineItem;
import com.capgemini.onlineshopping.entity.Order;
import com.capgemini.onlineshopping.exceptions.OrderNotFoundException;
import com.capgemini.onlineshopping.repository.OrderRepository;
import com.capgemini.onlineshopping.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	private HashMap<Integer, Set<LineItem>> itemCart = new HashMap<>();

	@Override
	public void addLineItem(LineItem item, int customerId) {
		Set<LineItem> tempSet = itemCart.get(customerId);
		if (tempSet == null) {
			tempSet = new HashSet<>();
			tempSet.add(item);
			itemCart.put(customerId, tempSet);
		} else {
			tempSet.add(item);
			itemCart.put(customerId, tempSet);
		}
	}

	@Override
	public void removeLineItem(LineItem item, int customerId) {
		Set<LineItem> tempSet = itemCart.get(customerId);
		if (tempSet != null) {
			tempSet.remove(item);
			itemCart.put(customerId, tempSet);
		}

	}

	@Override
	public Set<LineItem> getLineItems(int customerId) {
		Set<LineItem> tempSet = itemCart.get(customerId);
		return tempSet;
	}

	
	@Override
	public List<Order> getOrders(int customerId) {
		return orderRepository.findByCustomerId(customerId);
	}
	
	

	@Override
	public Order getOrder(int orderId) throws OrderNotFoundException {
		Optional<Order> orderFromDb = orderRepository.findById(orderId);
		if (orderFromDb.isPresent()) {
			return orderFromDb.get();
			}
		throw new OrderNotFoundException("Order not found");
	}
	
	
	
	@Override
	public Order submitOrder(Order order) {
        
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(int orderId) throws OrderNotFoundException {
			Optional<Order> orderFromDb = orderRepository.findById(orderId);
			if (orderFromDb.isPresent()) {
				orderFromDb.get().setStatus("deleted");
				orderRepository.deleteById(orderId);
				return;
			}
			throw new OrderNotFoundException("Order not found");
		}
	
	


	@Override
	public Order editOrder(Order order) throws OrderNotFoundException {
			Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
			if (orderFromDb.isPresent()) {
				orderFromDb.get().setStatus("ordered");
				return orderRepository.save(order);
			}
			throw new OrderNotFoundException("Order not found");
			
		}

	@Override
	public Order cancelOrder(int orderId) throws OrderNotFoundException {
		Optional<Order> orderFromDb = orderRepository.findById(orderId);
		if (orderFromDb.isPresent()) {
			orderFromDb.get().setStatus("cancelled");
			return orderRepository.save(orderFromDb.get());
		}
		throw new OrderNotFoundException("Order not found");
		
	}
		
}

