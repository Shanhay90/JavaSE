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
        if (object == null || isPrimitiveOrString(object.getClass())) {
            if (object==null) {
                builder.append("null");
            }else if (isCharacterOrString(object.getClass())){
                serializeCharAndString(object);
            }else {
                serializeSimpleField(object);
            }
        } else {
            builder.append("{");
            serializeObject(object);
            builder.append("}");
            this.result += builder.toString();
            return this;
        }
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
        return type.equals(Integer.class)
                || type.equals(String.class)
                || type.equals(Boolean.class)
                || type.equals(Character.class)
                || type.equals(Short.class)
                || type.equals(Long.class)
                || type.equals(Byte.class)
                || type.equals(Float.class)
                || type.equals(Double.class)
                || type.isPrimitive();
    }


    private boolean isCharacterOrString(Class type) {
        return type.equals(Character.class)
                || type.equals(String.class)
                || type.equals(char.class);
    }

    private void serializeCharAndString(Object obj) {
        builder.append("\"")
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
