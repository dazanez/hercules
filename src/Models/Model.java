package Models;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ConnectionDB.ConnectionDB;
import ConnectionDB.Utils;

public abstract class Model {
    protected String tableName;
    protected String idName;

    /**
     * Sets the name of the table and its primary key column
     * @param tableName the name of the table in the database
     * @param idName the primary key column name in the database for the current table
     */
    public Model(String tableName, String idName) {
        this.tableName = tableName;
        this.idName = idName;
    }

    /**
     * Gets a register from the current table by its id or primary key
     * @param id the identifier of the register to get.
     * @return the register with the given id. Returns null if no register was found or an error has occurred
     */
    public Map<String, String> getByIdMap(String id) {
        String sql = String.format("SELECT * FROM %s WHERE (%s = '%s');", tableName, idName, id);
        Map<String, String> element = null;
        try {
            element = ConnectionDB.executeQuery(sql).get(0);
        } catch (IndexOutOfBoundsException | NullPointerException ignored) {}
        catch (SQLException err) {
            System.out.println("Ocurrió un error obteniendo el elemento con id " + id + ": "  + err.getMessage());
            err.printStackTrace();
        }

        return element;
    }

    /**
     * Gets the register by the id or primary key
     * @param id the identifier or primary key of the table
     * @return an object of the model with the attributes of the register
     */
    public abstract Model getById(String id);

    /**
     * Gets all the registers from the current table
     * @return A list of maps in which each map represents a register in the table. Returns null if no registers were found or an error has ocurred
     */
    List<Map<String, String>> getAllMapList() {
        String sql = String.format("SELECT * FROM %s;", tableName);
        List<Map<String, String>> elements = null;
        try {
            elements = ConnectionDB.executeQuery(sql);
        } catch (SQLException err) {
            System.out.println("Ocurrió un error en la consulta obteniendo todos los elementos: " + err.getMessage());
        }

        return elements;
    }

    /**
     * Gets all the registers of the table. The list's elements type is the type of the current model (user, trainer, etc)
     * @return a list with the registers that has been found
     */
    public abstract <T> List<T> getAll();

    public boolean createNew(String sql) {
        int rowsAffected = 0;
        try {
            rowsAffected = ConnectionDB.insertUpdateDelete(sql);
        } catch (SQLException err) {
            System.out.println("Ocurrió un error en la creacion del dato: " + err.getMessage());
        }
        return rowsAffected == 1;
    }

    /**
     * Deletes a register from the current table by its id or primary key
     * @param id the identifier of the register to delete
     * @return true if the register has been deleted, false if not
     */
    public boolean deleteById(String id) {
        int rowsAffected = 0;
        String sql = String.format("DELETE FROM %s WHERE (%s = '%s');", tableName, idName, id);
        try {
            rowsAffected = ConnectionDB.insertUpdateDelete(sql);
        } catch (SQLException err) {
            System.out.println("Ocurrió un error en la eliminación del dato con id " + id + ": " + err.getMessage());
        }
        return rowsAffected == 1;
    }

    /**
     * Makes an update in the current table, the sql sentence is needed
     * @param sql the sql sentence to be executed
     * @return true if rows has been affected (which might mean the update has been done), false if no rows has been affected
     */
    boolean Update(String sql) {
        int rowsAffected = 0;
        try {
            rowsAffected = ConnectionDB.insertUpdateDelete(sql);
        } catch (SQLException err) {
            System.out.println("Ocurrió un error en la creacion del dato: " + err.getMessage());
        }
        return rowsAffected == 1;
    }

    /**
     Converts a Map into an object of the current Model type
     * @param modelMap a map with he attributes of the model
     * @return an object of the model class
     */
    public abstract Model getModelFromMap(Map<String, String> modelMap);

    public String[] getColumnsNames() throws SQLException {
        return Utils.getColumnsNames(tableName);
    }
}
