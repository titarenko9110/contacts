package titarenko.test2.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titarenko.test2.repo.SportRepo;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by MyMac on 15.01.17.
 */

@Service
public class MainService {

    @Autowired
    private SportRepo sportRepo;

    public static final String liveOrPre = "lv";

    public static final String syte = "allbestbets";

    public static int count = 0;
    private static int countRequest = 0;

    private static final List<String> allTokens = Arrays.asList("b4d0431c751e1ae21bf5ad40f8f4c00a","0c30688d46666c663e363def40bd43d7","dd5056ae0e8a2542d7213cd8167c8a40");
    private static String currentToken;
    private static int tokenIndex = 0;

    public static String getToken() {
        int tokensCount = allTokens.size();
        if (countRequest >= 500) {
            tokenIndex++;
            countRequest = 0;
            if (tokenIndex >= tokensCount) {
                tokenIndex = 0;
            }
            currentToken = allTokens.get(tokenIndex);
        }
        if (currentToken == null) {
            currentToken = allTokens.get(tokenIndex);
        }
        countRequest++;
        count ++;
        System.out.println(count);
        System.out.println(countRequest);
        return currentToken;
    }

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public JSONObject getAllData() throws Exception {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api-"+MainService.liveOrPre+".allbestbets.ru/api/v1/directories?access_token=" + getToken())
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
        }
        if (response != null && response.getBody() != null) {
            String body = response.getBody();
            return new JSONObject(body);
        }
        return null;
    }
}
