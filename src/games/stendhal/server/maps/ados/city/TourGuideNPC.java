package games.stendhal.server.maps.ados.city;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.entity.npc.SpeakerNPC;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TourGuideNPC implements ZoneConfigurator {
	
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildNPC(zone);
	}
	
	private void buildNPC(final StendhalRPZone zone) {
	    final SpeakerNPC npc = new SpeakerNPC("Mr Guidy") {
			 
	    	 @Override
	    	 protected void createPath() {
	    		 List<Node> nodes=new LinkedList<Node>();
	             nodes.add(new Node(52,116));
	             nodes.add(new Node(50,116));
	             setPath(new FixedPath(nodes, true));
	    	 }
			
			@Override
			protected void createDialog() {
				addGreeting("Hi there, I'm the local tour guide.");
				//Lets the NPC reply when a player says "job"
				addJob("I'm responsible for giving tours to new players to explore the wonderful world of Stendhal.");
				//Lets the NPC reply when a player asks for help
				addHelp("I'm able to give you tours for a #fee. However, tours are not avaialble yet as I'm working on getting my stall up and running.");
				//Lets the NPC respond about a special trigger word
				addReply("tour", "Tours are not available at the moment, please come back soon when I've got my stall up and running.");
				//use standard goodbye
				addGoodbye("Bye, make sure to come back soon.");
			}

			
	    };
	
    // This determines how the NPC will look like. welcomernpc.png is a picture in data/sprites/npc/
    npc.setEntityClass("guide");
    // set a description for when a player does 'Look'
    npc.setDescription("You see Guidy the tour guide. He can give you tours around the world of Stendhal but it doesn't look like he's set up yet.");
    // Set the initial position to be the first node on the Path you defined above.
    npc.setPosition(52, 116);
    npc.initHP(100);

    zone.add(npc);   
}
}
