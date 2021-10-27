/***************************************************************************
 *                   (C) Copyright 2003-2016 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.semos.library;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.entity.npc.ConversationPhrases;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.action.IncreaseXPAction;
import games.stendhal.server.entity.npc.action.SayTextAction;
import games.stendhal.server.entity.npc.action.SetQuestAction;
import games.stendhal.server.entity.npc.condition.AndCondition;
import games.stendhal.server.entity.npc.condition.GreetingMatchesNameCondition;
import games.stendhal.server.entity.npc.condition.QuestNotCompletedCondition;

public class HistorianGeographerNPC implements ZoneConfigurator {

	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildSemosLibraryArea(zone);
	}

	private void buildSemosLibraryArea(final StendhalRPZone zone) {
		final SpeakerNPC npc = new SpeakerNPC("Zynn Iwuhos") {

			@Override
			protected void createPath() {
				final List<Node> nodes = new LinkedList<Node>();
				nodes.add(new Node(15, 3));
				nodes.add(new Node(12, 3));
				nodes.add(new Node(12, 6));
				nodes.add(new Node(13, 6));
				nodes.add(new Node(13, 7));
				nodes.add(new Node(13, 6));
				nodes.add(new Node(15, 6));
				nodes.add(new Node(15, 7));
				nodes.add(new Node(15, 6));
				nodes.add(new Node(17, 6));
				nodes.add(new Node(17, 7));
				nodes.add(new Node(17, 3));
				setPath(new FixedPath(nodes, true));
			}

			@Override
			protected void createDialog() {
				addGreeting(null, new SayTextAction("Hi again, [name]. How can I #help you this time?"));
				addGoodbye();

				// A little trick to make NPC remember if it has met
		        // player before and react accordingly
		        // NPC_name quest doesn't exist anywhere else neither is
		        // used for any other purpose
				add(ConversationStates.IDLE,
						ConversationPhrases.GREETING_MESSAGES,
						new AndCondition(new GreetingMatchesNameCondition(getName()),
								new QuestNotCompletedCondition("Zynn")),
						ConversationStates.ATTENDING,
						"Hi, potential reader! Here you can find records of the history of Semos, and lots of interesting facts about this island of Faiumoni. If you like, I can give you a quick introduction to its #geography and #history! I also keep up with the #news, so feel free to ask me about that.",
						new SetQuestAction("Zynn", "done"));

				addHelp("I can best help you by sharing my knowledge of Faiumoni's #geography and #history, as well as the latest #news.");
				addJob("I'm a historian and geographer, committed to writing down every objective fact about Faiumoni. Did you know I wrote most of the books in this library? Well, apart from \"Know How To Kill Creatures\", of course... Hayunn Naratha wrote that.");

				addQuest("I don't think there's really anything you could do for me right now. But thanks for asking!");

				add(ConversationStates.ATTENDING, Arrays.asList("offer", "buy", "trade", "deal", "scroll", "scrolls", "home", "empty",
				        "marked", "summon", "magic", "wizard", "sorcerer"), null, ConversationStates.ATTENDING,
				        "I don't sell scrolls anymore... I had a big argument with my supplier, #Haizen.", null);

				add(
				        ConversationStates.ATTENDING,
				        Arrays.asList("haizen", "haizen."),
				        null,
				        ConversationStates.ATTENDING,
				        "Haizen? He's a wizard who lives in a small hut between Semos and Ados. I used to sell his scrolls here, but we had an argument... you'll have to go see him yourself, I'm afraid.",
				        null);
				
				// Zynn help/info replies. Reward with 5 experience.
				addReply(
								"history",
								"At present, there are two significant powers on Faiumoni; the Deniran Empire, and the dark legions of Blordrough. Blordrough has recently conquered the south of the island, seizing several steel mines and a large gold mine. At present, Deniran still controls the central and northern parts of Faiumoni, including several gold and mithril mines.",
								new IncreaseXPAction(5));

				addReply(
								"news",
								"The Deniran Empire is currently seeking adventurers to sign on as mercenaries with their army to retake southern Faiumoni from the forces of Blordrough. Unfortunately Blordrough is still holding out against everything the Empire can throw at him.",
								new IncreaseXPAction(5));

				addReply(
								"geography",
								"Let's talk about the different #places you can visit on Faiumoni! I can also help you #get and #use a map, or give you advice on using the psychic #SPS system.",
								new IncreaseXPAction(5));

				addReply(
								"places",
								"The most important locations on #Faiumoni are #Or'ril Castle, #Semos, #Ados, #Nalwor, and of course #Deniran City.",
								new IncreaseXPAction(5));

				addReply(
								"Faiumoni",
								"Faiumoni is the island on which you stand! You've probably already noticed the mountains to the north. There is also a large desert in the middle of the island, and of course the river which bisects it, just below #Or'ril Castle to the south.",
								new IncreaseXPAction(5));

				addReply(
								"Semos",
								"Semos is our town where you are right now. We're on the north side of Faiumoni, with a population of about 40-50.",
								new IncreaseXPAction(5));

				addReply(
								"Ados",
								"Ados is an important coastal city to the east of us here in #Semos, where merchants bring trade from #Deniran. It's widely considered to be one of the Empire's most important shipping routes.",
								new IncreaseXPAction(5));

				addReply(
								"Or'ril",
								"Or'ril Castle is one of a series of such castles built to defend the imperial road between #Ados and #Deniran. When in use, it housed a fighting force of around 60 swordsmen, plus ancillary staff. Now that most of the Empire's army has been sent south to fend off the invaders, the castle has been abandoned by the military. As far as I'm aware, it should be empty; I hear some rumours, though...",
								new IncreaseXPAction(5));

				addReply(
								"Nalwor",
								"Nalwor is an ancient elven city, built deep inside the forest to the southeast of us long before humans ever arrived on this island. Elves don't like mixing with other races, but we're given to understand that Nalwor was built to help defend their capital city of Teruykeh against an evil force.",
								new IncreaseXPAction(5));

				addReply(
								"Deniran",
								"The Empire's capital city of Deniran lies in the heart of Faiumoni, and is the main base of operations for the Deniran army. Most of the Empire's main trade routes with other countries originate in this city, then extending north through #Ados, and south to Sikhw. Unfortunately, the southern routes were been destroyed when Blordrough and his armies conquered Sikhw, some time ago now.",
								new IncreaseXPAction(5));

				addReply(
								"use",
								"Once you #get a map, there are three scales on which you need to understand it. Firstly, there are the map #levels, then you need to familiarize yourself with the #naming conventions for the different zones within those levels, and lastly you should learn how we describe a person's #positioning within a zone. We'll have you navigating around in no time!",
								new IncreaseXPAction(5));

				addReply(
								"levels",
								"Maps are split into levels according to the height of that particular area above or below the surface. Areas on the surface are at level 0. The level number is the first thing in a map's name. For instance, #Semos itself is at ground level, so it is level 0; its map is called \"0_semos_city\". The first level of the dungeon beneath us is at level -1, so its map is called \"-1_semos_dungeon\". You should note, though, that a map of a building's interior will usually have the level at the end of the name instead, with \"int\" (for \"interior\") at the start. For instance, the upstairs floor of the tavern would be mapped out as \"int_semos_tavern_1\".",
								new IncreaseXPAction(5));

				addReply(
								"naming",
								"Each map is usually split up into \"sets\" of zones, with a central feature that is used as a reference point. The zones surrounding this central zone are named by the direction in which they lie from it. For instance, from the central zone \"0_semos_city\", you can travel west to the old village at \"0_semos_village_w\", or you could travel two zones north and one west to the mountains at \"0_semos_mountain_n2_w\".",
								new IncreaseXPAction(5));

				addReply(
								"positioning",
								"Positioning within a zone is simply described in terms of co-ordinates. We conventionally take the top-left corner (that is, the northwest corner) to be the origin point with co-ordinates (0, 0). The first co-ordinate ('x') increases as you move to the right (that is, east) within the zone, and the second co-ordinate ('y') increases as you move down (that is, south).",
								new IncreaseXPAction(5));

				addReply(
								"get",
								"You can get a map of Stendhal at #https://stendhalgame.org/world/atlas.html if you want one. Careful you don't spoil any surprises for yourself, though!",
								new IncreaseXPAction(5));

				addReply(
								"SPS",
								"SPS stands for Stendhal Positioning System; you can ask #Io in the Temple about the exact details of how it works. Essentially, it allows you to ascertain the exact current location of yourself or your friends at any time.",
								new IncreaseXPAction(5));

				addReply(
								"Io",
								"Her full name is \"Io Flotto\". She spends most of her time in the Temple, um, floating. She may seem weird, but her \"intuition\" works far better than any mere compass, as I can vouch.",
								new IncreaseXPAction(5));

			}
		};

		npc.setEntityClass("wisemannpc");
		npc.setDescription("You see Zynn Iwuhos. He looks even older than some of his tattered maps lying around.");
		npc.setPosition(15, 3);
		npc.initHP(100);
		zone.add(npc);
	}
}
