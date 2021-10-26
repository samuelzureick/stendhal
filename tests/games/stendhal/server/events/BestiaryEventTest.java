package games.stendhal.server.events;

//import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import games.stendhal.server.entity.player.Player;
//import marauroa.common.game.RPObject;
import utilities.PlayerTestHelper;

public class BestiaryEventTest{
	Player player;
	@Before
	public void setUp() {
		PlayerTestHelper.generatePlayerRPClasses();
		player = PlayerTestHelper.createPlayer("player");
	}
	
	@Test
	public void testBestiaryEvent() {
		BestiaryEvent event = new BestiaryEvent(player,false,false);
		String e = event.get("enemies");
		assertFalse(e.contains("???"));
	}
}