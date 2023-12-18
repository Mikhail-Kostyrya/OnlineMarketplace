package ru.vsu.cs.MikhailKostyrya.repo;

import ru.vsu.cs.MikhailKostyrya.models.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {
    List<Product> getByOrderId(long order_id);
}
