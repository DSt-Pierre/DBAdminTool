/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbadmintool;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author Dave
 */
public class mySQLConnector extends SQLInterface {
    /**
     * 
     * @return A list of database names
     */
    @Override
    public List<String> getDatabases() {
        List<String> results;
        if ("".equals(getDatabase())){
        results = queryToStringArray("SHOW DATABASES;");
        }
        else{
          results = new ArrayList<>();  
          results.add(getDatabase());
        }
        return results;
    }
    /**
     * 
     * @param Database Name of the database for which we want the tables
     * @return a list of tables
     */
    @Override
    public List<String> getTables(String Database) {
        List<String> results;
        results = queryToStringArray("SHOW TABLES IN "+Database+";");
        return results;
    }
    /**
     * 
     * @return true if connection is successful
     */
    @Override
    public boolean connect() {
       setProvider("mySQL");
        return super.connect();
    }
    /**
     * 
     * @param query the query to execute
     * @return the dataset
     */
    @Override
    protected ResultSet queryDatabase(String query){
        setupQuery(query);
        return getQueryResults();
    }
    @Override
    public ResultSet getTableData(String table, String database){
        return queryDatabase("SELECT * FROM "+database+"."+table+" LIMIT 100;");
    }
}
