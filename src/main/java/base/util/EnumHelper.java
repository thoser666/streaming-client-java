package base.util;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Annotation to mimic the NameAttribute in C#
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Name {
    String value();
}

public class EnumHelper {

    // Get the public-facing name of the specified enum from the Name Attribute
    public static <T extends Enum<T>> String getEnumName(T value) {
        if (value == null) {
            return null;
        }
        try {
            Field field = value.getDeclaringClass().getField(value.name());
            Name nameAttribute = field.getAnnotation(Name.class);
            if (nameAttribute != null) {
                return nameAttribute.value();
            }
            return value.name();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get the public-facing name of the specified enum from the Name Attribute
    public static String getEnumName(Class<?> type, Object value) {
        if (value == null || !type.isEnum()) {
            return null;
        }
        try {
            Field field = type.getField(value.toString());
            Name nameAttribute = field.getAnnotation(Name.class);
            if (nameAttribute != null) {
                return nameAttribute.value();
            }
            return value.toString();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get the public-facing names of the specified enums from the Name Attribute
    public static <T extends Enum<T>> List<String> getEnumNames(List<T> list) {
        List<String> results = new ArrayList<>();
        for (T value : list) {
            String name = getEnumName(value);
            if (name != null && !name.isEmpty()) {
                results.add(name);
            }
        }
        return results;
    }

    // Get the public-facing names of the specified enums from the Name Attribute
    public static <T extends Enum<T>> List<String> getEnumNames(Class<T> enumClass, boolean includeObsoletes) {
        return getEnumNames(getEnumList(enumClass, includeObsoletes));
    }

    // Get a list of all available values for an enum type
    public static <T extends Enum<T>> List<T> getEnumList(Class<T> enumClass, boolean includeObsoletes) {
        List<T> values = new ArrayList<>();
        for (T value : enumClass.getEnumConstants()) {
            if (!includeObsoletes && isObsolete(value)) {
                continue;
            }
            values.add(value);
        }
        return values;
    }

    // Get the enum value that matches the specified name
    public static Object getEnumValueFromString(Class<?> type, String str) {
        if (!type.isEnum() || str == null) {
            return null;
        }
        for (Object value : type.getEnumConstants()) {
            if (str.equalsIgnoreCase(getEnumName(type, value))) {
                return value;
            }
        }
        return null;
    }

//    // Get the enum value that matches the specified name
//    public static <T extends Enum<T>> T getEnumValueFromString(Class<T> enumClass, String str) {
//        for (T value : enumClass.getEnumConstants()) {
//            if (str.equalsIgnoreCase(getEnumName(value))) {
//                return value;
//            }
//        }
//        return null;
//    }

    // Indicates whether an enum value is obsolete
    public static <T extends Enum<T>> boolean isObsolete(T value) {
        try {
            Field field = value.getDeclaringClass().getField(value.name());
            Deprecated deprecated = field.getAnnotation(Deprecated.class);
            return deprecated != null;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }
}
