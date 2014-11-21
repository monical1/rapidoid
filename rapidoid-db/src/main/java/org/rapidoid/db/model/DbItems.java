package org.rapidoid.db.model;

/*
 * #%L
 * rapidoid-db
 * %%
 * Copyright (C) 2014 Nikolche Mihajlovski
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

import java.util.List;

import org.rapidoid.db.DB;
import org.rapidoid.model.Item;
import org.rapidoid.model.Model;
import org.rapidoid.model.impl.BeanListItems;
import org.rapidoid.util.U;

@SuppressWarnings("serial")
public class DbItems<T> extends BeanListItems<T> {

	public DbItems(Class<T> type) {
		super(type);
	}

	@Override
	public void add(Item item) {
		DB.insert(item.value());
	}

	@Override
	public void insert(int index, Item item) {
		throw U.notSupported();
	}

	@Override
	public Item get(int index) {
		return data().get(index);
	}

	@Override
	protected List<Item> data() {
		List<T> all = DB.getAll(beanType);
		List<Item> records = U.list();

		for (T t : all) {
			records.add(Model.item(t));
		}

		return records;
	}

	@Override
	public void remove(int index) {
		DB.delete(idOf(index));
	}

	@Override
	public void set(int index, Item item) {
		DB.update(idOf(index), item.value());
	}

}
