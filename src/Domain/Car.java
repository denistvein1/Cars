package Domain;

public class Car implements Comparable {
    private String company, model;
    private int hp, quantity, price;


    public Car(String company, String model, int hp, int quantity, int price) {
        this.company = company;
        this.model = model;
        this.hp = hp;
        this.quantity = quantity;
        this.price = price;
    }

    public Car(Car car) {
        this.company = car.getCompany();
        this.model = car.getModel();
        this.hp = car.getHp();
        this.quantity = car.getQuantity();
        this.price = car.getPrice();
    }

    public Car(String company, String model) {
        this(company, model, 50, 1, 100);
    }

    public String getCompany() {
        return this.company;
    }

    public String getModel() {
        return this.model;
    }

    public int getHp() {
        return this.hp;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public String toString() {
        return "Company: " + this.company +
                ", Model: " + this.model +
                ", HP: " + this.hp +
                ", Quantity: " + this.quantity +
                ", Price: " + this.price;
    }

    public void set(Car other) {
        this.company = (other.getCompany());
        this.model = (other.getModel());
        this.hp = (other.getHp());
        this.quantity = (other.getQuantity());
        this.price = (other.getPrice());
    }

    @Override
    public int compareTo(Object o) {
        Car car = (Car) o;
        return this.quantity - car.getQuantity();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car other = (Car) obj;
        return this.company.equals(other.getCompany()) && this.model.equals(other.getModel());
    }
}
