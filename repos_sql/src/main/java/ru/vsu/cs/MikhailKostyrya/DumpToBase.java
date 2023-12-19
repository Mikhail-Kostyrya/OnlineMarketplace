package ru.vsu.cs.MikhailKostyrya;

import ru.vsu.cs.MikhailKostyrya.json.deserializer.Deserializer;
import ru.vsu.cs.MikhailKostyrya.repo.RepoLib;
import ru.vsu.cs.MikhailKostyrya.sql_repo.SQLRepoLib;

import java.io.File;

import static ru.vsu.cs.MikhailKostyrya.Main.printInfo;

public class DumpToBase {
    public static void main(String[] args) {
        RepoLib repoLib = SQLRepoLib.getINSTANCE();
        repoLib.getAddressRepo().clear();
        repoLib.getAClientRepo().clear();
        repoLib.getOrderRepo().clear();
        repoLib.getOrderProductRepo().clear();
        repoLib.getProductRepo().clear();
        repoLib.getWarehouseRepo().clear();

        Deserializer.deserializer(new File("dump.json"), repoLib);

        printInfo(repoLib);
    }
}
