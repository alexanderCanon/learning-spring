package com.neversion.learning_spring.product.domain.model;

public class Product {
        private Long id;
        private String name;
        private Double price;
        private boolean active;

        public Product(Long id, String name, Double price, boolean active) {
                this.id = id;
                this.name = name;
                this.price = price;
                this.active = active;
        }

        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public Double getPrice() {
                return price;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setPrice(Double price) {
                this.price = price;
        }

        public boolean isActive() {
                return active;
        }

        public void setActive(boolean active) {
                this.active = active;
        }
}
