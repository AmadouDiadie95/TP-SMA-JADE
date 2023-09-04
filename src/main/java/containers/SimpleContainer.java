package containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class SimpleContainer { // Cette classe est un agent qui va interragir avc notre agent rma du mainContainer

	public static void main(String[] args) throws ControllerException {
		// TODO Auto-generated method stub
		Runtime runtime = Runtime.instance() ;
		ProfileImpl config = new ProfileImpl() ;
		config.setParameter(ProfileImpl.MAIN_HOST, "localhost");  // Le parametre MAIN_HOST etant indispensable car etant dans un autre machine 
		// il faut se connecter a notre main container, on lui specifie dc ici le mainContainer
		// config.setParameter(ProfileImpl.MAIN_PORT, "1099");  // On specifie le port du main conatiner qui par defaut est 1099, donc pas forcement besoin de le specifier 
		AgentContainer agentContainer = runtime.createAgentContainer(config) ; // cette fois c createAgentContainer au lieu de createMainContainer
 		
		agentContainer.start(); // Apres l'execution de cet container agent sur le main container on remarque qu'un nouv container est apparu 
	}

}
