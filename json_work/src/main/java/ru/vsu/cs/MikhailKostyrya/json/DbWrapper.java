package ru.vsu.cs.MikhailKostyrya.json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.vsu.cs.MikhailKostyrya.models.*;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DbWrapper {
    List<Address> addresses;
    List<Client> clients;
    List<Order> orders;
    List<OrderProduct> orderProducts;
    List<Product> products;
    List<Warehouse> warehouses;
}
