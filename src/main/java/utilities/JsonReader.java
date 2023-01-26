package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReader {

    public static JsonObject getJsonObjectFromFile(String path) {
        Gson gson = new Gson();
        com.google.gson.stream.JsonReader reader = null;
        try {
            reader = new com.google.gson.stream.JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonObject jsonArray = gson.fromJson(reader, JsonObject.class);
        return jsonArray;
    }
}
