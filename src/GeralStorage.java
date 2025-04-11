import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeralStorage {
    static List<Ordens> ordens = new ArrayList<>();
    static List<Ordens> emergencyOrders = new ArrayList<>();
    private static int orderCounter = 1;
    private static List<Clientes> clientes = new ArrayList<>();
    private static Map<String, Integer> clientPoints = new HashMap<>();
    private static List<Tecnicos> tecnicos = new ArrayList<>();
    private static List<String> serviceNames = new ArrayList<>();
    private static Map<String, Ordens> technicianAssignments = new HashMap<>();
    private static StringBuilder txtHistorico = new StringBuilder();

    public static void addOrder(Ordens ordem) {
        String orderName = "Ordem-" + orderCounter++;
        ordem.setOrderName(orderName);

        if (ordem.isEmergencial()) {
            emergencyOrders.add(ordem);
        } else {
            ordens.add(ordem);
        }
    }

    public static int getOrderCounter() {
        return orderCounter;
    }

    public static String getOrderDetails(String orderName) {
        for (Ordens ordem : ordens) {
            if (ordem.getOrderName().equals(orderName)) {
                return ordem.toString();
            }
        }
        for (Ordens ordem : emergencyOrders) {
            if (ordem.getOrderName().equals(orderName)) {
                return ordem.toString();
            }
        }
        return null;
    }

    public static void updateOrderName(String oldOrderName, String newOrderName) {
        for (Ordens ordem : ordens) {
            if (ordem.getOrderName().equals(oldOrderName)) {
                ordem.setOrderName(newOrderName);
                return;
            }
        }
        for (Ordens ordem : emergencyOrders) {
            if (ordem.getOrderName().equals(oldOrderName)) {
                ordem.setOrderName(newOrderName);
                return;
            }
        }
    }

    public static void finalizeOrder(int orderId) {
        for (int i = 0; i < ordens.size(); i++) {
            if (ordens.get(i).getId() == orderId) {
                txtHistorico.append(ordens.get(i).toString()).append("\n\n");
                technicianAssignments.remove(ordens.get(i).getTecnico());
                ordens.remove(i);
                return;
            }
        }
        for (int i = 0; i < emergencyOrders.size(); i++) {
            if (emergencyOrders.get(i).getId() == orderId) {
                txtHistorico.append(emergencyOrders.get(i).toString()).append("\n\n");
                technicianAssignments.remove(emergencyOrders.get(i).getTecnico());
                emergencyOrders.remove(i);
                return;
            }
        }
    }

    public static String getTxtHistorico() {
        return txtHistorico.toString();
    }

    public static boolean hasOngoingEmergencyOrder() {
        for (Ordens ordem : emergencyOrders) {
            if (!ordem.getStatus().equals("FINALIZADA")) {
                return true;
            }
        }
        return false;
    }

    public static List<Ordens> getOrders() {
        List<Ordens> allOrders = new ArrayList<>(ordens);
        allOrders.addAll(emergencyOrders);
        return allOrders;
    }

    public static String getClientDetails(String clientName) {
        for (Clientes client : clientes) {
            if (client.getName().equals(clientName)) {
                return client.toString();
            }
        }
        return null;
    }

    public static String getClientCPF(String clientName) {
        for (Clientes client : clientes) {
            if (client.getName().equals(clientName)) {
                return client.getCpf();
            }
        }
        return null;
    }

    public static int getPoints(String clientName) {
        return clientPoints.getOrDefault(clientName, 0);
    }

    public static double calculateDiscount(String clientName) {
        int points = clientPoints.getOrDefault(clientName, 0);
        return Math.min(points * 0.10, 0.40);
    }

    public static void addPoints(String clientName, int points) {
        clientPoints.put(clientName, clientPoints.getOrDefault(clientName, 0) + points);
    }

    public static void exchangePointsForFreeOrder(String clientName) {
        Clientes client = getClientByName(clientName);
        if (client != null) {
            client.setNextOrderFree(true);
            clientPoints.put(clientName, 0);
        }
    }

    public static void addClient(Clientes client) {
        clientes.add(client);
    }

    public static List<String> getClientNames() {
        List<String> clientNames = new ArrayList<>();
        for (Clientes client : clientes) {
            clientNames.add(client.getName());
        }
        return clientNames;
    }

    private static Clientes getClientByName(String clientName) {
        for (Clientes client : clientes) {
            if (client.getName().equals(clientName)) {
                return client;
            }
        }
        return null;
    }

    public static void addTecnico(Tecnicos tecnico) {
        tecnicos.add(tecnico);
    }

    public static boolean isTechnicianAssigned(String tecnico) {
        for (Ordens ordem : ordens) {
            if (ordem.getTecnico().equals(tecnico)) {
                return true;
            }
        }
        for (Ordens ordem : emergencyOrders) {
            if (ordem.getTecnico().equals(tecnico)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isServiceMatchingTechnician(String tecnico, String service) {
        for (Tecnicos t : tecnicos) {
            if (t.getName().equals(tecnico) && t.getServices().contains(service)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getTecnicos() {
        List<String> tecnicoNames = new ArrayList<>();
        for (Tecnicos tecnico : tecnicos) {
            tecnicoNames.add(tecnico.getName());
        }
        return tecnicoNames;
    }

    private static String getTechnicianFromOrder(String order) {
        int startIndex = order.indexOf("Técnico: ") + 9;
        int endIndex = order.indexOf("\n", startIndex);
        return order.substring(startIndex, endIndex);
    }

    public static Double getPrecoDoTecnico(String tecnicoName) {
        for (Tecnicos tecnico : tecnicos) {
            if (tecnico.getName().equals(tecnicoName)) {
                return tecnico.getPrice();
            }
        }
        return null;
    }

    public static void addServiceName(String name) {
        serviceNames.add(name);
    }

    public static List<String> getServiceNames() {
        return new ArrayList<>(serviceNames);
    }
}