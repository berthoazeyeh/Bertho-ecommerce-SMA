package ag;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import models.Livre;

public class Consommateur extends Agent {
	private static final long serialVersionUID = 1L;
	private String livre;

	@Override
	protected void setup() {
		Object[] args = getArguments();
		System.out.println("je suis l'agent :" + this.getName());
		if (args.length > 0) {
			livre = (String) args[0];
			System.out.println("je cherche a acheter un livre" + livre);

		} else {
			doDelete();
		}
		ACLMessage sms = new ACLMessage(ACLMessage.INFORM);
		sms.addReceiver(new AID("Vendeur1", AID.ISLOCALNAME));
		sms.setContent(livre);
		send(sms);
		System.out.println("<<<<<<<<<<<<<<<<<<< je veux acheter le livre " + livre + " Bonjours >>>>>>>>>>>>>>>>>>>>");
		this.addBehaviour(new CyclicBehaviour() {
			private static final long serialVersionUID = 1L;
			private int compteur = 0;

			@Override
			public void action() {
				++compteur;
				System.out.println("<<<<<<<<<<<<<<<<<<<" + compteur + ">>>>>>>>>>>>>>>>>>>>");

				ACLMessage msg = myAgent.receive();
				if (msg == null) {
					this.block();
				} else {
					// Vous pouvez également accéder à l'objet contenu dans le message si nécessaire
					try {

						if (msg.getPerformative() == ACLMessage.INFORM) {
							Livre livre = (Livre) msg.getContentObject();
							System.out.println();
							System.out.println("Livre trouvé dans la liste.");
							System.out.println();

							livre.afficherInfo();

							System.out.println();
							System.out.println();
							ACLMessage sms = new ACLMessage(ACLMessage.INFORM_IF);
							sms.addReceiver(msg.getSender());
							sms.setContent("ok bien recu je prend celui-ci");
							send(sms);
						} else if (msg.getPerformative() == ACLMessage.INFORM_IF) {
							String contenuString = (String) msg.getContent();
							System.out.println("reponse du vendeur : " + contenuString);
						} else {
							// Le type de contenu n'est pas reconnu
							System.out.println("Type de contenu non reconnu.");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		});
		this.addBehaviour(new TickerBehaviour(this, 10000) {

			private static final long serialVersionUID = 1L;
//			private int compteur = 0;

			@Override
			protected void onTick() {
//				++compteur;
//				ACLMessage sms = new ACLMessage(ACLMessage.INFORM);
//				sms.addReceiver(new AID("Vendeur1", AID.ISLOCALNAME));
//				sms.setContent("1984");
//				send(sms);
//				System.out.println(
//						"<<<<<<<<<<<<<<<<<<< je veux acheter le livre " + compteur + " Bonjours >>>>>>>>>>>>>>>>>>>>");

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
