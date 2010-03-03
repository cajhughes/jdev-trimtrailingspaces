package com.cajhughes.jdev.trim.controller;

import com.cajhughes.jdev.util.NodeUtil;
import oracle.ide.Context;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;

/**
 * This class implements the Controller interface, and exists to update the
 * enabled status of the TrimTrailing action based on:
 *
 * (a) The document currently being edited is a text document
 * (b) The document currently being edited is editable
 *
 * @author Chris Hughes
 */
public final class TrimController implements Controller {
    @Override
    public boolean handleEvent(final IdeAction ideAction, final Context context) {
        return false;
    }

    @Override
    public boolean update(final IdeAction ideAction, final Context context) {
        ideAction.setEnabled(NodeUtil.isEditableTextNode(context));
        return true;
    }
}
