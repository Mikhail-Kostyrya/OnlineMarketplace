package ru.vsu.cs.MikhailKostyrya;

import ru.vsu.cs.MikhailKostyrya.generator.Generator;
import ru.vsu.cs.MikhailKostyrya.json.deserializer.Deserializer;
import ru.vsu.cs.MikhailKostyrya.json.serializer.Serializer;
import ru.vsu.cs.MikhailKostyrya.memory_repo.MemoryRepoLib;
import ru.vsu.cs.MikhailKostyrya.models.*;
import ru.vsu.cs.MikhailKostyrya.repo.RepoLib;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("write or read file? w/r");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        RepoLib repoLib = MemoryRepoLib.getINSTANCE();

        if (Objects.equals(s, "w")) {
            Generator.Generate(repoLib);
            Serializer.serialize(new File("dump.json"), repoLib);
        }
        else {
            Deserializer.deserializer(new File("dump.json"), repoLib);
            printInfo();
            // sount

        }
    }

    public static void printInfo(){
        RepoLib repoLib = MemoryRepoLib.getINSTANCE();
        for (Client c : repoLib.getAClientRepo().read()) {
            System.out.println(c.getId() + ", " + c.getFirstName() + ", " + c.getLastName() + ", " + c.getEmail());
            System.out.println("orders: ");
            for (Order order : repoLib.getOrderRepo().getByClientId(c.getId())) {
                System.out.println("\t" + order.getStatus());
                System.out.println("\t" + repoLib.getAddressRepo().read(order.getShippingAddressId()).getStreet());
                System.out.println("\tproducts: ");
                for (Product product : repoLib.getProductRepo().getByOrderId(order.getId())) {
                    System.out.println("\t\t" + product.getName());
                }
                System.out.println("---");
            }
            System.out.println("------------");
        }
    }
}
