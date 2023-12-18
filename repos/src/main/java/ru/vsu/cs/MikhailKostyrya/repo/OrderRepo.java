package ru.vsu.cs.MikhailKostyrya.repo;

import ru.vsu.cs.MikhailKostyrya.models.Order;

import java.util.List;

public interface OrderRepo extends Repository<Order> {
    List<Order> getByClientId(long client_id);
}
