package ru.vsu.cs.MikhailKostyrya.memory_repos;

import ru.vsu.cs.MikhailKostyrya.memory_repo.RepositoryMemory;
import ru.vsu.cs.MikhailKostyrya.models.Warehouse;

public class WarehouseMemoryRepo extends RepositoryMemory<Warehouse> {
    private WarehouseMemoryRepo(){

    }

    private static WarehouseMemoryRepo INSTANCE;

    public static WarehouseMemoryRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new WarehouseMemoryRepo();
        }
        return INSTANCE;
    }

    @Override
    protected boolean checkForeignKeys(Warehouse obj) {
        return true;
    }
}
