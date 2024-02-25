import jade.wrapper.AgentContainer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.ControllerException;
public class MainContainer {

	public static void main(String[] args)  {
		try {
		Runtime runtime =Runtime.instance();
		Properties pe= new ExtendedProperties();
		pe.setProperty("gui", "true");
		ProfileImpl profile= new ProfileImpl(pe); 
		profile.setParameter(ProfileImpl.GUI, "true"); 
		AgentContainer mainContainer= runtime.createMainContainer(profile);
			mainContainer.start();
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}

}
