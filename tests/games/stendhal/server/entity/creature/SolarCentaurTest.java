package games.stendhal.server.entity.creature;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.maps.MockStendhalRPRuleProcessor;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.common.constants.Nature;

public class SolarCentaurTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		MockStendlRPWorld.get();
		MockStendhalRPRuleProcessor.get();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		MockStendlRPWorld.reset();
	}
	
	@Test
	public void testSolarCentaurSusceptibilityFire() {
		Creature solarCentaur = SingletonRepository.getEntityManager().getCreature("solar centaur");
		assertTrue(solarCentaur.getSusceptibility(Nature.FIRE) < 1);
	}
	
	@Test
	public void testSolarCentaurSusceptibilityIce() {
		Creature solarCentaur = SingletonRepository.getEntityManager().getCreature("solar centaur");
		assertTrue(solarCentaur.getSusceptibility(Nature.ICE) > 1);
	}

}