import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class JadeContainer {

	public static void main(String[] args) {
		try {
			Runtime runtime = Runtime.instance();
			ProfileImpl profile = new ProfileImpl(false);
			profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
			AgentContainer mainContainer = runtime.createAgentContainer(profile);
			AgentController ctl = mainContainer.createNewAgent("Amir", "ag.Acheteur", new Object[] { "Multi-agent" });
//			mainContainer.start();
			ctl.start();
		} catch (ControllerException e) {
			e.printStackTrace();
		}

	}

}
