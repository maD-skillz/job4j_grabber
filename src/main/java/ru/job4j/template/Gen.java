package ru.job4j.template;


import java.util.HashMap;
import java.util.Map;

public class Gen implements Generator {

    public String finalString(String template) {
        return "";
    }


    public Map<String, String> getKeyAndVal(Map<String, String> map) {
        return new HashMap<>();
    }

    public Map<String, String> trimTemplate(String template) {
        return new HashMap<>();
    }

    public void checkKeyAndVal(Map<String, String> map) {

    }

    @Override
       public String produce(String template, Map<String, String> args) {
        return "";
    }
}
