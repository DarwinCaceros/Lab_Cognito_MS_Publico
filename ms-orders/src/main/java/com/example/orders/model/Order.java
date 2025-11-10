package com.example.orders.model;

public class Order {
  private Long id;
  private String description;
  private double total;

  public Order() {}
  public Order(Long id, String description, double total) {
    this.id = id;
    this.description = description;
    this.total = total;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public double getTotal() { return total; }
  public void setTotal(double total) { this.total = total; }
}
