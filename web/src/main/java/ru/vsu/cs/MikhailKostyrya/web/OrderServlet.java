package ru.vsu.cs.MikhailKostyrya.web;


import ru.vsu.cs.MikhailKostyrya.models.Address;
import ru.vsu.cs.MikhailKostyrya.models.Order;
import ru.vsu.cs.MikhailKostyrya.sql_repos.AddressSQLRepo;
import ru.vsu.cs.MikhailKostyrya.sql_repos.OrderSQLRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "orders", urlPatterns = {"/orders/*"} )
public class OrderServlet extends CRUDServlet<Order> {
    public OrderServlet() {
        super(Order.class, OrderSQLRepo.getINSTANCE());
    }

    @Override
    protected boolean validate(Order obj) {
        return
                obj.getClientId() != null &&
                obj.getStatus() != null &&
                obj.getShippingAddressId() != null;
    }

    @Override
    public List<Order> getWithParams(HttpServletRequest request) {
        String client_id = request.getParameter("client_id");
        if(client_id != null) {
            return OrderSQLRepo.getINSTANCE().getByClientId(Integer.parseInt(client_id));
        }
        return OrderSQLRepo.getINSTANCE().read();
    }
}
