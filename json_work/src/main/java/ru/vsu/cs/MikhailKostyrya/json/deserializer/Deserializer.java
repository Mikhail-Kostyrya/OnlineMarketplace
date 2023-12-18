package ru.vsu.cs.MikhailKostyrya.json.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vsu.cs.MikhailKostyrya.json.DbWrapper;
import ru.vsu.cs.MikhailKostyrya.models.*;
import ru.vsu.cs.MikhailKostyrya.repo.RepoLib;

import java.io.File;
import java.io.IOException;

public class Deserializer {
    public static void deserializer(File file, RepoLib repoLib){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DbWrapper DbWrapper = objectMapper.readValue(file, DbWrapper.class);
            for (Client client : DbWrapper.getClients()) {
                repoLib.getAClientRepo().create(client);
            }

            for (Address address : DbWrapper.getAddresses()) {
                repoLib.getAddressRepo().create(address);
            }

            for (Warehouse warehouse : DbWrapper.getWarehouses()) {
                repoLib.getWarehouseRepo().create(warehouse);
            }

            for (Product product : DbWrapper.getProducts()) {
                repoLib.getProductRepo().create(product);
            }

            for (Order order : DbWrapper.getOrders()) {
                repoLib.getOrderRepo().create(order);
            }

            for (OrderProduct orderProduct : DbWrapper.getOrderProducts()) {
                repoLib.getOrderProductRepo().create(orderProduct);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException("wrong file");
        }
    }
}
