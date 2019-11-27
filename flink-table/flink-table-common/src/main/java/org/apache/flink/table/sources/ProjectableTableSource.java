/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.sources;

import org.apache.flink.annotation.PublicEvolving;

/**
 * Adds support for projection push-down to a {@link TableSource}.
 *
 * <p>A {@link TableSource} extending this interface is able to project the fields of the returned
 * {@code DataSet} if it is a {@code BatchTableSource} or {@code DataStream} if it is a
 * {@code StreamTableSource}.
 *
 * @param <T> The return type of the {@link TableSource}.
 */
@PublicEvolving
public interface ProjectableTableSource<T> {

	/**
	 * Creates a copy of the {@link TableSource} that projects its output to the given field indexes.
	 * The field indexes relate to the physical return type ({@link TableSource#getReturnType}) and not
	 * to the table schema ({@link TableSource#getTableSchema} of the {@link TableSource}.
	 *
	 * <p>The table schema ({@link TableSource#getTableSchema} of the {@link TableSource} copy must not be
	 * modified by this method, but only the return type ({@link TableSource#getReturnType}) and the
	 * produced {@code DataSet} ({@code BatchTableSource#getDataSet(}) or {@code DataStream}
	 * ({@code StreamTableSource#getDataStream}).
	 *
	 * <p>If the {@link TableSource} implements the {@link DefinedFieldMapping} interface, it might
	 * be necessary to adjust the mapping as well.
	 *
	 * <p>IMPORTANT: This method must return a true copy and must not modify the original table
	 * source object.
	 *
	 * @param fields The indexes of the fields to return.
	 * @return A copy of the {@link TableSource} that projects its output.
	 */
	TableSource<T> projectFields(int[] fields);
}
