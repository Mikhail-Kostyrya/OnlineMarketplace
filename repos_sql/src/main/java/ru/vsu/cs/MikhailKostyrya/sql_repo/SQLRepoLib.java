package ru.vsu.cs.MikhailKostyrya.sql_repo;

import ru.vsu.cs.MikhailKostyrya.memory_repo.MemoryRepoLib;
import ru.vsu.cs.MikhailKostyrya.models.Client;
import ru.vsu.cs.MikhailKostyrya.models.OrderProduct;
import ru.vsu.cs.MikhailKostyrya.models.Warehouse;
import ru.vsu.cs.MikhailKostyrya.repo.*;
import ru.vsu.cs.MikhailKostyrya.sql_repos.*;

public class SQLRepoLib implements RepoLib {
    private static SQLRepoLib INSTANCE;
    public static SQLRepoLib getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new SQLRepoLib();
        }
        return INSTANCE;
    }

    private SQLRepoLib(){

    }
    @Override
    public AddressRepository getAddressRepo() {
        return AddressSQLRepo.getINSTANCE();
    }

    @Override
    public Repository<Client> getAClientRepo() {
        return ClientSQLRepo.getINSTANCE();
    }

    @Override
    public OrderRepository getOrderRepo() {
        return OrderSQLRepo.getINSTANCE();
    }

    @Override
    public Repository<OrderProduct> getOrderProductRepo() {
        return OrderProductSQLRepo.getINSTANCE();
    }

    @Override
    public ProductRepository getProductRepo() {
        return ProductSQLRepo.getINSTANCE();
    }

    @Override
    public Repository<Warehouse> getWarehouseRepo() {
        return WarehouseSQLRepo.getINSTANCE();
    }
}
