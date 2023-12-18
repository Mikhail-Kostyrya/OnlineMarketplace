package ru.vsu.cs.MikhailKostyrya.memory_repo;

import ru.vsu.cs.MikhailKostyrya.models.Model;
import ru.vsu.cs.MikhailKostyrya.repo.Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryMemory<T extends Model> implements Repository<T> {
    private final List<T> items = new ArrayList<>();;
    private long id = 0;

    protected abstract boolean checkForeignKeys(T obj);

    @Override
    public long create(T obj) {
        if(obj.getId() == null) {
            obj.setId(id);
        }
        if(!checkForeignKeys(obj)) {
            throw new IllegalArgumentException("illegal foreign key");
        }
        items.add(obj);
        id = Math.max(obj.getId(), id) + 1;
        return obj.getId();
    }

    @Override
    public List<T> read() {
        return new ArrayList<>(items);
    }

    @Override
    public T read(long id) {
        for (T item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void update(long id, T obj) {
        obj.setId(id);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.set(i, obj);
                return;
            }
        }
        throw new IllegalArgumentException("Object with ID " + id + " not found.");
    }

    @Override
    public void delete(long id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Object with ID " + id + " not found.");
    }

    @Override
    public void clear() {
        items.clear();
    }
}
