import java.util.ArrayList;
import java.util.List;

public class Clientes {
    private String name;
    private String cpf;
    private int points;
    private boolean nextOrderFree;
    private static List<Clientes> clientList = new ArrayList<>();

    public Clientes(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        clientList.add(this);
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isNextOrderFree() {
        return nextOrderFree;
    }

    public void setNextOrderFree(boolean nextOrderFree) {
        this.nextOrderFree = nextOrderFree;
    }


}