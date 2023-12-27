package ru.vsu.cs.MikhailKostyrya.web;


import ru.vsu.cs.MikhailKostyrya.models.Address;
import ru.vsu.cs.MikhailKostyrya.models.Client;
import ru.vsu.cs.MikhailKostyrya.sql_repos.AddressSQLRepo;
import ru.vsu.cs.MikhailKostyrya.sql_repos.ClientSQLRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "client", urlPatterns = {"/client/*"} )
public class ClientServlet extends CRUDServlet<Client> {
    public ClientServlet() {
        super(Client.class, ClientSQLRepo.getINSTANCE());
    }

    @Override
    public List<Client> getWithParams(HttpServletRequest request) {
        return ClientSQLRepo.getINSTANCE().read();
    }

    @Override
    protected boolean validate(Client obj) {
        return
                obj.getLastName() != null &&
                obj.getEmail() != null &&
                obj.getFirstName() != null;
    }
}
