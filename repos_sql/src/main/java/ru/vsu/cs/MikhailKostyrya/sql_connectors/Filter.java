package ru.vsu.cs.MikhailKostyrya.sql_connectors;

public class Filter {
    public final String column;
    public final String value;

    public Filter(String column, String value) {
        this.column = column;
        this.value = value;
    }
}
