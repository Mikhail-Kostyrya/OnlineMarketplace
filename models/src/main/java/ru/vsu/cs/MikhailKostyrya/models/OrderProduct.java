package ru.vsu.cs.MikhailKostyrya.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct extends Model {
    protected long orderId; // ссылка на заказ
    protected long productId; // ссылка на продукт
    protected int quantity;
}
