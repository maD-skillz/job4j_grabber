package ru.job4j.design.ocp;

import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Report;
import ru.job4j.design.srp.Store;

import java.util.function.Predicate;


public class JsonBuilder implements Report {

    private Store store;

    public JsonBuilder(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
         var lib = new GsonBuilder().create();
             return lib.toJson(store.findBy(filter));

    }
}
