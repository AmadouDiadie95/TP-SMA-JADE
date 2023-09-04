package containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MyMainContainer {
	public static void main(String[] args) throws ControllerException {
		Runtime runtime = Runtime.instance() ; // On cree une nouvelle instance de container avec jade.Runtime et non Runtime de java.lang
		ProfileImpl configuration = new ProfileImpl() ; // Cet object permet de creer les argument du container
		// configuration.setParameter("gui", "true");  // Permet de creer une interface graphik pour notre container 
		configuration.setParameter(ProfileImpl.GUI, "true");  // Equibalent a la ligne en haut
		AgentContainer mainContainer = runtime.createMainContainer(configuration) ; // AgentContainer de java.wrapper
	
		mainContainer.start(); // Permet de demarrer le container main avc une interface graphik GUI 
		
		// Ensuite mtn c Run as java App 
	
	
	}
}
