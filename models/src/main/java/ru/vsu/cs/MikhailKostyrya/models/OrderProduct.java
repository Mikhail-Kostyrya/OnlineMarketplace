package ru.vsu.cs.MikhailKostyrya.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct extends Model {
    protected Long orderId; // ссылка на заказ
    protected Long productId; // ссылка на продукт
    protected Integer quantity;
}
