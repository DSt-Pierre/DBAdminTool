/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbadmintool;
import java.sql.ResultSet;
/**
 *
 * @author Dave
 */
import java.util.List;
//interface to implement for usage by the interface
public abstract class SQLInterface extends SQLConnector {
   /**
    * returns a list of databases
    * @return list of databases
    */
   public abstract List<String> getDatabases();
   /**
    * returns a list of tables from the selected database
    * @param Database Database to check for tables
    * @return List of tabes from the database
   */
   public abstract List<String> getTables(String Database);
   /**
    * 
    * @param Query the query to execute
    * @return the resultset
    */
   protected abstract ResultSet queryDatabase(String Query);
   
   public abstract ResultSet getTableData(String table,String Database);
}
