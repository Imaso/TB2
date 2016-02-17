package dz.caar.tb.modeles;

public class Utilisateur {

	private int id_utilisateur;
	private String mdp;
	private String profil;
	
	public Utilisateur(int id_utilisateur, String mdp, String profil) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.mdp = mdp;
		this.profil = profil;
	}

	public Utilisateur() {
		super();
	}

	public int getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}
	
}
