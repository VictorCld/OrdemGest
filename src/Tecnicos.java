import java.util.List;

public class Tecnicos {
    private String name;
    private List<String> services;
    private double price;

    public Tecnicos(String name, List<String> services, double price) {
        this.name = name;
        this.services = services;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public List<String> getServices() {
        return services;
    }

    public double getPrice() {
        return price;
    }
}