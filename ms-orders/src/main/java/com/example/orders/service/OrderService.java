package com.example.orders.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.orders.model.Order;

@Service
public final class OrderService {
  private final Map<Long, Order> repo = new HashMap<>();
  private final AtomicLong seq = new AtomicLong(1);

  public OrderService() {
   
    save(new Order(null, "Orden inicial", 50.00));
  }

  public List<Order> findAll() { return new ArrayList<>(repo.values()); }

  public Optional<Order> findById(Long id) { return Optional.ofNullable(repo.get(id)); }

  public Order save(Order o) {
    if (o.getId() == null) o.setId(seq.getAndIncrement());
    repo.put(o.getId(), o);
    return o;
  }
}
