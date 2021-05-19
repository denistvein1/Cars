package UI;

import Domain.Car;
import Service.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UI {

    private final Service service;

    public UI() {
        service = new Service();
        service.initialize();
    }

    public void add() {
        String company, model;
        int hp, quantity, price;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter company: ");
        company = scanner.nextLine();
        System.out.println("Enter model: ");
        model = scanner.nextLine();
        try {
            System.out.println("Enter hp: ");
            hp = scanner.nextInt();
            System.out.println("Enter quantity: ");
            quantity = scanner.nextInt();
            System.out.println("Enter price: ");
            price = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("hp/quantity/price must be numbers");
            return;
        }
        try {
            service.add(company, model, hp, quantity, price);
            System.out.println(company + " " + model + " added");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input!");
        }
    }

    public void delete() {
        String company, model;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter company: ");
        company = scanner.nextLine();
        System.out.println("Enter model: ");
        model = scanner.nextLine();
        try {
            service.delete(company, model);
            System.out.println(company + " " + model + " deleted");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input!");
        } catch (NoSuchElementException e) {
            System.out.println(company + " " + model + " doesn't exist");
        }
    }

    public void update() {
        String company, model;
        int newHp, newQuantity, newPrice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter company: ");
        company = scanner.nextLine();
        System.out.println("Enter model: ");
        model = scanner.nextLine();
        try {
            System.out.println("Enter new hp: ");
            newHp = scanner.nextInt();
            System.out.println("Enter new quantity: ");
            newQuantity = scanner.nextInt();
            System.out.println("Enter new price: ");
            newPrice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("new hp/quantity/price must be numbers");
            return;
        }
        try {
            service.update(company, model, newHp, newQuantity, newPrice);
            System.out.println(company + " " + model + " updated");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input!");
        } catch (NoSuchElementException e) {
            System.out.println(company + " " + model + " doesn't exist");
        }
    }

    public void filterHpSortedByQuantity() {
        String hp;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter hp: ");
        try {
            hp = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("hp must be a number");
            return;
        }
        ArrayList<Car> allCars = service.getElementsGraterHp(hp);
        if (allCars.size() == 0) {
            System.out.println("Empty list");
        }
        service.sortByQuantity(allCars);
        for (Car car : allCars) {
            System.out.println(car.toString());
        }
    }

    public void filterByQuantity() {
        String quantity;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter quantity: ");
        try {
            quantity = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("quantity must be a number");
            return;
        }
        ArrayList<Car> allCars = service.getElementsLessQuantity(quantity);
        if (allCars.size() == 0) {
            System.out.println("Empty list");
        }
        for (Car car : allCars) {
            System.out.println(car.toString());
        }
    }

    public void undo() {
        try {
            service.undo();
            System.out.println("Undone successfully");
        } catch (UnsupportedOperationException e) {
            System.out.println("Nothing to undo!");
        }
    }

    public void redo() {
        try {
            service.redo();
            System.out.println("Redone successfully");
        } catch (UnsupportedOperationException e) {
            System.out.println("Nothing to redo!");
        }
    }

    public void printAll() {
        ArrayList<Car> allCars = service.getElements();
        if (allCars.size() == 0) {
            System.out.println("Empty list");
        }
        for (Car car : allCars) {
            System.out.println(car.toString());
        }
    }

    public void introduction() {
        System.out.println("Welcome to Denis' car dealer!\nHere you can manage all the cars in the dealership\n" +
                "Choose from the options below: ");
    }

    public void options() {
        System.out.println("0. Exit");
        System.out.println("1. Add a car");
        System.out.println("2. Delete a car");
        System.out.println("3. Update a car");
        System.out.println("4. Print all the cars");
        System.out.println("5. See the cars that have hp > N sorted ascending by quantity");
        System.out.println("6. See the cars that are in short supply");
        System.out.println("7. Undo last operation");
        System.out.println("8. Redo last operation");
        System.out.println("9. Print options again");
    }

    public void menu() {
        introduction();
        options();
        while (true) {
            int choice;
            String choiceString = "";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your choice: ");
            try {
                choiceString = scanner.nextLine();
                choice = Integer.parseInt(choiceString);
            } catch (NumberFormatException e) {
                System.out.println(choiceString + " is not a number");
                continue;
            }
            if (choice == 0) {
                break;
            } else if (choice == 1) {
                add();
            } else if (choice == 2) {
                delete();
            } else if (choice == 3) {
                update();
            } else if (choice == 4) {
                printAll();
            } else if (choice == 5) {
                filterHpSortedByQuantity();
            } else if (choice == 6) {
                filterByQuantity();
            } else if (choice == 7) {
                undo();
            } else if (choice == 8) {
                redo();
            } else if (choice == 9) {
                options();
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

}
