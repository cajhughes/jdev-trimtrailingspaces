package com.cajhughes.jdev.trim.view;

import com.cajhughes.jdev.trim.TrimCommand;

import oracle.ide.Context;
import oracle.ide.controller.ContextMenu;
import oracle.ide.controller.ContextMenuListener;
import oracle.ide.controller.IdeAction;

/**
 * This class implements the ContextMenuListener interface, and exists to add
 * the TrimTrailing action to the context menu within the CodeEditor.
 *
 * @author Chris Hughes
 */
public final class TrimMenuListener implements ContextMenuListener {
    @Override
    public boolean handleDefaultAction(Context context) {
        return false;
    }

    @Override
    public void menuWillShow(ContextMenu contextMenu) {
        contextMenu.add(contextMenu.createMenuItem(IdeAction.find(TrimCommand.actionId())));
    }

    @Override
    public void menuWillHide(ContextMenu contextMenu) {
    }
}
