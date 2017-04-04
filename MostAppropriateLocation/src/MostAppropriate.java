import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Location {
    String zip_code = "";
    double lat = 0.0;
    double lng = 0.0;
    String city = "";
    String state = "";
}

class Distance {
    public double distance = 0.0;
}

public class MostAppropriate {
    static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        BufferedReader locationFile = new BufferedReader(new FileReader("Copart_Locations.csv"));
        long[] copartLocations = {99501, 36613, 35023, 36116, 35671, 79601, 97218};//, 72032, 72753, 85043, 85706, 93307, 92324, 93725, 94545, 90001, 94553, 91739, 95828, 95828, 92154, 95046, 94950, 91405, 80603, 80907, 80229, 19973, 34946, 33032, 32220, 32209, 33167, 33054, 32343, 34482, 32822, 33982, 33578, 33411, 30168, 30120, 30294, 30507, 30052, 31405, 31794, 96707, 50317, 52748, 52748, 83651, 60411, 60120, 61554, 60090, 46320, 47348, 46254, 66111, 67216, 40342, 40509, 40272, 41094, 70739, 70129, 71109, 21048, 20602, 48243, 49788, 48917, 48875, 48183, 56310, 55011, 55304, 55421, 63044, 65201, 65742, 63801, 39073, 59101, 59601, 28023, 28334, 27302, 68366, 87105, 89115, 89506, 12205, 11719, 13036, 14482, 12542, 44028, 43207, 45439, 44067, 73129, 74107, 97402, 97218, 97071, 15611, 17201, 18642, 15931, 16117, 17028, 18073, 18914, 15122, 17370, 29053, 29651, 37090, 37354, 38118, 79601, 79118, 79821, 78403, 75051, 76052, 77073, 75603, 75904, 78570, 78130, 78224, 76501, 75172, 84045, 24531, 23666, 23150, 99001, 98223, 98338, 99301, 53110, 53718, 25526};

        BufferedReader br = new BufferedReader(new FileReader("MOSTAPPROPRIATEInput.txt"));
        String zipcode = "";
        String clientKey = "GVx5feMfyxhFKUlgssdm7Bc0V8dbNjjtfQzu5lKES1jDVEUo78LfJXfF5tRTNan2";
        Gson gson = new Gson();

        System.out.println("Most appropriate Copart Facility applicable for the customer");
        while ((zipcode = br.readLine()) != null) {
            double minDistance = Double.MAX_VALUE;
            long minLocation = 0;
            double distance = 0.0;
            Location location = new Location();
            String custId = br.readLine();
            String result = "";
            for (long copart : copartLocations) {
                Distance dist = new Distance();
                String url = "https://www.zipcodeapi.com/rest/" + clientKey + "/distance.json/" + zipcode + "/" + copart + "/km";
                result = run(url);
                dist = gson.fromJson(result, Distance.class);
                distance = dist.distance;
                if (distance < minDistance) {
                    minDistance = distance;
                    minLocation = copart;
                }

            }
            
            String infoUrl = "https://www.zipcodeapi.com/rest/" + clientKey + "/info.json/" + minLocation;
            result = run(infoUrl);
            location = gson.fromJson(result, Location.class);

            System.out.println("\t\t\t For customer " + custId + " with a provided zipcode of " + zipcode + ", closest Copart Facility is " + location.city + ", " + location.state);
        }
    }

    public static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
