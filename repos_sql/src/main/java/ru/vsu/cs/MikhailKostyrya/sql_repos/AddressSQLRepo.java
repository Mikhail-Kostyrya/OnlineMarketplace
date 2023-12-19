package ru.vsu.cs.MikhailKostyrya.sql_repos;

import ru.vsu.cs.MikhailKostyrya.memory_repos.AddressMemoryRepo;
import ru.vsu.cs.MikhailKostyrya.models.Address;
import ru.vsu.cs.MikhailKostyrya.models.Client;
import ru.vsu.cs.MikhailKostyrya.repo.AddressRepository;
import ru.vsu.cs.MikhailKostyrya.sql_connectors.Filter;
import ru.vsu.cs.MikhailKostyrya.sql_repo.RepositorySQL;

import java.util.List;

public class AddressSQLRepo extends RepositorySQL<Address> implements AddressRepository {
    private AddressSQLRepo() {
        super(Address.class, "address");
    }

    private static AddressSQLRepo INSTANCE;

    public static AddressSQLRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new AddressSQLRepo();
        }
        return INSTANCE;
    }

    @Override
    public List<Address> getByClientId(long client_id) {
        return read(new Filter[]{new Filter("clientId", Long.toString(client_id))});
    }

}
