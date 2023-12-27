package ru.vsu.cs.MikhailKostyrya.web;


import ru.vsu.cs.MikhailKostyrya.models.Product;
import ru.vsu.cs.MikhailKostyrya.models.Warehouse;
import ru.vsu.cs.MikhailKostyrya.sql_repos.ProductSQLRepo;
import ru.vsu.cs.MikhailKostyrya.sql_repos.WarehouseSQLRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "warehouses", urlPatterns = {"/warehouses/*"} )
public class WarehouseServlet extends CRUDServlet<Warehouse> {
    public WarehouseServlet() {
        super(Warehouse.class, WarehouseSQLRepo.getINSTANCE());
    }

    @Override
    public List<Warehouse> getWithParams(HttpServletRequest request) {
        return WarehouseSQLRepo.getINSTANCE().read();
    }

    @Override
    protected boolean validate(Warehouse obj) {
        return
                obj.getAddress() != null &&
                obj.getName() != null;
    }
}
