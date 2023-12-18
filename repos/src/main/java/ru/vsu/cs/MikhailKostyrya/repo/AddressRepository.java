package ru.vsu.cs.MikhailKostyrya.repo;

import ru.vsu.cs.MikhailKostyrya.models.Address;

import java.util.List;

public interface AddressRepository extends Repository<Address> {
    List<Address> getByClientId(long client_id);
}
