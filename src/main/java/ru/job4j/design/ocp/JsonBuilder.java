package ru.job4j.design.ocp;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;


public class JsonBuilder implements Report {

    private Store store;

    public JsonBuilder(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<User> filter) {
         var lib = new GsonBuilder().create();
         for (User u : store.findBy(filter)) {
             return lib.toJson(u);
             }
         return "";
    }
}
