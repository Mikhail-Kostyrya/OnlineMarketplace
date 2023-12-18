package ru.vsu.cs.MikhailKostyrya.memory_repos;

import ru.vsu.cs.MikhailKostyrya.memory_repo.RepositoryMemory;
import ru.vsu.cs.MikhailKostyrya.models.OrderProduct;

public class OrderProductMemoryRepo extends RepositoryMemory<OrderProduct> {
    private OrderProductMemoryRepo(){

    }

    private static OrderProductMemoryRepo INSTANCE;

    public static OrderProductMemoryRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new OrderProductMemoryRepo();
        }
        return INSTANCE;
    }

    @Override
    protected boolean checkForeignKeys(OrderProduct obj) {
        return
                OrderMemoryRepo.getINSTANCE().read(obj.getOrderId()) != null &&
                ProductMemoryRepo.getINSTANCE().read(obj.getProductId()) != null;
    }
}
