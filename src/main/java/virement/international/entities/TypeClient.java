package virement.international.entities;


public enum TypeClient {
    ENTREPRISE("ENTREPRISE"),
    BANQUE("BANQUE"),
    PARTICILIER("PARTICILIER");
    private String type = "";

    TypeClient (String type){
        this.type = type;
    }

    public String getRole() {
        return type;
    }

    public void setRole(String type) {
        this.type = type;
    }

    public String toString(){
        return type;
    }
}
