package org.rapidoid.net.impl;

/*
 * #%L
 * rapidoid-net
 * %%
 * Copyright (C) 2014 - 2017 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.net.abstracts.Channel;
import org.rapidoid.net.abstracts.ChannelHolder;

import java.util.concurrent.atomic.AtomicLong;

@Authors("Nikolche Mihajlovski")
@Since("NET_EXTRAS")
public class ChannelHolderImpl implements ChannelHolder {

	private static final AtomicLong COUNTER = new AtomicLong();

	private volatile Channel channel;

	private final long id;

	public ChannelHolderImpl() {
		this.id = COUNTER.incrementAndGet();
	}

	public ChannelHolderImpl(Channel channel) {
		this();
		this.channel = channel;
	}

	@Override
	public long id() {
		return id;
	}

	@Override
	public synchronized Channel channel() {
		return channel;
	}

	public synchronized void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "#" + id + ":" + channel;
	}

	public synchronized void closed() {
		setChannel(null);
	}

}
