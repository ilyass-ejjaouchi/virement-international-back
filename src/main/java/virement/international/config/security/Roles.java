package virement.international.config.security;

import virement.international.entities.Etat;
import virement.international.exceptions.BadRequestException;

public enum Roles {
	  ABONNÉ("ABONNÉ"),
	  DG("DG"),
	  BACK_OFFICE("BACK_OFFICE");

	  private String role = "";

	public static Roles fromRole(String role) {
		if (role == null) return null;
		switch (role) {
			case "ABONNÉ":
				return Roles.ABONNÉ;
			case "BACK_OFFICE":
				return Roles.BACK_OFFICE;
			case "DG":
				return Roles.DG;
			default:
				throw new BadRequestException("l' accronyme [" + role + "] n' est pas supporté.");
		}
	}
	  Roles (String role){
	    this.role = role;
	  }

	  public String getRole() {
		return role;
	  }

	   public void setRole(String role) {
			this.role = role;
	   }

	   public String toString(){
		    return role;
	   }
}
