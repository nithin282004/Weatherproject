package K22UG;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class weather {
    public static void main(String[] args) {
        // Replace with your OpenWeatherMap API key
        String apiKey = "170e7e023d758159189cf7f6f8f6ea8d";
        
        // Get user input for the location
        String location = getUserInput();
        
        try {
            // Construct the API URL
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey;

            // Create an HttpURLConnection and set request properties
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response from the API
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Read the response data
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response (You can use a library like Gson for better parsing)
                String responseData = response.toString();

                // Display the weather data to the user
                System.out.println("Weather information for " + location + ":");
                System.out.println(responseData);
            } else {
                System.out.println("Error: Unable to fetch weather data. Response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
        	
        }
    }

    private static String getUserInput() {
        System.out.print("Enter the location (e.g., city name): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

