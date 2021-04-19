package virement.international.entities;
import virement.international.exceptions.BadRequestException;

public enum EtatVirement {
    ENREGISTRÉ("ENREGISTRÉ"),
    EN_COURS_DE_SIGNATURE("EN_COURS_DE_SIGNATURE"),
    EN_COURS_DE_TRAITEMENT("EN_COURS_DE_TRAITEMENT"),
    ABANDONNÉ("ABANDONNÉ"),
    SIGNÉ("SIGNÉ"),
    ANNULÉ("ANNULÉ"),
    NON_VALIDÉ("NON_VALIDÉ"),
    TRAITÉ("TRAITÉ");
    private String etat = "";

    EtatVirement(String etat){
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }
    public static EtatVirement fromEtat( String etat) {
        if (etat == null) return null;
        switch (etat) {
            case "ENREGISTRÉ":
                return EtatVirement.ENREGISTRÉ;
            case "EN_COURS_DE_SIGNATURE":
                return EtatVirement.EN_COURS_DE_SIGNATURE;
            case "ABANDONNÉ":
                return EtatVirement.ABANDONNÉ;
            case "SIGNÉ":
                return EtatVirement.SIGNÉ;
            case "ANNULÉ":
                return EtatVirement.ANNULÉ;
            case "EN_COURS_DE_TRAITEMENT":
                return EtatVirement.EN_COURS_DE_TRAITEMENT;
            case "NON_VALIDÉ":
                return EtatVirement.NON_VALIDÉ;
            case "TRAITÉ":
                return EtatVirement.TRAITÉ;
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
