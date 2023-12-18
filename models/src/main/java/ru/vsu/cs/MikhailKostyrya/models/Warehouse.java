package ru.vsu.cs.MikhailKostyrya.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse extends Model {
    protected String name;
    protected String address;
}
