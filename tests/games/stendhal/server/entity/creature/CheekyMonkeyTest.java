package games.stendhal.server.entity.creature;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPClass;
import marauroa.common.game.RPObject;
import utilities.PlayerTestHelper;
import utilities.RPClass.PetTestHelper;

public class CheekyMonkeyTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PetTestHelper.generateRPClasses();

		if (!RPClass.hasRPClass("cheeky_monkey")) {
			CheekyMonkey.generateRPClass();
		}
		
		assertTrue(RPClass.hasRPClass("cheeky_monkey"));
		MockStendlRPWorld.get();
	}

	List<String> foods = Arrays.asList("ham", "pizza", "meat");

	/**
	 * Tests for CheekyMonkey.
	 */
	@Test
	public void testCheekyMonkey() {
		final CheekyMonkey curiousGeorge = new CheekyMonkey();
		assertThat(curiousGeorge.getFoodNames(), is(foods));
	}

	/**
	 * Tests for CheekyMonkeyPlayer.
	 */
	@Test
	public void testCheekyMonkeyPlayer() {

		final StendhalRPZone zone = new StendhalRPZone("zone");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		zone.add(bob);
		final CheekyMonkey curiousGeorge = new CheekyMonkey(bob);

		assertThat(curiousGeorge.getFoodNames(), is(foods));
	}

	/**
	 * Tests for CheekyMonkeyRPObjectPlayer.
	 */
	@Test
	public void testCheekyMonkeyRPObjectPlayer() {
		RPObject template = new RPObject();
		template.put("hp", 30);
		final CheekyMonkey curiousGeorge = new CheekyMonkey(template, PlayerTestHelper.createPlayer("bob"));
		assertThat(curiousGeorge.getFoodNames(), is(foods));
	}
	
	@Test
	public void testCheekyMonkeyTrackingOnCreature() {
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		final CheekyMonkey curiousGeorge = new CheekyMonkey(bob);
		final Creature trackTarget = new Creature();
		zone.add(bob);
		zone.add(trackTarget);
		zone.add(curiousGeorge);
		
		bob.setPosition(0, 0);
		curiousGeorge.setPosition(0, 0);
		trackTarget.setPosition(0, 0);
		
		curiousGeorge.attemptSteal(curiousGeorge.getNearbyCreature(100));
		
		assertTrue(curiousGeorge.hasTargetMoved());
	}
	
	@Test
	public void testCheekyMonkeyTrackingOnPlayer() {
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		final Player ross = PlayerTestHelper.createPlayer("ross");
		
		zone.add(bob);
		zone.add(ross);
		
		CheekyMonkey curiousGeorge = new CheekyMonkey(bob);
		
		bob.setPosition(5,5);
		ross.setPosition(5, 5);
		curiousGeorge.setPosition(1, 1);
		
		assertEquals(curiousGeorge.getNearbyPlayer(100), ross);
		curiousGeorge.attemptSteal(curiousGeorge.getNearbyPlayer(100));
		
		int[] posBefore = {1, 1};
		int afterX = curiousGeorge.getX();
		int afterY = curiousGeorge.getY();
		int[] posAfter = {afterX, afterY};
		
		assertThat(posBefore, not(equalTo(posAfter)));
	}

}
