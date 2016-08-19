/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbadmintool;

import java.sql.*;
//import java.math.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Dave
 */
public class SQLConnector {

    //private String Host;
    private String server;
    private String database;
    private int port;
    private String username;
    private String password;
    private String provider;
    private Connection connection;
    private boolean bIsConnected = false;
    private ResultSet Results;
    private boolean bIsValidQuery = false;
    /**
     * protected constructor. Cannot be instantiated
     */
    protected SQLConnector() {
        server = "";
        database = "";
        port = 3306;
        username = "";
        password = "";
        provider = "";
        bIsConnected= false;
        bIsValidQuery = false;
    }
    /**
     * password to set
     * @param tPass password to set
     */
    public void setPassword(String tPass) {
        password = tPass;
    }
    /**
     * Username to set
     * @param tUser Username to set
     */
    public void setUsername(String tUser) {
        username = tUser;
    }
    /**
     * Server to set
     * @param tServ server to set
     */
    public void setServer(String tServ) {
        server = tServ;
    }
    /**
     * Database to set
     * @param tDB Database to set
     */
    public void setDatabase(String tDB) {
        database = tDB;
    }
    /**
     * get set database name
     */
    protected String getDatabase(){
        return database;
    }
    /**
     * set provider
     * @param tProv provider to set
     */
    protected void setProvider(String tProv) {
        provider = tProv;
    }
    /**
     * connects to the database
     * @return true if successful
     */
    public boolean connect() {

        bIsValidQuery = false;
        //Check username
        if ("".equals(username)) {
            String[] arr = {"Username cannot be empty"};
            Message.main(arr);
            return false;
        }
        //check password
        if ("".equals(password)) {
            String[] arr = {"Password cannot be empty"};
            Message.main(arr);
            return false;
        }
        //check server
        if ("".equals(server)) {
            String[] arr = {"Server cannot be empty"};
            Message.main(arr);
            return false;
        }
        //check provider
        if ("".equals(provider)) {
            String[] arr = {"System Error: Provider cannot be empty"};
            Message.main(arr);
            return false;
        }
        //build host string
        String Host = "jdbc:" + provider + "://" + server + ":" + String.valueOf(port);
        if ("".equals(database) == false) {
            Host += "/" + database;
        }
        try {
            connection = DriverManager.getConnection(Host, username, password);
            bIsConnected = true;
            return true;
        } catch (SQLException e) {
            bIsConnected = false;
            String[] arr = {"SQL Error :" + e.getMessage()};
            Message.main(arr);
            return false;
        }
    }

    /**
     * Returns a list of elements from a simple query. Only works on the first column of the query. Also sets up query
     * @param query Query to be executed
     * @return list of elements
     */
    public List<String> queryToStringArray(String query) {
        List<String> formattedResults = new ArrayList<>();
        if (setupQuery(query)) {
            return queryToStringArray();
        }
        return formattedResults;
    }
    /**
     * Returns a list of elements from a simple query that is already set up. Only works on the first column of the query
     * @return list of elements
     */
    public List<String> queryToStringArray() {
        List<String> results = new ArrayList<>();
        if (firstElmnt()) {
            do {
                results.add(resultAsString(1));
            } while (nextElmnt());
        }
        return results;
    }

    /**
     * moves the cursor for the record set to the first item
     * @return true if successful
     */
    protected boolean firstElmnt() {
        boolean IsPresent = false;
        try {
            IsPresent = Results.first();
        } catch (SQLException ex) {
            String[] arr = {"Query error #3 : " + ex.getMessage()};
            Message.main(arr);
            return IsPresent;
        }
        return IsPresent;
    }

    /**
     * moves the cursor of the result set to the next row, returns true if successful
     * @return true if still in record set
     */
    protected boolean nextElmnt() {
        boolean IsPresent = false;
        try {
            IsPresent = Results.next();
        } catch (SQLException ex) {
            String[] arr = {"Query error #4 : " + ex.getMessage()};
            Message.main(arr);
            return IsPresent;
        }
        return IsPresent;
    }
    /**
     * returns a value of the current row in the result set, as a string, at the column identified by the column attribute
     * @param column column number
     * @return the value, as a string, of the column
     */
    protected String resultAsString(int column) {
        String result = "";
        if (bIsValidQuery) {
            try {
                result = Results.getString(column);
            } catch (SQLException ex) {
                String[] arr = {"Data retrieve error #1 : " + ex.getMessage()};
                Message.main(arr);
            }
        }
        return result;
    }
    /**
     * 
     * @return types of columns
     */
    public List<String> GetColumnTypes() {
        List<String> ColumnTypes = new ArrayList<>();
        if (!bIsValidQuery) {
            return ColumnTypes;
        }
        try {
            ResultSetMetaData Columns = Results.getMetaData();
            int ColumnCount = Columns.getColumnCount();
            for (int pos = 1; pos <= ColumnCount; pos++) {
                ColumnTypes.add(Columns.getColumnClassName(pos));
            }
        } catch (SQLException ex) {
            String[] arr = {"Column name error : " + ex.getMessage()};
            Message.main(arr);
        }
        return ColumnTypes;

    }
    /**
     * return a list of the column names if the query has been set up
     * @return list of column names
     */
    public List<String> GetColumnNames() {
        List<String> ColumnNames = new ArrayList<>();
        if (!bIsValidQuery) {
            return ColumnNames;
        }
        try {
            ResultSetMetaData Columns = Results.getMetaData();
            int ColumnCount = Columns.getColumnCount();
            for (int pos = 1; pos <= ColumnCount; pos++) {
                ColumnNames.add(Columns.getColumnName(pos));
            }
        } catch (SQLException ex) {
            String[] arr = {"Column name error : " + ex.getMessage()};
            Message.main(arr);
        }
        return ColumnNames;

    }
    /**
     * function to set up a query in the class
     * @param Query Query to be executed
     * @return true if query is successful
     */
    public boolean setupQuery(String Query) {
        bIsValidQuery = false;
        if (bIsConnected) {
            try {
                Statement stmt = connection.createStatement();
                Results = stmt.executeQuery(Query);
                bIsValidQuery = true;

            } catch (SQLException e) {
                String[] arr = {"Query error : " + e.getMessage()};
                Message.main(arr);
            }
        } else {
            String[] arr = {"Not Connected"};
            Message.main(arr);
        }

        return bIsValidQuery;
    }
    protected ResultSet getQueryResults(){
        return Results;
    }
    /**
     * simple function to determine if the class is connected
     * @return true if connected
     */
    public boolean IsConnected() {
        return bIsConnected;
    }

}
