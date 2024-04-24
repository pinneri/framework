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
package com.vaadin.client.renderers;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Button;
import com.vaadin.client.widget.grid.RendererCellReference;

/**
 * A Renderer that displays buttons with textual captions. The values of the
 * corresponding column are used as the captions. Click handlers can be added to
 * the renderer, invoked when any of the rendered buttons is clicked.
 *
 * @since 7.4
 * @author Vaadin Ltd
 */
public class ButtonRenderer extends ClickableRenderer<String, Button> {

    private boolean htmlContentAllowed = false;

    @Override
    public Button createWidget() {
        Button b = GWT.create(Button.class);
        b.addClickHandler(this);
        b.setStylePrimaryName("v-nativebutton");
        return b;
    }

    /**
     * Sets whether HTML content is allowed.
     *
     * @param htmlContentAllowed
     *            {@code true} if HTML content allowed, {@code false} if
     *            contents should be rendered as plain text
     */
    public void setHtmlContentAllowed(boolean htmlContentAllowed) {
        this.htmlContentAllowed = htmlContentAllowed;
    }

    /**
     * Returns whether HTML content is allowed.
     *
     * @return {@code true} if HTML content allowed, {@code false} if contents
     *         are rendered as plain text
     */
    public boolean isHtmlContentAllowed() {
        return htmlContentAllowed;
    }

    @Override
    public void render(RendererCellReference cell, String text, Button button) {
        if (htmlContentAllowed) {
            button.setHTML(text);
        } else {
            button.setText(text);
        }
    }
}
