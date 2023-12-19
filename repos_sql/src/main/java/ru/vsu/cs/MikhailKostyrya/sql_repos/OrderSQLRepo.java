package ru.vsu.cs.MikhailKostyrya.sql_repos;

import ru.vsu.cs.MikhailKostyrya.models.Order;
import ru.vsu.cs.MikhailKostyrya.models.Product;
import ru.vsu.cs.MikhailKostyrya.repo.OrderRepository;
import ru.vsu.cs.MikhailKostyrya.repo.ProductRepository;
import ru.vsu.cs.MikhailKostyrya.sql_connectors.Filter;
import ru.vsu.cs.MikhailKostyrya.sql_repo.RepositorySQL;

import java.util.List;

public class OrderSQLRepo extends RepositorySQL<Order> implements OrderRepository {
    public OrderSQLRepo() {
        super(Order.class, "order_table");
    }

    private static OrderSQLRepo INSTANCE;

    public static OrderSQLRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new OrderSQLRepo();
        }
        return INSTANCE;
    }

    @Override
    public List<Order> getByClientId(long client_id) {
        return read(new Filter[]{new Filter("clientId", Long.toString(client_id))});
    }
}
