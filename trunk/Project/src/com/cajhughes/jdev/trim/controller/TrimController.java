package com.cajhughes.jdev.trim.controller;

import oracle.ide.Context;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;

public final class TrimController implements Controller {
    public boolean handleEvent(IdeAction ideAction, Context context) {
        return false;
    }

    public boolean update(IdeAction ideAction, Context context) {
        return true;
    }
}
