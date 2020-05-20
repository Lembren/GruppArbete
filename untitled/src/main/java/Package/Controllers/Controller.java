package Package.Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class Controller {

    @RequestMapping("/")
    public String index() throws MalformedURLException {
        String temperature = "",timestamp = "" ;
        URL u = new URL("https://mdna.azurewebsites.net/api/GetDataFromTableStorage?limitRows=1&orderBy=desc");
        try (InputStream in = u.openStream()) {
            Object obj = new String(in.readAllBytes());
            String strObj = obj.toString();
            JSONArray jsonArr = new JSONArray(strObj);


            for (int i = 0; i < jsonArr.length(); ++i) {
                JSONObject rec = jsonArr.getJSONObject(i);
                 temperature = rec.optString("temperature");
                 timestamp = rec.optString("timestamp");

            }
            return "Temperature: "+ temperature + " Timestamp: "+timestamp;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
