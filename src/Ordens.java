public class Ordens {
    private int id;
    private String OrderName;
    private String clienteName;
    private String service;
    private String tecnico;
    private String description;
    private Double price;
    private String prazo;
    private String details;
    private boolean isEmergencial;
    private boolean isFinalizada;

    public Ordens(int id, String clienteName, String service, String tecnico, String description, Double price, String prazo, boolean isEmergencial) {
        this.id = id;
        this.OrderName = "Ordem " + id;
        this.clienteName = clienteName;
        this.service = service;
        this.tecnico = tecnico;
        this.description = description;
        this.price = price;
        this.prazo = prazo;
        this.isEmergencial = isEmergencial;
        this.isFinalizada = false;
    }

    public int getId() {
        return id;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        this.OrderName = orderName;
    }

    public String getStatus() {
        return isFinalizada ? "FINALIZADA" : "PENDENTE";
    }

    public String getClienteName() {
        return clienteName;
    }

    public void setClienteName(String clienteName) {
        this.clienteName = clienteName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public boolean isEmergencial() {
        return isEmergencial;
    }

    public void setEmergencial(boolean emergencial) {
        isEmergencial = emergencial;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean finalizada) {
        isFinalizada = finalizada;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "\n" + OrderName + '\n' +
            "Cliente: " + clienteName + '\n' +
            "Serviço: " + service + '\n' +
            "Técnico: " + tecnico + '\n' +
            "Descrição: " + description + '\n' +
            "Preço: " + price + '\n' +
            "Prazo: " + prazo + " Horas" + '\n' +
            "Emergencial: " + (isEmergencial ? "SIM" : "NÃO") + '\n' +
            "Finalizada: " + (isFinalizada ? "SIM" : "NÃO") + '\n';
    }
}