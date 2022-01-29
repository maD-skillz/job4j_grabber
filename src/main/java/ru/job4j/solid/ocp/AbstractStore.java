package ru.job4j.solid.ocp;

public abstract class AbstractStore {
}

class Account extends AbstractStore {
    public void add() {

    }
}

class Role extends AbstractStore {
    public void add() {

    }
}

class Store {
    public void save(AbstractStore abstractStore) {
        Account account = new Account();
        Role role = new Role();
        if (abstractStore.equals(account)) {
            account.add();
        } else if (abstractStore.equals(role)) {
            role.add();
        }
    }
}
