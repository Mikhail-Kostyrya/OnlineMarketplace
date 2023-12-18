package ru.vsu.cs.MikhailKostyrya.memory_repos;

import ru.vsu.cs.MikhailKostyrya.memory_repo.RepositoryMemory;
import ru.vsu.cs.MikhailKostyrya.models.Order;
import ru.vsu.cs.MikhailKostyrya.repo.OrderRepo;

import java.util.List;

public class OrderMemoryRepo extends RepositoryMemory<Order> implements OrderRepo {
    private OrderMemoryRepo(){

    }

    private static OrderMemoryRepo INSTANCE;

    public static OrderMemoryRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new OrderMemoryRepo();
        }
        return INSTANCE;
    }

    @Override
    protected boolean checkForeignKeys(Order obj) {
        return
                ClientMemoryRepo.getINSTANCE().read(obj.getClientId()) != null &&
                AddressMemoryRepo.getINSTANCE().read(obj.getShippingAddressId()) != null;
    }

    @Override
    public List<Order> getByClientId(long client_id) {
        return read().stream().filter(e -> e.getClientId() == client_id).toList();
    }
}
