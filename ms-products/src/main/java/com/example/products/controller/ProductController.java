package com.example.products.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.products.model.Product;
import com.example.products.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService svc;
  public ProductController(ProductService svc) { this.svc = svc; }

  @GetMapping
  public List<Product> getAll() { return svc.findAll(); }

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product p) {
    Product saved = svc.save(p);
    return ResponseEntity.status(201).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product p) {
    return svc.findById(id).map(existing -> {
      existing.setName(p.getName());
      existing.setPrice(p.getPrice());
      svc.save(existing);
      return ResponseEntity.ok(existing);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
