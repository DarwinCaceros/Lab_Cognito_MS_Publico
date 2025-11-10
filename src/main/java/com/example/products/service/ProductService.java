package com.example.products.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.products.model.Product;

@Service
public final class ProductService {
  private final Map<Long, Product> repo = new HashMap<>();
  private final AtomicLong seq = new AtomicLong(1);

  public ProductService() {
    save(new Product(null, "Producto demo", 9.99));
  }

  public List<Product> findAll() { return new ArrayList<>(repo.values()); }
  public Optional<Product> findById(Long id) { return Optional.ofNullable(repo.get(id)); }
  public Product save(Product p) {
    if (p.getId() == null) p.setId(seq.getAndIncrement());
    repo.put(p.getId(), p);
    return p;
  }
}
