/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License") +  you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.openmeetings.web.room.sidebar.icon.activity;

import static org.apache.openmeetings.web.room.sidebar.RoomSidebar.FUNC_TOGGLE_ACTIVITY;
import static org.apache.openmeetings.web.room.sidebar.RoomSidebar.activityAllowed;

import org.apache.openmeetings.db.entity.basic.Client.Activity;
import org.apache.openmeetings.db.entity.basic.Client.Pod;
import org.apache.openmeetings.db.entity.room.Room;
import org.apache.openmeetings.web.room.RoomPanel;
import org.apache.openmeetings.web.room.sidebar.icon.ClientIcon;

public abstract class RoomActivityIcon extends ClientIcon {
	private static final long serialVersionUID = 1L;
	private static final String CLS_ENABLED = "enabled ";
	private static final String CLS_DISABLED = "disabled";
	protected final Activity activity;
	protected final Pod pod = Pod.none;

	public RoomActivityIcon(String id, String uid, Activity activity, RoomPanel room) {
		super(id, uid, room);
		this.activity = activity;
	}

	@Override
	protected String getScript() {
		return String.format("%s('%s', '%s', '%s');", FUNC_TOGGLE_ACTIVITY, activity.name(), uid, pod.name());
	}

	protected boolean visible() {
		return Room.Type.interview != room.getRoom().getType()
				&& activityAllowed(getClient(), activity, room.getRoom());
	}

	@Override
	protected boolean isClickable() {
		return isEnabled();
	}

	@Override
	protected void internalUpdate() {
		setVisible(visible());
		if (!isEnabled()) {
			cssClass.append(CLS_DISABLED);
		} else if (getClient().hasActivity(activity)) {
			cssClass.append(CLS_ENABLED);
		}
	}
}
