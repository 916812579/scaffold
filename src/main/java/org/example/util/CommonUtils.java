package org.example.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static List<Long> stringToLong(String str) {
        if (StringUtils.isNotBlank(str)) {
            String[] strings = str.split(",");
            List<Long> result = new ArrayList<>();
            for (String string : strings) {
                result.add(Long.valueOf(string));
            }
            return result;
        }
        return null;
    }
}
