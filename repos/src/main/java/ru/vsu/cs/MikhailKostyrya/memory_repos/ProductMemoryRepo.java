package ru.vsu.cs.MikhailKostyrya.memory_repos;

import ru.vsu.cs.MikhailKostyrya.memory_repo.RepositoryMemory;
import ru.vsu.cs.MikhailKostyrya.models.OrderProduct;
import ru.vsu.cs.MikhailKostyrya.models.Product;
import ru.vsu.cs.MikhailKostyrya.repo.ProductRepository;

import java.util.List;

public class ProductMemoryRepo extends RepositoryMemory<Product> implements ProductRepository {
    private ProductMemoryRepo(){

    }

    private static ProductMemoryRepo INSTANCE;

    public static ProductMemoryRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new ProductMemoryRepo();
        }
        return INSTANCE;
    }

    @Override
    protected boolean checkForeignKeys(Product obj) {
        return WarehouseMemoryRepo.getINSTANCE().read(obj.getWarehouseId()) != null;
    }

    @Override
    public List<Product> getByOrderId(long order_id) {
        List<Long> ids = OrderProductMemoryRepo.getINSTANCE().read().stream().filter(e -> e.getOrderId() == order_id).
                map(OrderProduct::getProductId).toList();

        return read().stream().filter(e -> ids.contains(e.getId())).toList();
    }
}
