package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
		String[] vendeursList = controlAcheterProduit.rechercherVendeursProduit(produit);
		if (vendeursList.length == 0) {
			System.out.println("Desole, personne ne vend ce produit au marche.");
			return;
		}
		StringBuilder string = new StringBuilder("Chez quel commercant voulez-vous acheter des fleurs");
		for (int i = 0; i < vendeursList.length; i++) {
			string.append("\n" + (i+1) + " - " + vendeursList[i]);
		}
		int choix = Clavier.entrerEntier(string.toString());
		String nomVendeur = vendeursList[choix-1];
		boolean etalOccupe = controlAcheterProduit.trouverEtalVendeur(nomVendeur);
		if (!etalOccupe) {
			System.out.println("Je suis desolee " + nomVendeur
					+ " mais il faut etre un habitant de notre village pour commercer ici.");
			return;
		}
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis desolee " + nomAcheteur
					+ "mais il faut etre un habitant de notre village pour commercer ici.");
			return;
		}
		System.out.println("Panoramix se deplace jusqu'a l'etal du vendeur Bonemine");
		System.out.println("Bonjour " + nomAcheteur);
		int nbProduit = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
		int quantiteAcheter = controlAcheterProduit.acheterProduit(nomVendeur, nbProduit);
		if (nbProduit == quantiteAcheter) {
			System.out.println(nomAcheteur + "achete " + nbProduit + " " + produit + " a " + nomVendeur);
		}else if (nbProduit == 0) {
			System.out.println(nomAcheteur + " veut acheter " + nbProduit + " fleurs, malheureusement il n’y en a plus !");
		}else if (nbProduit>quantiteAcheter) {
			System.out.println(nomAcheteur + " veut acheter " + nbProduit + " fleurs, malheureusement " + nomVendeur + " n’en a plus que " + quantiteAcheter + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
		}

	}
}
