package games.stendhal.server.entity.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.maps.MockStendlRPWorld;

public class SleepingBagTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
	}
	
	@Test
	public void testCreation() {
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("quantity", "1");
		attributes.put("max_quantity", "2147483647");
		attributes.put("menu", "Sleep");
		attributes.put("frequency", "12");
		attributes.put("regen", "3");
		final StackableItem bag = new RestItem("sleeping bag", "misc", "bag01", attributes);
		assertNotNull("Generated item is not null", bag);
	}
	
	@Test
	public void testDescribe() {
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("quantity", "1");
		attributes.put("max_quantity", "2147483647");
		attributes.put("menu", "Sleep");
		final StackableItem bag = new StackableItem("sleeping bag", "misc", "bag01", attributes);
		assertEquals(bag.describe(), "You see a §'sleeping bag'.");
	}
	
	@Test
	public void testImage() {
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("quantity", "1");
		attributes.put("max_quantity", "2147483647");
		attributes.put("menu", "Sleep");
		final StackableItem bag = new StackableItem("sleeping bag", "misc", "bag01", attributes);
		String image = bag.get("subclass");
		assertTrue(image.equals(bag.get("subclass")));
	}
}