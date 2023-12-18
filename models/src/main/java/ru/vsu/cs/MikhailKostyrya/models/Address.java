package ru.vsu.cs.MikhailKostyrya.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends Model {
    protected String street;
    protected String city;
    protected String postalCode;
    protected long clientId; // ссылка на клиента
}
