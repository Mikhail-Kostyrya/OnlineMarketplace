package ru.vsu.cs.MikhailKostyrya.web;


import ru.vsu.cs.MikhailKostyrya.models.Order;
import ru.vsu.cs.MikhailKostyrya.models.OrderProduct;
import ru.vsu.cs.MikhailKostyrya.sql_repos.OrderProductSQLRepo;
import ru.vsu.cs.MikhailKostyrya.sql_repos.OrderSQLRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "order_products", urlPatterns = {"/order_products/*"} )
public class OrderProductServlet extends CRUDServlet<OrderProduct> {
    public OrderProductServlet() {
        super(OrderProduct.class, OrderProductSQLRepo.getINSTANCE());
    }

    @Override
    public List<OrderProduct> getWithParams(HttpServletRequest request) {
        return OrderProductSQLRepo.getINSTANCE().read();
    }

    @Override
    protected boolean validate(OrderProduct obj) {
        return
                obj.getProductId() != null &&
                obj.getQuantity() != null &&
                obj.getOrderId() != null;
    }
}
