package com.capgemini.onlineshopping.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlineshopping.entity.LineItem;
import com.capgemini.onlineshopping.entity.Order;
import com.capgemini.onlineshopping.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/lineitem")
	public ResponseEntity<LineItem> addLineItem(@RequestBody LineItem item,@PathVariable int customerId) {
		orderService.addLineItem(item, customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/lineitem")
	public ResponseEntity<Set<LineItem>> getLineItems(int customerId) {
		Set<LineItem> tempSet = orderService.getLineItems(customerId);
		return new ResponseEntity<Set<LineItem>>(tempSet, HttpStatus.OK);
	}

	@DeleteMapping("/lineitem")
	public ResponseEntity<Set<LineItem>> removeLineItem(int customerId, @RequestBody LineItem item) {
		orderService.removeLineItem(item, customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/order")
	public ResponseEntity<Order> submitOrder(@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.submitOrder(order), HttpStatus.OK);

	}
	
	@PutMapping("/order/{orderId}")
	public ResponseEntity<Order> cancelOrder(@PathVariable int orderId) {
		orderService.cancelOrder(orderId);
		return new ResponseEntity<Order>(HttpStatus.OK);

	}
	
	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable int orderId){
		orderService.deleteOrder(orderId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/order")
	public ResponseEntity<Order> editOrder(@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.editOrder(order), HttpStatus.OK);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
		return new ResponseEntity<Order>(orderService.getOrder(orderId), HttpStatus.OK);
	}
	

	@GetMapping("/orders/{customerId}")
	public ResponseEntity<List<Order>> getOrders(@PathVariable int customerId)  {
		return new ResponseEntity<List<Order>>(orderService.getOrders(customerId), HttpStatus.OK);
	}
	
	
}