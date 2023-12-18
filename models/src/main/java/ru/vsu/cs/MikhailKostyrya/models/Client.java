package ru.vsu.cs.MikhailKostyrya.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Model {
    protected String firstName;
    protected String lastName;
    protected String email;

}

