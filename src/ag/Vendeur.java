package ag;

import java.util.Arrays;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import models.Livre;

public class Vendeur extends Agent {
	private static final long serialVersionUID = 1L;
	Livre livre1 = new Livre("Le Maître et Marguerite", "Mikhaïl Boulgakov", 1967, "978-2-07-036822-8");
	Livre livre2 = new Livre("Les Misérables", "Victor Hugo", 1862, "978-0-14-044430-8");
	Livre livre3 = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
	Livre livre4 = new Livre("Orgueil et Préjugés", "Jane Austen", 1813, "978-1-85326-000-1");
	Livre livre5 = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "978-2-266-11121-4");
	Livre livre6 = new Livre("L'Odyssée", "Homère", -800, "978-2-07-041622-6");
	Livre livre7 = new Livre("Don Quichotte", "Miguel de Cervantes", 1605, "978-2-253-09884-4");
	Livre livre8 = new Livre("Les Hauts de Hurlevent", "Emily Brontë", 1847, "978-2-253-09884-4");
	Livre livre9 = new Livre("Crime et Châtiment", "Fiodor Dostoïevski", 1866, "978-2-253-09905-6");
	Livre livre10 = new Livre("Harry Potter à l'école des sorciers", "J.K. Rowling", 1997, "978-2-07-054127-8");

	public List<Livre> listeLivres = Arrays.asList(livre1, livre2, livre3, livre4, livre5, livre6, livre7, livre8,
			livre9, livre10);

	@Override
	protected void setup() {
		Object[] args = getArguments();
		System.out.println("je suis l'agent :" + this.getName());
		if (args.length > 0) {
//			livre = (String) args[0];
			System.out.println("je cherche a vendres mes produit (livre) j'en ait :" + listeLivres.size());

		} else {
			doDelete();
		}

		this.addBehaviour(new CyclicBehaviour() {
			private static final long serialVersionUID = 1L;
//			private int compteur = 0;

			@Override
			public void action() {
//				++compteur;

				ACLMessage sms = myAgent.receive();
				if (sms == null) {
					this.block();
				} else {
					if (sms.getPerformative() == ACLMessage.INFORM_IF) {
						String contenuString = (String) sms.getContent();
						System.out.println("reponse de l'acheteur : " + contenuString);
						return;
					}
					Livre livreRecherche = null;
					for (Livre livre : listeLivres) {
						if (livre.getTitre().equals(sms.getContent())) {
							livreRecherche = livre;
							break; // Arrêter la boucle une fois que le livre est trouvé
						}
					}

					if (livreRecherche != null) {
						ACLMessage sms1 = new ACLMessage(ACLMessage.INFORM);
						sms1.addReceiver(sms.getSender());
						try {
							System.out.println("Commande recu ---" + sms.getContent() + ">>>>>>>>>>>>>>>>>>>>");
							sms1.setContentObject(livreRecherche);
							send(sms1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						ACLMessage sms3 = new ACLMessage(ACLMessage.INFORM_IF);

						System.out.println("Commande recu ---" + sms.getContent() + ">>>>>>>>>>>>>>>>>>>>");

						ACLMessage sms1 = new ACLMessage(ACLMessage.INFORM_IF);
						sms1.addReceiver(sms.getSender());
						try {
							sms3.setContent(sms.getContent());
							sms3.addReceiver(new AID("Fournisseur1", AID.ISLOCALNAME));
							send(sms3);
							System.out.println("Commande recu ---" + sms.getContent() + ">>>>>>>>>>>>>>>>>>>>");
							sms1.setContent("Livre non trouvé dans la liste.");
							send(sms1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			}

		});
		this.addBehaviour(new TickerBehaviour(this, 5000) {

			private static final long serialVersionUID = 1L;
//			private int compteur = 0;

			@Override
			protected void onTick() {
//				++compteur;
//				ACLMessage sms = new ACLMessage(ACLMessage.INFORM);
//				sms.addReceiver(new AID("Malik", AID.ISLOCALNAME));
//				try {
//					// Définir l'objet Livre comme contenu du message
//					Livre l = listeLivres.get(0);
//					sms.setContentObject(l);
//					// Envoyer le message
//					send(sms);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("<<<<<<<<<<<<<<<<<<< je veux acheter le livre " + compteur + ">>>>>>>>>>>>>>>>>>>>");

			}

		});
		super.setup();
	}

	@Override
	protected void takeDown() {
		System.out.println("je suis fini :" + this.getName());

		super.takeDown();
	}

	@Override
	public void doMove(Location arg0) {
		System.out.println("Depart pour  :" + arg0.getName());

	}
}
