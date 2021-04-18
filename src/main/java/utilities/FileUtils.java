package utilities;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import exceptions.CustomExceptions;
import models.Attractions;
import models.City;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    public static String readJson(String pathToFileFromResources) {
        try {
            return new String(Files.readAllBytes(Paths.get(Constants.pathToResources + pathToFileFromResources)));
        } catch (IOException e) {
            throw new CustomExceptions("Exception occurred during json read: " + e.getMessage());
        }
    }

    public static Object getObject(String response, TypeReference<?> c) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, c);
        } catch (IOException e) {
            throw new CustomExceptions("Exception occurred during object mapping: " + e.getMessage());
        }
    }

    public static List<City> getCities() {

        List<City> cityList = (List<City>)getObject(readJson("city.json"),new TypeReference<List<City>>() {});

        return cityList;
    }


    public static List<Attractions> getAttractions() {

        List<Attractions> attractionList = (List<Attractions>)getObject(readJson("attractions.json"),new TypeReference<List<Attractions>>() {});

        return attractionList;
    }

    public static List<Attractions> getAttractionsDesc() {

        List<Attractions> attractionList = (List<Attractions>)getObject(readJson("attractionsDesc.json"),new TypeReference<List<Attractions>>() {});

        return attractionList;
    }
}
