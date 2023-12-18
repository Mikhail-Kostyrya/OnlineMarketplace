package ru.vsu.cs.MikhailKostyrya.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Model {
    protected String name;
    protected double price;
    protected String description;
    protected long warehouseId; // ссылка на склад
}
