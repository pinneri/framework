/*
@VaadinApache2LicenseForJavaFiles@
 */
package com.vaadin.terminal.gwt.client.ui.layout;

import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.ComponentState;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VAbstractPaintableWidgetContainer;
import com.vaadin.terminal.gwt.client.ui.VMarginInfo;

public abstract class CellBasedLayoutPaintable extends
        VAbstractPaintableWidgetContainer {

    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        getWidgetForPaintable().client = client;

        if (isRealUpdate(uidl)) {
            /**
             * Margin and spacing detection depends on classNames and must be
             * set before setting size. Here just update the details from UIDL
             * and from overridden setStyleName run actual margin detections.
             */
            updateMarginAndSpacingInfo(uidl);
        }

        /*
         * This call should be made first. Ensure correct implementation, handle
         * size etc.
         */
        super.updateFromUIDL(uidl, client);

        if (isRealUpdate(uidl)) {
            handleDynamicDimensions();
        }
    }

    private void handleDynamicDimensions() {
        String w = "";
        String h = "";
        if (null != getState()) {
            if (getState().getState().containsKey(ComponentState.STATE_WIDTH)) {
                w = String.valueOf(getState().getState().get(
                        ComponentState.STATE_WIDTH));
            }
            if (getState().getState().containsKey(ComponentState.STATE_HEIGHT)) {
                h = String.valueOf(getState().getState().get(
                        ComponentState.STATE_HEIGHT));
            }
        }

        if (w.equals("")) {
            getWidgetForPaintable().dynamicWidth = true;
        } else {
            getWidgetForPaintable().dynamicWidth = false;
        }

        if (h.equals("")) {
            getWidgetForPaintable().dynamicHeight = true;
        } else {
            getWidgetForPaintable().dynamicHeight = false;
        }

    }

    void updateMarginAndSpacingInfo(UIDL uidl) {
        int bitMask = uidl.getIntAttribute("margins");
        if (getWidgetForPaintable().activeMarginsInfo.getBitMask() != bitMask) {
            getWidgetForPaintable().activeMarginsInfo = new VMarginInfo(bitMask);
            getWidgetForPaintable().marginsNeedsRecalculation = true;
        }
        boolean spacing = uidl.getBooleanAttribute("spacing");
        if (spacing != getWidgetForPaintable().spacingEnabled) {
            getWidgetForPaintable().marginsNeedsRecalculation = true;
            getWidgetForPaintable().spacingEnabled = spacing;
        }
    }

    @Override
    protected abstract CellBasedLayout createWidget();

    @Override
    public CellBasedLayout getWidgetForPaintable() {
        return (CellBasedLayout) super.getWidgetForPaintable();
    }
}
