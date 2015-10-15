package movealarm.kmitl.net;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLInquirer {

    private Connection connector;
    private Statement stmt;
    private ResultSet rs;
    private ArrayList<HashMap<String, Object>> collection;
    private boolean connectionStatus = false;

    public SQLInquirer()
    {
        connectionStatus = startConnection();
        collection = new ArrayList<HashMap<String, Object>>();
    }

    public ArrayList<HashMap<String, Object>> where(String tableName, String colName, String operator, String value) throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE " + colName + " " + operator + " '" + value + "'");
        ResultSetMetaData metaData = rs.getMetaData();
        while(rs.next()) {
            HashMap<String, Object> temp = new HashMap<String, Object>();
            for(int i = 1; i <= metaData.getColumnCount(); i++)
                temp.put(metaData.getColumnName(i), rs.getObject(i));
            collection.add(temp);
        }
        return collection;
    }

    public ArrayList<HashMap<String, Object>> where(String sqlCommand) throws SQLException {
        rs = stmt.executeQuery(sqlCommand);
        ResultSetMetaData metaData = rs.getMetaData();
        while(rs.next()) {
            HashMap<String, Object> temp = new HashMap<String, Object>();
            for(int i = 1; i <= metaData.getColumnCount(); i++)
                temp.put(metaData.getColumnName(i), rs.getObject(i));
            collection.add(temp);
        }
        return collection;
    }

    public String testQuery()  {
        String word = "test";
        try {
            if(isConnecting()) {
                rs = stmt.executeQuery("SELECT * FROM testTable");
                rs.next();
                word = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return word;
    }

    public boolean startConnection()
    {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connector =  DriverManager.getConnection("jdbc:mariadb://203.151.92.198/MoveAlarm" +
                    "?user=oat&password=123454322");
            if(connector != null){
                stmt = connector.createStatement();
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean isConnecting()
    {
        return connectionStatus;
    }

    public void closeConnection()
    {
        try {
            connector.close();
            stmt.close();
            rs.close();
            connectionStatus = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, Object>> getCollection()
    {
        return collection;
    }
}
