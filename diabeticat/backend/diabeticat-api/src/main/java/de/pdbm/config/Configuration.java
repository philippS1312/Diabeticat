package de.pdbm.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class holds string representations of URI prefixes from microservices store, customer, film and ui.
 */
public class Configuration {
    public static final String DVD_RENTAL_STORE = read("storepath");
    public static final String DVD_RENTAL_CUSTOMER = read("customerpath");
    public static final String DVD_RENTAL_FILM = read("filmpath");
    public static final String DVD_RENTAL_UI = read("uipath");

    /**
     * Method to get the string representation of URI prefixes from microservice according to identifier.
     * @param identifier storepath, customerpath, filmpath or uipath.
     * @return String representation of URI prefix.
     */
    private static String read(String identifier){
        InputStream is = Configuration.class.getClassLoader().getResourceAsStream("paths");
        String result = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining("\n"));
        Pattern pattern = Pattern.compile("(?<="+identifier+"=).*(?=\n)");
        Matcher matcher = pattern.matcher(result);

        return matcher.find()?matcher.group():"";
    }
}
