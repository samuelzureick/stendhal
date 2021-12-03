package games.stendhal.server.entity.creature;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.StackableItem;
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
		// Arrange
		final CheekyMonkey curiousGeorge = new CheekyMonkey();
		
		// Test
		assertThat(curiousGeorge.getFoodNames(), is(foods));
	}

	/**
	 * Tests for CheekyMonkeyPlayer.
	 */
	@Test
	public void testCheekyMonkeyPlayer() {
		// Arrange
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		zone.add(bob);
		final CheekyMonkey curiousGeorge = new CheekyMonkey(bob);

		// Test
		assertThat(curiousGeorge.getFoodNames(), is(foods));
	}

	/**
	 * Tests for CheekyMonkeyRPObjectPlayer.
	 */
	@Test
	public void testCheekyMonkeyRPObjectPlayer() {
		// Arrange
		RPObject template = new RPObject();
		template.put("hp", 30);
		final CheekyMonkey curiousGeorge = new CheekyMonkey(template, PlayerTestHelper.createPlayer("bob"));
		
		// Test
		assertThat(curiousGeorge.getFoodNames(), is(foods));
	}
	
	@Test
	public void testStealableItems(){
		// Arrange
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("quantity", "1");
		attributes.put("max_quantity", "2147483647");
		final StackableItem money = new StackableItem("money", "misc", "bag01", attributes);
		
		// Test
		assertNotNull("Generated item is not null", money);
		assertTrue(money.has("stealable"));
	}

}
