package com.cajhughes.jdev.trim.view;

import com.cajhughes.jdev.trim.TrimCommand;

import oracle.ide.Context;
import oracle.ide.controller.ContextMenu;
import oracle.ide.controller.ContextMenuListener;
import oracle.ide.controller.IdeAction;

public final class TrimMenuListener implements ContextMenuListener {
    public void menuWillShow(ContextMenu contextMenu) {
        contextMenu.add(contextMenu.createMenuItem(IdeAction.find(TrimCommand.actionId())));
    }

    public void menuWillHide(ContextMenu contextMenu) {
    }

    public boolean handleDefaultAction(Context context) {
        return false;
    }
}
