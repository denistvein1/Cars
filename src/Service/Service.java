package Service;

import Domain.Car;
import Domain.Validator;
import Repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class Service {

    private final Repository repository;

    public Service() {
        repository = new Repository();
    }

    public void add(String company, String model, int hp, int quantity, int price) throws IllegalArgumentException {
        Validator.validate(company, model, hp, quantity, price);
        Car car = new Car(company, model, hp, quantity, price);
        repository.add(car);
    }

    public void delete(String company, String model) throws IllegalArgumentException, NoSuchElementException {
        Validator.validateShort(company, model);
        Car car = new Car(company, model);
        repository.delete(car);
    }

    public void update(String company, String model, int newHp, int newQuantity, int newPrice) throws IllegalArgumentException, NoSuchElementException {
        Validator.validate(company, model, newHp, newQuantity, newPrice);
        Car car = new Car(company, model, newHp, newQuantity, newPrice);
        repository.update(car);
    }

    public ArrayList<Car> getElements() {
        return repository.getElements();
    }

    public ArrayList<Car> getElementsGraterHp(String hpString) {
        if (hpString.isEmpty()) {
            return getElements();
        } else {
            int hpInt = Integer.parseInt(hpString);
            return (ArrayList<Car>) getElements().stream().filter(car -> car.getHp() > hpInt).collect(Collectors.toList());
        }
    }

    public void sortByQuantity(ArrayList<Car> cars) {
        Collections.sort(cars);
    }

    public ArrayList<Car> getElementsLessQuantity(String quantityString) {
        if (quantityString.isEmpty()) {
            return getElements();
        } else {
            int quantityInt = Integer.parseInt(quantityString);
            return (ArrayList<Car>) getElements().stream().filter(car -> car.getQuantity() < quantityInt).collect(Collectors.toList());
        }
    }

    public void undo() throws UnsupportedOperationException {
        try {
            repository.undo();
        } catch (IndexOutOfBoundsException e) {
            throw new UnsupportedOperationException();
        }
    }

    public void redo() throws UnsupportedOperationException {
        try {
            repository.redo();
        } catch (IndexOutOfBoundsException e) {
            throw new UnsupportedOperationException();
        }
    }

    /*
     * Initializes the program with 10 elements */
    public void initialize() {
        add("Porsche", "911 Turbo S", 650, 3, 350000);
        add("Mercedes-Benz", "A 250", 225, 2, 50000);
        add("BMW", "M135i", 306, 5, 50000);
        add("Dacia", "Duster", 150, 7, 20000);
        add("Volkswagen", "Golf GTI", 245, 3, 35000);
        add("Audi", "S3", 310, 4, 45000);
        add("Renault", "Megane", 116, 2, 23000);
        add("Porsche", "Carrera 4S", 450, 5, 100000);
        add("Mercedes-Benz", "AMG GTR", 585, 2, 185000);
        add("Škoda", "Octavia", 245, 6, 35000);
    }

}
