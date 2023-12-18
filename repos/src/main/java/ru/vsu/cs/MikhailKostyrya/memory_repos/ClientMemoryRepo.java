package ru.vsu.cs.MikhailKostyrya.memory_repos;

import ru.vsu.cs.MikhailKostyrya.models.Client;
import ru.vsu.cs.MikhailKostyrya.memory_repo.RepositoryMemory;

public class ClientMemoryRepo extends RepositoryMemory<Client> {
    private ClientMemoryRepo(){

    }

    private static ClientMemoryRepo INSTANCE;

    public static ClientMemoryRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new ClientMemoryRepo();
        }
        return INSTANCE;
    }

    @Override
    protected boolean checkForeignKeys(Client obj) {
        return true;
    }
}
