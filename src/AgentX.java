import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentX extends Agent {

	/**
		 *  
		 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {

		System.out.println("je suis l'agent :" + this.getName());

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
		super.setup();
	}

	@Override
	protected void takeDown() {
		System.out.println("je suis fini :" + this.getName());

		super.takeDown();
	}

}
