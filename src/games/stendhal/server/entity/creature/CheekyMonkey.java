package games.stendhal.server.entity.creature;

//import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPClass;
import marauroa.common.game.RPObject;
import marauroa.common.game.SyntaxException;

public class CheekyMonkey extends Pet{
	
	/** the logger instance. */
	private static final Logger logger = Logger.getLogger(CheekyMonkey.class);

	@Override
	void setUp() {
		HP = 1450;
		incHP = 100;
		ATK = 535;
		DEF = 250;
		lv_cap = 25;
		XP = 28900;
		baseSpeed = 10;

		setAtk(ATK);
		setDef(DEF);
		//setLVCap(LVCap);
		setXP(XP);
		setBaseHP(HP);
		setHP(HP);
		setSize(1, 1);

	}
	public static void generateRPClass() {
		try {
			final RPClass cheeky_monkey = new RPClass("cheeky_monkey");
			cheeky_monkey.isA("pet");
		} catch (final SyntaxException e) {
			logger.error("cannot generate RPClass", e);
		}
	}
	
	@Override
	protected
	List<String> getFoodNames() {
		//return Arrays.asList("ham", "pizza", "meat");
		return null;
	}
	
	public CheekyMonkey(final Player owner) {
		super();
		setOwner(owner);
		setUp();
		setRPClass("cheeky_monkey");
		put("type", "cheeky_monkey");

		if (owner != null) {
			// add pet to zone and create RPID to be used in setPet()
			owner.getZone().add(this);
			owner.setPet(this);
		}

		update();
	}
	
	public CheekyMonkey(final RPObject object, final Player owner) {

		super(object, owner);

		setRPClass("cheeky_monkey");
		put("type", "cheeky_monkey");

		update();
	}
	
	public CheekyMonkey() {
		this(null);
	}
	
	public Creature getNearbyCreature(int range){
		return null;
	}
	
	public int attemptSteal(Creature c) {
		return -1;
	}
}
