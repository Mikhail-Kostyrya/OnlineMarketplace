package ru.vsu.cs.MikhailKostyrya.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Model {
    protected String status;
    protected long clientId; // ссылка на клиента
    protected long shippingAddressId; // ссылка на адрес
}
