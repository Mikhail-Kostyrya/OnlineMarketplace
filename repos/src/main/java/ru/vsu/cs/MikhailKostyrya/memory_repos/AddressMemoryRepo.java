package ru.vsu.cs.MikhailKostyrya.memory_repos;

import ru.vsu.cs.MikhailKostyrya.memory_repo.RepositoryMemory;
import ru.vsu.cs.MikhailKostyrya.models.Address;
import ru.vsu.cs.MikhailKostyrya.repo.AddressRepository;

import java.util.List;

public class AddressMemoryRepo extends RepositoryMemory<Address> implements AddressRepository {
    private AddressMemoryRepo(){

    }

    private static AddressMemoryRepo INSTANCE;

    public static AddressMemoryRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new AddressMemoryRepo();
        }
        return INSTANCE;
    }


    @Override
    protected boolean checkForeignKeys(Address obj) {
        return ClientMemoryRepo.getINSTANCE().read(obj.getClientId()) != null;
    }

    @Override
    public List<Address> getByClientId(long client_id) {
        return read().stream().filter(e -> e.getClientId() == client_id).toList();
    }
}
