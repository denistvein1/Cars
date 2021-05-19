package Repository;

import Domain.Car;

import java.util.ArrayList;

public class Handler {

    private static final ArrayList<String> commandsUndo = new ArrayList<>();
    private static final ArrayList<String> commandsRedo = new ArrayList<>();
    private static final ArrayList<Car> carsUndo = new ArrayList<>();
    private static final ArrayList<Car> carsRedo = new ArrayList<>();

    public static void addHandler(Car car, boolean undo) {
        if (undo) {
            commandsUndo.add("delete");
            carsUndo.add(car);
        } else {
            commandsRedo.add("delete");
            carsRedo.add(car);
        }
    }

    public static void deleteHandler(Car car, boolean undo) {
        if (undo) {
            commandsUndo.add("add");
            carsUndo.add(car);
        } else {
            commandsRedo.add("add");
            carsRedo.add(car);
        }
    }

    public static void updateHandler(Car car, boolean undo) {
        if (undo) {
            commandsUndo.add("update");
            carsUndo.add(car);
        } else {
            commandsRedo.add("update");
            carsRedo.add(car);
        }
    }

    public static String getLastUndoCommand() {
        return commandsUndo.get(commandsUndo.size() - 1);
    }

    public static String getLastRedoCommand() {
        return commandsRedo.get(commandsRedo.size() - 1);
    }

    public static Car getLastUndoCar() {
        return carsUndo.get(carsUndo.size() - 1);
    }

    public static Car getLastRedoCar() {
        return carsRedo.get(carsRedo.size() - 1);
    }

    public static void executeUndo(ArrayList<Car> elements) throws IndexOutOfBoundsException {
        if (commandsUndo.size() <= 0) {
            throw new IndexOutOfBoundsException();
        }

        String command = getLastUndoCommand();
        Car car = getLastUndoCar();

        switch (command) {
            case "add" -> elements.add(car);
            case "delete" -> elements.remove(car);
            case "update" -> elements.get(elements.indexOf(car)).set(car);
        }

        commandsUndo.remove(commandsUndo.size() - 1);
        carsUndo.remove(carsUndo.size() - 1);
    }

    public static void clearRedo() {
        commandsRedo.clear();
        carsRedo.clear();
    }

    public static void executeRedo(ArrayList<Car> elements) throws IndexOutOfBoundsException {
        if (commandsRedo.size() <= 0) {
            throw new IndexOutOfBoundsException();
        }

        String command = getLastRedoCommand();
        Car car = getLastRedoCar();

        switch (command) {
            case "add" -> elements.add(car);
            case "delete" -> elements.remove(car);
            case "update" -> elements.get(elements.indexOf(car)).set(car);
        }

        commandsRedo.remove(commandsRedo.size() - 1);
        carsRedo.remove(carsRedo.size() - 1);
    }
}
