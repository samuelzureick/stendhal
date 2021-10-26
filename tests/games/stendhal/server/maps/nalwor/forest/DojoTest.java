/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

package games.stendhal.server.maps.nalwor.forest;

import static org.junit.Assert.*;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import games.stendhal.server.entity.npc.SpeakerNPC;
import utilities.ZonePlayerAndNPCTestImpl;
import utilities.QuestHelper;
import games.stendhal.server.entity.npc.fsm.Engine;


public class DojoTest extends ZonePlayerAndNPCTestImpl{
	
	private static final String ZONE_NAME = "testzone";
	private static final String npcName = "Omura Sumitada";
	private static Dojo configurator = new Dojo();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	}
	
	@Override
	@Before
	public void setUp() throws Exception {
		setZoneForPlayer(ZONE_NAME);
		setNpcNames(npcName);

		addZoneConfigurator(configurator, ZONE_NAME);
		
		super.setUp();
	}
	
	


	@Test
	public void testHi() {
		//Arrange
		final SpeakerNPC omura = getNPC(npcName);
		final Engine en = omura.getEngine();
		
		//Act
		en.step(player, "hi");
		
		//Assert
		assertEquals("This is the assassins' dojo.", getReply(omura));	
	}
	
	@Test
	public void testBye() {
		//Arrange
		final SpeakerNPC omura = getNPC(npcName);
		final Engine en = omura.getEngine();
		
		//Act
		en.step(player, "hi");
		en.step(player,  "bye");
		
		//Assert
		assertEquals("Bye.", getReply(omura));		
	}
	
	@Test
	public void testQuest() {
		//Arrange
		final SpeakerNPC omura = getNPC(npcName);
		final Engine en = omura.getEngine();
		
		//Act
		en.step(player, "hi");
		en.step(player, "quest");
		
		//Assert
		assertEquals("I don't need any help, but I can let you to #train for a #fee if you have been approved by the assassins' HQ.", getReply(omura));			
	}
	
	@Test
	public void feeAttackTest() {
		//Arrange
		final SpeakerNPC omura = getNPC(npcName);
		final Engine en = omura.getEngine();
		
		//Act
		en.step(player, "hi");
		en.step(player, "fee");
		
		//Assert
		assertEquals("At your level of experience, your attack strength is too high to train here at this time.", getReply(omura));
	}
	
	

}
