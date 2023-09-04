package agents;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// DO NOT DELETE COMMENTS BELOW !!!!!!!!!!!!!!!!!

public class AcheteurAgent extends Agent { // IL suffit de faire heriter une classe de la classe Agent de jade.core pour que ce soit un agent
	
	private String livre ;
	
	@Override
	protected void setup() { // C la 1ere methode qui va s'executer au lancement de l'agent et elle doit etre redefinis lorskon herite de jade.core.Agent
		// TODO Auto-generated method stub
		super.setup();
		Object[] params = getArguments() ;		// Permet de recuperer les arguments donner par le conatiner AcheteurContainer 
		livre = params[0].toString() ;
		System.out.println("******************************************************");
		System.out.println("Deploiement de l'Agent " + getAID().getName() ); // getAID().getName() donnele nom complet de l'Agent avc l'@ip et le port 
		System.out.println("Je viens tenter d'acheter le livre "+ livre);
		System.out.println("******************************************************");
		/*
		addBehaviour(new Behaviour() {		// Permet d'afecter un Comportement a l'agent 
			// La methode behaviour execute une boucle a l'infini de la methode Action() juska ce que la methode done() return True 
			// Action() est le 1er executer donc executer au moins une fois 
			@Override
			public boolean done() {
				// TODO Auto-generated method stub
				return false;
			}
			
 			@Override
			public void action() {
				// TODO Auto-generated method stub
				System.out.println("Tentative d'acheter le livre "+ livre);
			}

			@Override
			public void onStart() {  // Appeler avant la methode action()
				super.onStart();
			}

			@Override
			public int onEnd() {	// Appeler juste apreès le retournement de true de la methode done()
				// TODO Auto-generated method stub
				return super.onEnd();
			}
		});
		 */

		// OneShot Behaviour : C un comportement qui s'execute une seule fois, il n'a pas de methode done() car il s'execute une seule fois et return true automatiquement
		/*addBehaviour(new OneShotBehaviour() {
			@Override
			public void action() {
				// TODO Auto-generated method stub
				System.out.println("One shot behaviour");
			}
		});*/

		// Cyclic Behaviour : C un comportement qui s'execute en boucle, il a une methode done() qui return tjrs false, boucle a l'infini
		/*addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				// TODO Auto-generated method stub
				System.out.println("Cyclic behaviour");
			}
		});*/

		// Waker Behaviour : C un comportement qui s'execute apres un certain temps, il a une methode done() qui return true apres un certain temps, schedule()
			// => il utilise la methode onWake() qui s'execute apres le temps defini, et prend fin apres l'execution de la methode onWake()

		/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss");
		Date wakeupDate ;
		try {
			wakeupDate = simpleDateFormat.parse("20/04/2024 at 15:00:00") ;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		addBehaviour(new WakerBehaviour(this, wakeupDate ) {		// 10000ms = 10s
			@Override
			protected void onWake() {
				// TODO Auto-generated method stub
				System.out.println("Tentative d'acheter le livre "+ livre);
			}
		}) ;*/

		// Ticker Behaviour : c un comportement qui s'execute preriodiquement avec la methode onTick() qui s'execute a chaque fois
			// => La durée de l'execution de la methode onTick() est defini par le constructeur de la classe TickerBehaviour

		/*addBehaviour(new TickerBehaviour(this, 10000) {		// 10000ms = 10s
			@Override
			protected void onTick() {
				// TODO Auto-generated method stub
				System.out.println("Tentative d'acheter le livre "+ livre);
			}
		});*/

		// Pour plusieur comportement en mm temps, il faut utiliser la classe ParallelBehaviour
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour() ;
		parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {
			@Override
			public void action() {
				// TODO Auto-generated method stub
				System.out.println("One shot behaviour");
			}
		});
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				// TODO Auto-generated method stub
				System.out.println("Cyclic behaviour");
				// ACLMessage aclMessage = receive() ; // Permet de recevoir un message de tout autre agent

				// MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM) ; // Permet de filtrer les messages recu de type INFORM uniquement
				// ACLMessage aclMessage = receive(messageTemplate) ; // Permet de recevoir un message de tout autre agent de type INFORM uniquement

				// Permet de filtrer les messages recu de type INFORM et de langue Francais (combinaisons) :
				MessageTemplate messageTemplate2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchLanguage("Francais")) ;
				ACLMessage aclMessage = receive(messageTemplate2) ;

				if (aclMessage != null) {
					System.out.println("J'ai recu un message de l'agent "+ aclMessage.getSender().getName());
					System.out.println("Le contenu du message est : "+ aclMessage.getContent());
					System.out.println("Le language du message est : "+ aclMessage.getLanguage());
					System.out.println("Le protocole du message est : "+ aclMessage.getProtocol());
					System.out.println("Le performative SpeechAct du message est : "+ ACLMessage.getPerformative(aclMessage.getPerformative()));

					ACLMessage reply = aclMessage.createReply() ;
					reply.setContent("J'ai bien recu votre message");
					send(reply);

				} else {
					block() ; // Permet de bloquer l'agent jusqu'a ce qu'il recoit un message, fait un subscribe de la boite de msg
				}
			}
		});

		addBehaviour(parallelBehaviour) ;

	}
	
	@Override
		protected void beforeMove() { 		// C 1 methode qui s'execute juste avant la migration de l'Agent
			// TODO Auto-generated method stub
			super.beforeMove();
			System.out.println("**********************************");
			System.out.println("Avant Migration de l'Agent "+ getAID().getLocalName()); 	// LocalName etant le nom abregé de l'agent
			System.out.println("**********************************");
	}
	
	@Override
		protected void afterMove() {		// C 1 methode qui s'execute juste apres la migration de l'Agent
			// TODO Auto-generated method stub
			super.afterMove();
			System.out.println("**********************************");
			System.out.println("Apres Migration de l'Agent "+ getAID().getLocalName()); 	// LocalName etant le nom abregé de l'agent
			System.out.println("**********************************");
		}
	
	@Override
		protected void takeDown() {		// Avant la descruction de l'Agent
			// TODO Auto-generated method stub
			super.takeDown();
			System.out.println("**********************************");
			System.out.println("Je suis entrain de Mourir..., Agent "+ getAID().getLocalName()); 	// LocalName etant le nom abregé de l'agent
			System.out.println("**********************************");
		}

}

