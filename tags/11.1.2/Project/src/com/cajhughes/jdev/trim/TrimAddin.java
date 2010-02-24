package com.cajhughes.jdev.trim;

import com.cajhughes.jdev.trim.view.TrimKeyStrokeContext;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import oracle.ide.Addin;
import oracle.ide.Ide;
import oracle.ide.controller.IdeAction;
import oracle.ide.keyboard.KeyStrokes;

public final class TrimAddin implements Addin {
    private static final KeyStroke trimShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_DOWN_MASK);

    public void initialize() {
        TrimKeyStrokeContext trimContext = new TrimKeyStrokeContext();
        trimContext.add(IdeAction.find(TrimCommand.actionId()), new KeyStrokes(trimShortcut));
        Ide.getKeyStrokeContextRegistry().addContext(trimContext);
    }
}
