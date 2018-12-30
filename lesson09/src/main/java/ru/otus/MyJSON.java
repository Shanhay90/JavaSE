package ru.otus;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyJSON {

    private String result;
    private StringBuilder builder;

    public MyJSON() {
        this.result = "";
        this.builder = new StringBuilder();
    }

    public MyJSON toJSON(Object object) throws IllegalAccessException {
        builder.append("{");
        serializeObject(object);
        builder.append("}");
        this.result += builder.toString();
        return this;
    }


    private void serializeObject(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        List<Field> fields = Stream.of(clazz.getDeclaredFields())
                .filter(field -> !Modifier.isTransient(field.getModifiers()))
                .collect(Collectors.toList());

        for (Field tempField : fields) {
            tempField.setAccessible(true);
            Class fieldType = tempField.getType();

            builder.append("\"")
                    .append(tempField.getName())
                    .append("\"")
                    .append(":");

            if (tempField.get(object) == null) {
                builder.append(" null, ");
            } else {
                if (!isPrimitiveOrString(fieldType)) {
                    if (Collection.class.isAssignableFrom(fieldType)) {
                        Collection collectionInField = (Collection) tempField.get(object);
                        builder.append("[");
                        for (Object objectInCollection : collectionInField) {
                            serializeInArrayOrCollection(objectInCollection);
                        }
                        builder.deleteCharAt(builder.lastIndexOf(","));
                        builder.append("]");
                    } else if (fieldType.isArray()) {
                        builder.append("[");
                        for (int x = 0; x < Array.getLength(tempField.get(object)); x++) {
                            Object objectInArray = Array.get(tempField.get(object), x);
                            serializeInArrayOrCollection(objectInArray);
                        }
                        builder.deleteCharAt(builder.lastIndexOf(","));
                        builder.append("]");
                    } else {
                        builder.append("{");
                        serializeObject(tempField.get(object));
                        builder.append("}");
                    }
                } else {
                    if (isCharacterOrString(fieldType)) {
                        serializeCharAndString(tempField.get(object));
                    } else {
                        serializeSimpleField(tempField.get(object));
                    }
                }
                builder.append(",");
            }
        }
        builder.deleteCharAt(builder.lastIndexOf(","));

    }

    private void serializeInArrayOrCollection(Object objectInArray) throws IllegalAccessException {
        if (objectInArray == null) {
            builder.append("null");
        } else {

            if (isPrimitiveOrString(objectInArray.getClass())) {
                if (isCharacterOrString(objectInArray.getClass())) {
                    serializeCharAndString(objectInArray);
                } else {
                    serializeSimpleField(objectInArray);
                }
            } else {
                builder.append("{");
                serializeObject(objectInArray);
                builder.append("}");
            }
        }
        builder.append(",");
    }


    private boolean isPrimitiveOrString(Class type) {
        String className = type.getSimpleName();
        return className.equals("Integer")
                || className.equals("String")
                || className.equals("Boolean")
                || className.equals("Character")
                || className.equals("Short")
                || className.equals("Long")
                || className.equals("Byte")
                || className.equals("Float")
                || className.equals("Double")
                || type.isPrimitive();
    }


    private boolean isCharacterOrString(Class type) {
        return type.getSimpleName().equals("Character")
                || type.getSimpleName().equals("String")
                || type.getSimpleName().equals("char") ;
    }

    private void serializeCharAndString(Object obj) {
        builder
                .append("\"")
                .append(obj)
                .append("\"");
    }


    private void serializeSimpleField(Object obj) {
        builder.append(obj);
    }

    @Override
    public String toString() {
        return result;
    }
}
