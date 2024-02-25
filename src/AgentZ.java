import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentZ extends Agent {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println("je suis l'agent :" + this.getName());
		ACLMessage sms = new ACLMessage(ACLMessage.INFORM);
		sms.addReceiver(new AID("Amir", AID.ISLOCALNAME));
		sms.setContent("Bonjours");
		this.send(sms);
//		super.setup();
		System.out.println("Message envoyer" + this.getName());
		this.addBehaviour(new CyclicBehaviour() {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				System.out.println("<<<<<<<<<<<<<<<<<<<sms.getContent()+>>>>>>>>>>>>>>>>>>>>");

				ACLMessage sms = myAgent.receive();
				if (sms == null) {
					this.block();
				} else {
					System.out.println("<<<<<<<<<<<<<<<<<<<" + sms.getContent() + ">>>>>>>>>>>>>>>>>>>>");
				}
			}

		});
	}

	@Override
	protected void takeDown() {
		System.out.println("je suis fini :" + this.getName());

//		super.takeDown();
	}

}
