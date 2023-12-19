package ru.vsu.cs.MikhailKostyrya.sql_repos;

import ru.vsu.cs.MikhailKostyrya.models.Client;
import ru.vsu.cs.MikhailKostyrya.models.OrderProduct;
import ru.vsu.cs.MikhailKostyrya.models.Product;
import ru.vsu.cs.MikhailKostyrya.repo.ProductRepository;
import ru.vsu.cs.MikhailKostyrya.sql_connectors.Filter;
import ru.vsu.cs.MikhailKostyrya.sql_repo.RepositorySQL;

import java.util.List;

public class ProductSQLRepo extends RepositorySQL<Product> implements ProductRepository {
    public ProductSQLRepo() {
        super(Product.class, "product");
    }

    private static ProductSQLRepo INSTANCE;

    public static ProductSQLRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new ProductSQLRepo();
        }
        return INSTANCE;
    }

    @Override
    public List<Product> getByOrderId(long order_id) {
        //List<Long> ids = OrderProductMemoryRepo.getINSTANCE().read().stream().filter(e -> e.getOrderId() == order_id).
        //                map(OrderProduct::getProductId).toList();
        //
        //        return read().stream().filter(e -> ids.contains(e.getId())).toList();
        List<Long> ids = OrderProductSQLRepo.getINSTANCE().read(new Filter[]{
                new Filter("orderId", Long.toString(order_id))}).stream().
                map(OrderProduct::getProductId).toList();

        return read().stream().filter(e -> ids.contains(e.getId())).toList();
    }
}
