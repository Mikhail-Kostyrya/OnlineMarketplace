package ru.vsu.cs.MikhailKostyrya.sql_repos;

import ru.vsu.cs.MikhailKostyrya.models.Address;
import ru.vsu.cs.MikhailKostyrya.models.Warehouse;
import ru.vsu.cs.MikhailKostyrya.repo.AddressRepository;
import ru.vsu.cs.MikhailKostyrya.sql_connectors.Filter;
import ru.vsu.cs.MikhailKostyrya.sql_repo.RepositorySQL;

import java.util.List;

public class WarehouseSQLRepo extends RepositorySQL<Warehouse>  {
    private WarehouseSQLRepo() {
        super(Warehouse.class, "warehouse");
    }

    private static WarehouseSQLRepo INSTANCE;

    public static WarehouseSQLRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new WarehouseSQLRepo();
        }
        return INSTANCE;
    }


}
