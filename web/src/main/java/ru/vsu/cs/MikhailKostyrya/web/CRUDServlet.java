package ru.vsu.cs.MikhailKostyrya.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.vsu.cs.MikhailKostyrya.models.Model;
import ru.vsu.cs.MikhailKostyrya.repo.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public abstract class CRUDServlet<T extends Model> extends HttpServlet {
    protected final Class<T> tClass;
    private final Repository<T> tRepository;

    protected final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
    protected final ObjectMapper objectMapper = new ObjectMapper();

    public CRUDServlet(Class<T> tClass, Repository<T> tRepository) {
        this.tClass = tClass;
        this.tRepository = tRepository;
    }

    protected final Integer getIdFromPath(String path){
        String[] params = path.split("/");

        if(params.length != 0 && params.length != 2) {
            throw new IllegalArgumentException("illegal path");
        }
        if(params.length == 0){
            return null;
        }
        try {
            return Integer.parseInt(params[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("illegal path");
        }
    }

    public abstract List<T> getWithParams(HttpServletRequest request);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id;
        try {
            id = getIdFromPath(request.getPathInfo());
        } catch (IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.toString());
            return;
        }
        Object value;
        if( id != null) {
            value = tRepository.read(id);
        }
        else {
            value = getWithParams(request);
           // value = tRepository.read();
        }

        if(value == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        PrintWriter out = response.getWriter();
        objectWriter.writeValue(out, value);
        out.flush();
    }


    protected abstract boolean validate(T obj);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getPathInfo().split("/").length != 0){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "illegal path");
            return;
        }

        request.getReader();
        T got = objectMapper.readValue(request.getReader(), tClass);

        if(!validate(got) && got.getId() != null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error while parsing");
            return;
        }
        try {
            tRepository.create(got);
            PrintWriter out = response.getWriter();
            objectWriter.writeValue(out, got);
            out.flush();
        }
        catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error appending in db");
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getPathInfo().split("/").length != 0){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "illegal path");
            return;
        }

        request.getReader();
        T got = objectMapper.readValue(request.getReader(), tClass);

        if(!validate(got) && got.getId() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error while parsing body");
            return;
        }
        try {
            tRepository.update(got.getId(), got);
        }
        catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error appending in db" + e.toString());
            return;
        }
        response.sendError(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        Integer id;
        try {
            id = getIdFromPath(request.getPathInfo());
        } catch (IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error parsing id");
            return;
        }

        if( id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error parsing id");
            return;
        }

        try {
            tRepository.delete(id);
        } catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error deleting from db");
        }
        response.sendError(HttpServletResponse.SC_OK);
    }


}
