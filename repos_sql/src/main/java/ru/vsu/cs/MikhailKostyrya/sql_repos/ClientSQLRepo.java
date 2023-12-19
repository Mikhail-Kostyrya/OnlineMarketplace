package ru.vsu.cs.MikhailKostyrya.sql_repos;

import ru.vsu.cs.MikhailKostyrya.models.Client;
import ru.vsu.cs.MikhailKostyrya.sql_repo.RepositorySQL;

public class ClientSQLRepo extends RepositorySQL<Client> {
    private ClientSQLRepo() {
        super(Client.class, "client");
    }

    private static ClientSQLRepo INSTANCE;

    public static ClientSQLRepo getINSTANCE(){
        if(INSTANCE == null) {
            INSTANCE = new ClientSQLRepo();
        }
        return INSTANCE;
    }


}
