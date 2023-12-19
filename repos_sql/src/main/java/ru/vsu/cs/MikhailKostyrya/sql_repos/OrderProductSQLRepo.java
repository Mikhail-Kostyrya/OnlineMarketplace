package ru.vsu.cs.MikhailKostyrya.sql_repos;

import ru.vsu.cs.MikhailKostyrya.models.Address;
import ru.vsu.cs.MikhailKostyrya.models.OrderProduct;
import ru.vsu.cs.MikhailKostyrya.repo.AddressRepository;
import ru.vsu.cs.MikhailKostyrya.sql_connectors.Filter;
import ru.vsu.cs.MikhailKostyrya.sql_repo.RepositorySQL;

import java.util.List;

public class OrderProductSQLRepo extends RepositorySQL<OrderProduct>{
    private OrderProductSQLRepo() {
        super(OrderProduct.class, "order_product");
    }

    private static OrderProductSQLRepo INSTANCE;

    public static OrderProductSQLRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new OrderProductSQLRepo();
        }
        return INSTANCE;
    }


}
