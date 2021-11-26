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


package games.stendhal.server.maps.ados.city;

import static org.junit.Assert.*;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import games.stendhal.server.entity.npc.ConversationPhrases;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class TourGuideNPCTest extends ZonePlayerAndNPCTestImpl {
	
	private static final String ZONE_NAME = "testzone";
	private static final String npcName = "guidy";
	private Player player;
	
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
		
		super.setUp();
		player = createPlayer("player");
	}

	@Test
	public void testHi() {
		//Arrange
		final SpeakerNPC guidyNPC = getNPC(npcName);
		final Engine guidyEngine = guidyNPC.getEngine();
		
		//Act
		guidyEngine.step(player, ConversationPhrases.GREETING_MESSAGES.get(0));
		
		//Assert
		assertTrue(guidyNPC.isTalking());
		assertEquals("Hi there, I'm the local tour guide", getReply(guidyNPC));
	}
	
	@Test
	public void testHelp() {
		//Arrange
		final SpeakerNPC guidyNPC = getNPC(npcName);
		final Engine guidyEngine = guidyNPC.getEngine();
		
		//Act
		guidyEngine.step(player, ConversationPhrases.GREETING_MESSAGES.get(0));
		guidyEngine.step(player, ConversationPhrases.HELP_MESSAGES.get(0));
		
		//Assert
		assertTrue(guidyNPC.isTalking());
		assertEquals("I'm able to give you #tours for a #fee. However, tours are not avaialble yet as I'm working on getting my stall up and running.", getReply(guidyNPC));
	}
	
	@Test
	public void testBye() {
		//Arrange
		final SpeakerNPC guidyNPC = getNPC(npcName);
		final Engine guidyEngine = guidyNPC.getEngine();
		
		//Act
		guidyEngine.step(player, ConversationPhrases.GREETING_MESSAGES.get(0));
		guidyEngine.step(player, ConversationPhrases.GOODBYE_MESSAGES.get(0));
		
		//Assert
		assertTrue(guidyNPC.isTalking());
		assertEquals("bye", getReply(guidyNPC));
	}

}
