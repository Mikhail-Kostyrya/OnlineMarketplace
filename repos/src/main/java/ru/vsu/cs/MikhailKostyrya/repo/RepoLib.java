package ru.vsu.cs.MikhailKostyrya.repo;

import ru.vsu.cs.MikhailKostyrya.models.*;

public interface RepoLib {
    AddressRepository getAddressRepo();
    Repository<Client> getAClientRepo();
    OrderRepository getOrderRepo();
    Repository<OrderProduct> getOrderProductRepo();
    ProductRepository getProductRepo();
    Repository<Warehouse> getWarehouseRepo();

}
