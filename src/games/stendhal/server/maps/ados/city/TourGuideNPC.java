package games.stendhal.server.maps.ados.city;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import java.util.Map;

public class TourGuideNPC implements ZoneConfigurator {
	
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildNPC(zone);
	}
	
	private void buildNPC(final StendhalRPZone zone) {
	    final SpeakerNPC npc = new SpeakerNPC("Mr Guidy") {
	    	protected void createPath() {
	            // NPC does not move
	            setPath(null);
	        }

	        
	    };
	
    // This determines how the NPC will look like. welcomernpc.png is a picture in data/sprites/npc/
    npc.setEntityClass("guide");
    // set a description for when a player does 'Look'
    npc.setDescription("You see Guidy the tour guide. He can give you tours around the world of Stendhal but it doesn't look like he's set up yet.");
    // Set the initial position to be the first node on the Path you defined above.
    npc.setPosition(45, 90);
    npc.initHP(100);

    zone.add(npc);   
}
}
