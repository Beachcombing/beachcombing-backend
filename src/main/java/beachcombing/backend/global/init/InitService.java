package beachcombing.backend.global.init;

import beachcombing.backend.domain.common.domain.Location;
import beachcombing.backend.domain.trashcan.domain.Trashcan;
import beachcombing.backend.domain.trashcan.repository.TrashcanRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class InitService {

    private final TrashcanRepository trashcanRepository;
    private final GeocodingHelper geocodingHelper;

    @Value("${DB_API_KEY}")
    private String serviceKey;

    public void initDatabase() {
        // TODO: 해변 정보 추가시 사용
    }

    public ResponseEntity<Void> saveTrashcanFromApi() {
        String page = "1";
        String perPage = "80";

        String urlStr = "https://api.odcloud.kr/api/15089095/v1/uddi:c03ea19d-e9b0-4cfe-9714-96a9bb363db6?" +
                "page=" + page +
                "&perPage=" + perPage+
                "&serviceKey="+serviceKey;
        StringBuffer result = new StringBuffer();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result.append(bf.readLine());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");

            for(int i=0; i<jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                String address = (String) object.get("설치장소");
                Map<String, String> coords = geocodingHelper.getGeoDataByAddress(address);

                Location location = Location.builder()
                        .lat(new BigDecimal(coords.get("lat")))
                        .lng(new BigDecimal(coords.get("lng")))
                        .build();
                Trashcan trashcan = Trashcan.createTrashcanByApi(location);
                trashcanRepository.save(trashcan);
            }

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}