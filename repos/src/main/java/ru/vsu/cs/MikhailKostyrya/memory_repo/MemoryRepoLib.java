package ru.vsu.cs.MikhailKostyrya.memory_repo;

import ru.vsu.cs.MikhailKostyrya.memory_repos.*;
import ru.vsu.cs.MikhailKostyrya.models.*;
import ru.vsu.cs.MikhailKostyrya.repo.*;

public class MemoryRepoLib implements RepoLib {
    private static MemoryRepoLib INSTANCE;

    public static MemoryRepoLib getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new MemoryRepoLib();
        }
        return INSTANCE;
    }

    private MemoryRepoLib(){

    }

    @Override
    public AddressRepository getAddressRepo() {
        return AddressMemoryRepo.getINSTANCE();
    }

    @Override
    public Repository<Client> getAClientRepo() {
        return ClientMemoryRepo.getINSTANCE();
    }

    @Override
    public OrderRepo getOrderRepo() {
        return OrderMemoryRepo.getINSTANCE();
    }

    @Override
    public Repository<OrderProduct> getOrderProductRepo() {
        return OrderProductMemoryRepo.getINSTANCE();
    }

    @Override
    public ProductRepository getProductRepo() {
        return ProductMemoryRepo.getINSTANCE();
    }

    @Override
    public Repository<Warehouse> getWarehouseRepo() {
        return WarehouseMemoryRepo.getINSTANCE();
    }
}
