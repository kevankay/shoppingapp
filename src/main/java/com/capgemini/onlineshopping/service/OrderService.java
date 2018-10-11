package com.capgemini.onlineshopping.service;

import java.util.List;
import java.util.Set;

import com.capgemini.onlineshopping.entity.LineItem;
import com.capgemini.onlineshopping.entity.Order;
import com.capgemini.onlineshopping.exceptions.OrderNotFoundException;

public interface OrderService {

	public void addLineItem(LineItem item, int customerId);

	public void removeLineItem(LineItem item, int customerId);

	public Set<LineItem> getLineItems(int customerId);
	
	public List<Order> getOrders(int customerId) throws OrderNotFoundException;

	public Order getOrder(int orderId) throws OrderNotFoundException;

	public Order submitOrder(Order order);
	
	public Order editOrder(Order order)throws OrderNotFoundException;

	public Order cancelOrder(int orderId) throws OrderNotFoundException;

	public void deleteOrder(int orderId) throws OrderNotFoundException;
}
	