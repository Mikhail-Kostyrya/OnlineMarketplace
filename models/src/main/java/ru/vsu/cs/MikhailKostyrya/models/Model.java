package ru.vsu.cs.MikhailKostyrya.models;

public abstract class Model {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
