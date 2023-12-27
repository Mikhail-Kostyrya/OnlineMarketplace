package ru.vsu.cs.MikhailKostyrya.web;


import ru.vsu.cs.MikhailKostyrya.models.*;
import ru.vsu.cs.MikhailKostyrya.sql_repos.AddressSQLRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "addresses", urlPatterns = {"/addresses/*"} )
public class AddressServlet extends CRUDServlet<Address> {
    public AddressServlet() {
        super(Address.class, AddressSQLRepo.getINSTANCE());
    }

    @Override
    public List<Address> getWithParams(HttpServletRequest request) {
        String client_id = request.getParameter("client_id");
        if(client_id != null) {
            return AddressSQLRepo.getINSTANCE().getByClientId(Integer.parseInt(client_id));
        }
        return AddressSQLRepo.getINSTANCE().read();
    }

    @Override
    protected boolean validate(Address obj) {
        return
                obj.getCity() != null &&
                obj.getStreet() != null &&
                obj.getClientId() != null &&
                obj.getPostalCode() != null;
    }
}
