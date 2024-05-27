package Commons;

import ConnectionDB.ConnectionDB;
import Models.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commons {
    public static boolean validatePass(String pass) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=])(?=.{8,})[a-zA-Z0-9!@#$%^&*()-_+=]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public static Integer getIntOrNull(String s) {
        return s != null ? Integer.parseInt(s) : null;
    }

    public static Float getFloatOrNull(String s) {
        return s != null ? Float.parseFloat(s) : null;
    }

    public static Short getShortOrNull(String s) {
        return s != null ? Short.parseShort(s) : null;
    }

    public static Boolean getBoolOrNull(String s) {
        if (s != null && (s.equalsIgnoreCase("t") || s.equalsIgnoreCase("true")))
            return true;
        return s != null ? Boolean.parseBoolean(s) : null;
    }

    public static Timestamp getTimestampOrNull(String s) {
        return s != null ? Timestamp.valueOf(s) : null;
    }
    public static Date getDateOrNull(String s) {
        return s != null ? Date.valueOf(s) : null;
    }

    public static <T> List<T> modelMapListToModelList(List<Map<String, String>> modelMapList, Model model) {
        if (modelMapList == null)
            return null;
        else if (model == null) {
            System.out.println("El model para hacer la conversion mapListToModelList no es válido, su valor es null");
            return null;
        }

        List<T> modelList = new ArrayList<>();

        for (Map<String, String> modelMap : modelMapList)
            modelList.add((T) model.getModelFromMap(modelMap));

        return modelList;
    }
}
