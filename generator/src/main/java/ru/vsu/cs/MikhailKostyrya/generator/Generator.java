package ru.vsu.cs.MikhailKostyrya.generator;

import ru.vsu.cs.MikhailKostyrya.models.*;
import ru.vsu.cs.MikhailKostyrya.repo.RepoLib;

import java.util.List;
import java.util.Random;

public class Generator {
    protected static Random random = new Random();
    public static void Generate(RepoLib repoLib){
        String[] firstNames = {"Alexander", "Elena", "Benjamin", "Grace", "Daniel", "Ava", "Matthew", "Lily", "Christopher", "Chloe"};
        String[] lastNames = {"Walker", "Clark", "Parker", "Moore", "Robinson", "Carter", "Turner", "Baker", "Wright", "Lewis"};
        for (String first : firstNames) {
            for (String last : lastNames) {
                repoLib.getAClientRepo().create(new Client(first, last, first+last+"@mail.com"));
            }
        }

        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};

        for(Client client: repoLib.getAClientRepo().read()){
            int numAddresses = random.nextInt(3) + 1;
            for (int i = 0; i < numAddresses; i++) {
                String street = "Great address " + (random.nextInt(100000) + 1) + " for " + client.getFirstName();
                String postalCode = String.valueOf(random.nextInt(90000) + 10000);

                repoLib.getAddressRepo().create(new Address(street, pickRandom(cities), postalCode, client.getId()));
            }
        }


        for (int i = 0; i < 100; i++) {
            repoLib.getWarehouseRepo().create(new Warehouse(
                    "Great warehouse number: " + i,
                    "Pretty address for warehouse:" + i + " at" + pickRandom(cities)));
        }

        List<Long> warehousesIds = repoLib.getWarehouseRepo().read().stream().map(Model::getId).toList();

        for (int i = 0; i < 1000; i++) {
            String name = "Wow product " + i;
            double price = random.nextDouble(200, 100000);
            String descr = "Great description for product " + i;
            long warehouseId = pickRandom(warehousesIds);
            repoLib.getProductRepo().create(new Product(name, price, descr, warehouseId));
        }

        List<Long> clientIds = repoLib.getAClientRepo().read().stream().map(Model::getId).toList();
        String[] statuses = {"OPENED", "IN_PROGRESS", "FINISHED"};
        for (int i = 0; i < 10000; i++) {
            String status = pickRandom(statuses);
            long clientId = pickRandom(clientIds);
            List<Long> addressIds = repoLib.getAddressRepo().getByClientId(clientId).stream().map(Model::getId).toList();
            long addressId = pickRandom(addressIds);

            repoLib.getOrderRepo().create(new Order(status, clientId, addressId));
        }

        List<Long> orderIds = repoLib.getOrderRepo().read().stream().map(Model::getId).toList();
        List<Long> productIds = repoLib.getProductRepo().read().stream().map(Model::getId).toList();

        for (int i = 0; i < 1000000; i++) {
            int quantity = random.nextInt(1, 10);
            long orderId = pickRandom(orderIds);
            long productId = pickRandom(productIds);

            repoLib.getOrderProductRepo().create(new OrderProduct(orderId, productId, quantity));
        }
    }

    protected static String pickRandom(String[] array){
        return array[random.nextInt(array.length)];
    }

    protected static Long pickRandom(List<Long> array){
        return array.get(random.nextInt(array.size()));
    }
}
