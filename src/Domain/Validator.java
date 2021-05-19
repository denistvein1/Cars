package Domain;

public class Validator {

    public static void validate(String company, String model, int hp, int quantity, int price) throws IllegalArgumentException {
        if (company.length() < 2 || model.length() < 2 || hp < 50 || quantity < 1 || price < 100) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateShort(String company, String model) throws IllegalArgumentException {
        if (company.length() < 2 || model.length() < 2) {
            throw new IllegalArgumentException();
        }
    }
}
