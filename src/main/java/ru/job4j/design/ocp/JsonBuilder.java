package ru.job4j.design.ocp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;


public class JsonBuilder implements Report {

    private Store store;

    public JsonBuilder(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<User> filter) {
         Gson gson = new GsonBuilder().create();
         StringBuilder userJson = new StringBuilder();
         for (User u : store.findBy(filter)) {
           userJson.append("{")
                   .append("\"")
                   .append(u.getName())
                   .append("\"")
                   .append(": ")
                   .append("\"")
                   .append(u.getLastName())
                   .append("\"")
                   .append("}");
         }
         return gson.fromJson(userJson.toString(), User.class).toString();
    }
}
