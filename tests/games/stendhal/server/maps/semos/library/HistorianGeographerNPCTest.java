package games.stendhal.server.maps.semos.library;

import static org.junit.Assert.*;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import games.stendhal.server.entity.player.Player;

public class HistorianGeographerNPCTest{
	
	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
	}

	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new HistorianGeographerNPC().configureZone(zone, null);
		npc = SingletonRepository.getNPCList().get("Zynn Iwuhos");
		en = npc.getEngine();
		player = PlayerTestHelper.createPlayer("player");
	}
	
	@Test
	public void testHelpXP() {
		// XP before 
		final int initialXP = player.getXP();

		// Initiate the conversation.
		en.step(player, "hi");
		assertTrue(npc.isTalking());
		assertEquals("Hi, potential reader! Here you can find records of the history of Semos, and lots of interesting facts about this island of Faiumoni. If you like, I can give you a quick introduction to its #geography and #history! I also keep up with the #news, so feel free to ask me about that.",
				getReply(npc));

		// Testing reply using 'news', the 'news' reply uses the same code as the other XP giving replies
		// so the rest of the activations are tested.
		en.step(player, "news");
		assertEquals("The Deniran Empire is currently seeking adventurers to sign on as mercenaries with their army to retake southern Faiumoni from the forces of Blordrough. Unfortunately Blordrough is still holding out against everything the Empire can throw at him.",
				getReply(npc));

		// Ensures that the player gains 5 experience.
		assertEquals(initialXP+5, player.getXP());
	}

}