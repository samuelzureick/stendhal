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
package games.stendhal.server.maps.quests;

import java.util.ArrayList;
import java.util.List;

import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.condition.LevelGreaterThanCondition;
import games.stendhal.server.entity.npc.condition.LevelLessThanCondition;
import games.stendhal.server.entity.player.Player;

/**
 * QUEST: Speak with Zynn PARTICIPANTS: - Zynn
 *
 * STEPS: - Talk to Zynn to activate the quest and keep speaking with Zynn.
 *
 * REWARD: - 5 XP for each question asked
 * REPETITIONS: - As much as wanted.
 */

// Zynn questions/help moved to HistorianGeographer file.
public class MeetZynn extends AbstractQuest {
	
	SpeakerNPC npc = null;
	
	public MeetZynn() {
		super();
		npc = npcs.get(getNPCName());
	}
	public MeetZynn(SpeakerNPC npcIn) {
		super();
		npc = npcIn;
	}
	
	@Override
	public String getSlotName() {
		return "meetzynn";
	}
	private void step_1() {

		npc.add(ConversationStates.ATTENDING, "bye",
			new LevelLessThanCondition(15),
			ConversationStates.IDLE,
			"Bye. Hey, if you're going to hang around the library, don't forget to be quiet; people could be studying!",
			null);

		npc.add(ConversationStates.ATTENDING, "bye",
			new LevelGreaterThanCondition(14),
			ConversationStates.IDLE,
			"Bye. Hey, you should consider getting a library card, you know.",
			null);
	}

	@Override
	public void addToWorld() {
		fillQuestInfo(
				"Meet Zynn Iwuhos",
				"Zynn Iwuhos, in the Semos library, is a great source of useful information.",
				true);
		step_1();
	}

	@Override
	public String getName() {
		return "MeetZynn";
	}

	// no quest slots ever get set so making it visible seems silly
	// however, there is an entry for another quest slot in the games.stendhal.server.maps.semos.library.HistorianGeographerNPC file
	@Override
	public boolean isVisibleOnQuestStatus() {
		return false;
	}

	@Override
	public List<String> getHistory(final Player player) {
		return new ArrayList<String>();
	}

	@Override
	public String getNPCName() {
		return "Zynn Iwuhos";
	}
}
