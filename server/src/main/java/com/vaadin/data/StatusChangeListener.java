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
package com.vaadin.data;

import com.vaadin.event.SerializableEventListener;

/**
 * Listener interface for status change events from binder.
 *
 * @author Vaadin Ltd
 *
 * @since 8.0
 *
 * @see StatusChangeEvent
 * @see Binder#addStatusChangeListener(StatusChangeListener)
 */
@FunctionalInterface
public interface StatusChangeListener extends SerializableEventListener {

    /**
     * Notifies the listener about status change {@code event}.
     *
     * @param event
     *            a status change event, not null
     */
    void statusChange(StatusChangeEvent event);
}
