package com.cajhughes.jdev.trim.controller;

import com.cajhughes.jdev.util.NodeUtil;
import oracle.ide.Context;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;

public final class TrimController implements Controller {
    public boolean handleEvent(final IdeAction ideAction, final Context context) {
        return false;
    }

    public boolean update(final IdeAction ideAction, final Context context) {
        ideAction.setEnabled(NodeUtil.isEditableTextNode(context));
        return true;
    }
}
