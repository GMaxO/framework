package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class FilesUtil {

    public static JsonObject getJsonObjectFromFile(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonObject jsonArray = gson.fromJson(reader, JsonObject.class);
        return jsonArray;
    }
}
