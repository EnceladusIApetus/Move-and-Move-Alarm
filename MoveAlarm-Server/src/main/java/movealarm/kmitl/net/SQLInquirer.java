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

    public boolean startConnection()
    {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connector =  DriverManager.getConnection("jdbc:mariadb://203.151.92.198/MoveAlarm" +
                    "?user=ice&password=7571179");
            if(connector != null){
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

    public HashMap<String,Object> find(int id,String table)
    {
        HashMap<String,Object> data = new HashMap<>();
        try {
            stmt = connector.createStatement();
            String sql = "SELECT * FROM " + table;
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rs_m = rs.getMetaData();
            while(rs != null) {
                rs.next();
                if(rs.getInt("id") == id) {
                    for(int col = 1;col <= rs_m.getColumnCount();col++) {
                        data.put(rs_m.getColumnName(col),rs.getObject(col));
                    }
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<HashMap<String, Object>> getCollection()
    {
        return collection;
    }
}
