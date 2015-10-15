package movealarm.kmitl.net;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by Moobi on 15-Oct-15.
 */
@RestController
public class ConnectionTesting {

    @RequestMapping("/testConnection")
    public String testConnection()
    {
        return "Connection success!";
    }

    @RequestMapping("/testPassParameter")
    public String testPassParameter(@RequestParam(value="param", required = true, defaultValue = "Server cannot recieve any value." +
            "Please check your sending process.") String param)
    {
        return "Your pass value: " + param;
    }

    @RequestMapping("connectToDB")
    public boolean testConDB() {
        SQLInquirer sql = new SQLInquirer();
        sql.startConnection();
        return sql.isConnecting();
    }

    @RequestMapping("/find")
    public String testFind(@RequestParam(value="id") int id,@RequestParam(value = "table") String table) {
        SQLInquirer sql = new SQLInquirer();
        sql.startConnection();
        HashMap<String,Object> data = sql.find(id,table);
        return data.toString();
    }
}
