package Repository;

import Domain.Car;

import java.util.ArrayList;
import java.util.NoSuchElementException;

//ELIMINATE COMMENTED LINES TO USE UNDO/REDO FUNCTIONALITIES THAT USE COPIES OF THE ATTRIBUTE "elements"
public class Repository {

    private final ArrayList<Car> elements;
    //private final ArrayList<ArrayList<Car>> undoHistory;
    //private final ArrayList<ArrayList<Car>> redoHistory;

    public Repository() {
        elements = new ArrayList<>();
        //undoHistory = new ArrayList<>();
        //redoHistory = new ArrayList<>();
    }

    public void add(Car car) {
        Handler.addHandler(car, true);
/*        ArrayList<Car> elementsCopy = new ArrayList<>(elements);
        undoHistory.add(elementsCopy);*/
        if (elements.contains(car)) {
            int carIndex = elements.indexOf(car);
            int newQuantity = elements.get(carIndex).getQuantity() + car.getQuantity();
            elements.get(carIndex).setQuantity(newQuantity);
        } else {
            elements.add(car);
        }
        //redoHistory.clear();
        Handler.clearRedo();
    }

    public void delete(Car car) throws NoSuchElementException {
        if (elements.contains(car)) {
            Handler.deleteHandler(elements.get(elements.indexOf(car)), true);
/*            ArrayList<Car> elementsCopy = new ArrayList<>(elements);
            undoHistory.add(elementsCopy);*/
            elements.remove(car);
            //redoHistory.clear();
            Handler.clearRedo();
        } else {
            throw new NoSuchElementException();
        }
    }

    public void update(Car car) throws NoSuchElementException {
        if (elements.contains(car)) {
            Car temp = new Car(elements.get(elements.indexOf(car)));
            Handler.updateHandler(temp, true);
/*            ArrayList<Car> elementsCopy = new ArrayList<>(elements);
            undoHistory.add(elementsCopy);*/
            elements.get(elements.indexOf(car)).set(car);
            //redoHistory.clear();
            Handler.clearRedo();
        } else {
            throw new NoSuchElementException();
        }
    }

    /*
     * Undoes the previous operation and returns the program
     * to the previous state
     * @throws IndexOutOfBoundsException if there is nothing
     * to undo */
    public void undo() throws IndexOutOfBoundsException {
        Car temp = new Car(Handler.getLastUndoCar());
        switch (Handler.getLastUndoCommand()) {
            case "add" -> Handler.addHandler(temp, false);
            case "delete" -> Handler.deleteHandler(temp, false);
            case "update" -> Handler.updateHandler(temp, false);
        }
        Handler.executeUndo(elements);
/*        if (undoHistory.size() > 0) {
            redoHistory.add(elements);
            elements = undoHistory.get(undoHistory.size() - 1);
            undoHistory.remove(undoHistory.size() - 1);
        } else {
            throw new IndexOutOfBoundsException();
        }*/

    }

    /*
     * Redoes the previous operation and returns the program
     * to the previous state
     * @throws IndexOutOfBoundsException if there is nothing
     * to redo */
    public void redo() throws IndexOutOfBoundsException {
        Car temp = new Car(Handler.getLastRedoCar());
        switch (Handler.getLastRedoCommand()) {
            case "add" -> Handler.addHandler(temp, true);
            case "delete" -> Handler.deleteHandler(temp, true);
            case "update" -> Handler.updateHandler(temp, true);
        }
        Handler.executeRedo(elements);
/*        if (redoHistory.size() > 0) {
            undoHistory.add(elements);
            elements = redoHistory.get(redoHistory.size() - 1);
            redoHistory.remove(redoHistory.size() - 1);
        } else {
            throw new IndexOutOfBoundsException();
        }*/
    }

    public ArrayList<Car> getElements() {
        return elements;
    }
}
