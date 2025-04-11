
public class Services {
    private String name;

    public Services(String name) {
        this.name = name;
        GeralStorage.addServiceName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}