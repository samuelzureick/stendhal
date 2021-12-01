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
package games.stendhal.server.entity.item;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.status.StatusType;
import marauroa.common.game.RPObject;

/**
 * Represents everything that can be consumed by RPentity. Including food,
 * poison, antidote, ...
 *
 * Note: this class has a natural ordering that is inconsistent with equals.
 */
public class RestItem extends StackableItem implements Comparable<RestItem> {

	private final static Logger logger = Logger.getLogger(RestItem.class);

	/** How much of this item has not yet been consumed. */

	@Override
	public void put(final String attribute, final double value) {
		super.put(attribute, value);
	}

	@Override
	public void put(final String attribute, final int value) {
		super.put(attribute, value);
	}

	@Override
	public void put(final String attribute, final String value) {
		super.put(attribute, value);
	}

	public RestItem(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);

	}

	/**
	 * copy constructor.
	 *
	 * @param item
	 *            item to copy
	 */
	public RestItem(final RestItem item) {
		super(item);
	}

	public int getAmount() {
		return getInt("amount");
	}

	public int getFrecuency() {
		return getInt("frequency");
	}

	public int getRegen() {
		return getInt("regen");
	}

	/**
	 * Sleeps
	 *
	 * @return The regen gained from sleep
	 */
	public int sleep() {
		int restBenefit = getRegen();
		return restBenefit;
	}

	/**
	 * Verifies item is near to player. if so splits one single item of and
	 * calls consumeItem of the player.
	 * @param user the eating player
	 * @return true if consumption can be started
	 */
	@Override
	public boolean onUsed(final RPEntity user) {
		if (user instanceof Player) {
			final Player player = (Player) user;

			if (isContained()) {
				// We modify the base container if the object change.
				RPObject base = getContainer();

				while (base.isContained()) {
					base = base.getContainer();
				}

				if (!user.nextTo((Entity) base)) {
					user.sendPrivateText("You can't rest from that far away!");
					return false;
				}
			} else {
				if (!nextTo(user)) {
					user.sendPrivateText("You can't rest from that far away!");
					return false;
				}
			}
			player.notifyWorldAboutChanges();
			return true;
		} else {
			logger.error("user is no instance of Player but: " + user, new Throwable());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final RestItem other) {

		final float result = (float) other.getRegen() / (float) other.getFrecuency()
				- (float) getRegen() / (float) getFrecuency();
		return (int) Math.signum(result);
	}

	/*
	 * Sub-classes that use immunizations should override this.
	 */
	public Set<StatusType> getImmunizations() {
		return Collections.emptySet();
	}
}
