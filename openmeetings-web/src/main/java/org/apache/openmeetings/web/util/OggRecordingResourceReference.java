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
package org.apache.openmeetings.web.util;

import static org.apache.openmeetings.util.OmFileHelper.OGG_EXTENSION;
import static org.apache.openmeetings.util.OmFileHelper.getOggRecording;

import java.io.File;

import org.apache.openmeetings.db.entity.record.Recording;

public class OggRecordingResourceReference extends RecordingResourceReference {
	private static final long serialVersionUID = 1L;

	public OggRecordingResourceReference() {
		super("ogg-recording");
	}
	
	@Override
	public String getMimeType() {
		return "video/ogg";
	}
	
	@Override
	String getFileName(Recording r) {
		return r.getHash() + OGG_EXTENSION;
	}
	
	@Override
	File getFile(Recording r) {
		return getOggRecording(r.getHash());
	}
}