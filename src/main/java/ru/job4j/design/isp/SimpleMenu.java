package ru.job4j.design.isp;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    private final List<MenuItemInfo> menuList = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        if (findItem(parentName).isEmpty()) {
            result = menuList.add(new MenuItemInfo(findItem(parentName).get().menuItem.getName(),
                    List.of(findItem(childName).get().menuItem.getName()), actionDelegate,
                    findItem(parentName).get().number));
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> result = Optional.empty();
        if (findItem(itemName).isPresent()) {
           result = Optional.of(new MenuItemInfo(findItem(itemName).get().menuItem, findItem(itemName).get().number));
        }
        return result;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return iterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator dfsIt = new DFSIterator();
        Optional<ItemInfo> res = Optional.empty();
        while (dfsIt.hasNext()) {
            if (dfsIt.next().menuItem.getName().equals(name)) {
                res = Optional.of(new ItemInfo(dfsIt.next().menuItem, dfsIt.next().number));
            }
        }
        return res;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}
