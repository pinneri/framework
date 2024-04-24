/*
 * Copyright 2000-2022 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.v7.client.connectors;

import com.vaadin.client.ServerConnector;
import com.vaadin.v7.client.renderers.Renderer;
import com.vaadin.v7.client.widgets.Grid.Column;

import elemental.json.JsonObject;

/**
 * An abstract base class for renderer connectors. A renderer connector is used
 * to link a client-side {@link Renderer} to a server-side
 * {@link com.vaadin.ui.components.grid.Renderer Renderer}. As a connector, it
 * can use the regular Vaadin RPC and shared state mechanism to pass additional
 * state and information between the client and the server. This base class
 * itself only uses the basic {@link com.vaadin.shared.communication.SharedState
 * SharedState} and no RPC interfaces.
 *
 * @param <T>
 *            the presentation type of the renderer
 *
 * @since 7.4
 * @author Vaadin Ltd
 */
public abstract class AbstractGridRendererConnector<T>
        extends AbstractRendererConnector<T> {

    /**
     * Gets the row key for a row object.
     * <p>
     * In case this renderer wants be able to identify a row in such a way that
     * the server also understands it, the row key is used for that. Rows are
     * identified by unified keys between the client and the server.
     *
     * @param row
     *            the row object
     * @return the row key for the given row
     */
    protected String getRowKey(JsonObject row) {
        final ServerConnector parent = getParent();
        if (parent instanceof GridConnector) {
            return ((GridConnector) parent).getRowKey(row);
        } else {
            throw new IllegalStateException(
                    "Renderers can only be used " + "with a Grid.");
        }
    }

    /**
     * Gets the column id for a column.
     * <p>
     * In case this renderer wants be able to identify a column in such a way
     * that the server also understands it, the column id is used for that.
     * Columns are identified by unified ids between the client and the server.
     *
     * @param column
     *            the column object
     * @return the column id for the given column
     */
    protected String getColumnId(Column<?, JsonObject> column) {
        final ServerConnector parent = getParent();
        if (parent instanceof GridConnector) {
            return ((GridConnector) parent).getColumnId(column);
        } else {
            throw new IllegalStateException(
                    "Renderers can only be used " + "with a Grid.");
        }
    }

}
