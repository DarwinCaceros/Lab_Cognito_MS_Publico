package com.example.orders.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orders.model.Order;
import com.example.orders.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService svc;
  public OrderController(OrderService svc) { this.svc = svc; }

  @GetMapping
  public List<Order> getAll() {
    return svc.findAll();
  }

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody Order o) {
    Order saved = svc.save(o);
    return ResponseEntity.status(201).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order o) {
    return svc.findById(id).map(existing -> {
      existing.setDescription(o.getDescription());
      existing.setTotal(o.getTotal());
      svc.save(existing);
      return ResponseEntity.ok(existing);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
