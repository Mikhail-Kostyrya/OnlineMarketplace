package ru.vsu.cs.MikhailKostyrya.json.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.vsu.cs.MikhailKostyrya.json.DbWrapper;
import ru.vsu.cs.MikhailKostyrya.repo.RepoLib;

import java.io.File;
import java.io.IOException;

public class Serializer {
    public static void serialize(File file, RepoLib repoLib){
        ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        DbWrapper dbWrapper = new DbWrapper(
                repoLib.getAddressRepo().read(),
                repoLib.getAClientRepo().read(),
                repoLib.getOrderRepo().read(),
                repoLib.getOrderProductRepo().read(),
                repoLib.getProductRepo().read(),
                repoLib.getWarehouseRepo().read()
        );
        try {
            objectWriter.writeValue(file, dbWrapper);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException("wrong file");
        }
    }
}
