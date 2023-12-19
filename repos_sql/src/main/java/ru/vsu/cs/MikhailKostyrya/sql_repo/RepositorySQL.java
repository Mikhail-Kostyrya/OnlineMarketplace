package ru.vsu.cs.MikhailKostyrya.sql_repo;

import ru.vsu.cs.MikhailKostyrya.models.Model;
import ru.vsu.cs.MikhailKostyrya.repo.Repository;
import ru.vsu.cs.MikhailKostyrya.sql_connectors.Filter;
import ru.vsu.cs.MikhailKostyrya.sql_connectors.PostgreSQLConn;
import ru.vsu.cs.MikhailKostyrya.sql_connectors.SQLConnector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositorySQL<T extends Model> implements Repository<T> {
    protected final Class<T> clazz;
    protected final String tableName;

    protected final SQLConnector sqlConnector;

    protected final Field[] fields;

    protected RepositorySQL(Class<T> clazz, String tableName){
        this.clazz = clazz;
        this.tableName = tableName;

        try {
            sqlConnector = PostgreSQLConn.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        fields = clazz.getDeclaredFields();
    }

    protected String getColumnsCommaDivided(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].getName());
            if(i != fields.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    protected String fieldToGetter(Field field){
        String fieldStr = field.getName();
        String st = fieldStr.substring(0, 1);
        st = st.toUpperCase();

        return "get" + st + fieldStr.substring(1);
    }

    protected String getFieldValue(Field field, T obj){
        String getter = fieldToGetter(field);
        try {
            return clazz.getMethod(getter).invoke(obj).toString();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long create(T obj) {

        StringBuilder queryString = new StringBuilder();
        queryString.append(String.format(
                "INSERT INTO %s (", tableName));

        if(obj.getId() != null) {
            queryString.append("ID,");
        }
        queryString.append(" ");
        queryString.append(getColumnsCommaDivided());
        queryString.append(" ) VALUES ( ");
        if(obj.getId() != null) {
            queryString.append(obj.getId());
            queryString.append(", ");
        }

        for (int i = 0; i < fields.length; i++) {
            queryString.append("'");
            queryString.append(getFieldValue(fields[i], obj));
            queryString.append("'");
            if(i != fields.length - 1) {
                queryString.append(", ");
            }
        }
        queryString.append(" );");

        try {
            PreparedStatement p = sqlConnector.makeUpdate(queryString.toString());
            ResultSet resultSet = p.getGeneratedKeys();

            if(!resultSet.next()) {
                throw new RuntimeException();
            }
            obj.setId(resultSet.getLong("id"));
            if(resultSet.next()){
                throw new RuntimeException();
            }

            p.close();

        } catch (SQLException e) {
            System.out.println(queryString);
            throw new RuntimeException(e);
        }


        return obj.getId();
    }

    protected T getNewT(ResultSet resultSet){
        try {
            T obj = clazz.getDeclaredConstructor().newInstance();
            obj.setId(resultSet.getLong("id"));

            for (Field field : fields) {
                field.setAccessible(true);
                final Class<?> type = field.getType();

                field.set(obj, resultSet.getObject(field.getName()));
                field.setAccessible(false);
            }
            return obj;

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static StringBuilder buildWhere(Filter[] filters){
        if (filters.length == 0) {
            return new StringBuilder();
        }
        StringBuilder queryString = new StringBuilder();
        queryString.append(" WHERE ");
        for (int i = 0; i < filters.length; i++) {
            Filter filter = filters[i];
            queryString.append(filter.column);
            queryString.append(" = ");
            queryString.append(filter.value);
            if (i != filters.length - 1) {
                queryString.append(" AND ");
            }
        }
        return queryString;
    }

    public List<T> read(Filter[] filters) {
        StringBuilder queryString = new StringBuilder("SELECT ");
        queryString.append("id, ");
        queryString.append(getColumnsCommaDivided());
        queryString.append(" FROM ").append(tableName);
        queryString.append(buildWhere(filters));



        List<T> result = new ArrayList<>();

        try {

            ResultSet resultSet = sqlConnector.makeQuery(queryString.toString());
            while (resultSet.next()) {
                T newObject = getNewT(resultSet);
                result.add(newObject);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<T> read() {
        return read(new Filter[0]);
    }

    @Override
    public T read(long id) {
        List<T> objs = read(new Filter[]{new Filter("id", Long.toString(id))});
        if(objs.size() == 0) {
            return null;
        }
        if(objs.size() == 1) {
            return objs.get(0);
        }
        else {
            throw new RuntimeException("illegal db state, wtf?");
        }
    }

    @Override
    public void update(long id, T obj) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(String.format("UPDATE %s SET ", tableName));

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String column = field.getName();
            queryString.append(String.format("%s = '%s'", column, getFieldValue(field, obj)));

            if (i != fields.length - 1) {
                queryString.append(", ");
            }
        }

        queryString.append(" where id = ").append(id);
        try {
            PreparedStatement p = sqlConnector.makeUpdate(queryString.toString());
            int affectedRows = p.getUpdateCount();
            if(affectedRows != 1){
                throw new IllegalArgumentException();
            }
            p.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        String queryString = String.format("DELETE FROM %S WHERE ID = %d", tableName, id);

        try {
            PreparedStatement p = sqlConnector.makeUpdate(queryString);
            int affectedRows = p.getUpdateCount();
            if(affectedRows != 1){
                throw new IllegalArgumentException();
            }
            p.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        String queryString = String.format("TRUNCATE %S CASCADE", tableName);
        try {
            PreparedStatement p = sqlConnector.makeUpdate(queryString);

            p.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
