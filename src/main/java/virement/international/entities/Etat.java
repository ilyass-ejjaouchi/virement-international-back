package virement.international.entities;
import virement.international.exceptions.BadRequestException;

public enum Etat {
    ENREGISTRÉ("ENREGISTRÉ"),
    EN_COURS_DE_SIGNATURE("EN_COURS_DE_SIGNATURE"),
    EN_COURS_DE_TRAITEMENT("EN_COURS_DE_TRAITEMENT"),
    ABANDONNÉ("ABANDONNÉ"),
    SIGNÉ("SIGNÉ"),
    ANNULÉ("ANNULÉ"),
    NON_VALIDÉ("NON_VALIDÉ"),
    TRAITÉ("TRAITÉ");
    private String etat = "";

    Etat(String etat){
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }
    public static Etat fromEtat(String etat) {
        if (etat == null) return null;
        switch (etat) {
            case "ENREGISTRÉ":
                return Etat.ENREGISTRÉ;
            case "EN_COURS_DE_SIGNATURE":
                return Etat.EN_COURS_DE_SIGNATURE;
            case "ABANDONNÉ":
                return Etat.ABANDONNÉ;
            case "SIGNÉ":
                return Etat.SIGNÉ;
            case "ANNULÉ":
                return Etat.ANNULÉ;
            case "EN_COURS_DE_TRAITEMENT":
                return Etat.EN_COURS_DE_TRAITEMENT;
            case "NON_VALIDÉ":
                return Etat.NON_VALIDÉ;
            case "TRAITÉ":
                return Etat.TRAITÉ;
            default:
                throw new BadRequestException("l' accronyme [" + etat + "] n' est pas supporté.");
        }
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String toString(){
        return etat;
    }
}
