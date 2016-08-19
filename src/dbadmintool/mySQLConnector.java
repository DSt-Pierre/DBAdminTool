/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbadmintool;

import java.util.List;

/**
 *
 * @author Dave
 */
public class mySQLConnector extends SQLInterface {

    public List<String> getDatabases() {
        List<String> results;
        results = queryToStringArray("SHOW DATABASES;");
        return results;
    }

    public List<String> getTables(String Database) {
        List<String> results;
        results = queryToStringArray("SHOW TABLES IN "+Database+";");
        return results;
    }

    public boolean connect() {
       setProvider("mySQL");
        return super.connect();
    }
}
