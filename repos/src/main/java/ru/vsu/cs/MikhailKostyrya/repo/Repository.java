package ru.vsu.cs.MikhailKostyrya.repo;

import ru.vsu.cs.MikhailKostyrya.models.Model;

import java.util.List;

public interface Repository<T extends Model> {
    long create(T obj);

    List<T> read();

    T read(long id);

    void update(long id, T obj);

    void delete(long id);

    void clear();
}
