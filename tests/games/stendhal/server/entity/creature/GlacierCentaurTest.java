package games.stendhal.server.entity.creature;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.maps.MockStendhalRPRuleProcessor;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.common.constants.Nature;

public class GlacierCentaurTest {
	// Setup mock World
	@BeforeClass
	public static void setUpBeforeClass() {
		MockStendlRPWorld.get();
		MockStendhalRPRuleProcessor.get();
	}
	// Reset mock World
	@AfterClass
	public static void tearDownAfterClass() {
		MockStendlRPWorld.reset();
	}
	
	@Test
	public void testGlacierCentaurSusceptibilityIce() {
		Creature glacierCentaur = SingletonRepository.getEntityManager().getCreature("glacier centaur");
		assertTrue(glacierCentaur.getSusceptibility(Nature.ICE) < 1);
	}
	
	@Test
	public void testGlacierCentaurSusceptibilityFire() {
		Creature glacierCentaur = SingletonRepository.getEntityManager().getCreature("glacier centaur");
		assertTrue(glacierCentaur.getSusceptibility(Nature.FIRE) > 1);
	}

}