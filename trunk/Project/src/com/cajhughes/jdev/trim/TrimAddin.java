package com.cajhughes.jdev.trim;

import com.cajhughes.jdev.trim.view.TrimKeyStrokeContext;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import oracle.ide.Addin;
import oracle.ide.Ide;
import oracle.ide.controller.IdeAction;
import oracle.ide.keyboard.KeyStrokes;

/**
 * This class implements the Addin interface, and exists to perform the
 * initialization required for the TrimTrailing extension to work correctly.
 *
 * (a) It registers a default shortcut key for the action
 *
 * @author Chris Hughes
 */
public final class TrimAddin implements Addin {
    private static final KeyStroke trimShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_DOWN_MASK);

    @Override
    public void initialize() {
        TrimKeyStrokeContext trimContext = new TrimKeyStrokeContext();
        trimContext.add(IdeAction.find(TrimCommand.actionId()), new KeyStrokes(trimShortcut));
        Ide.getKeyStrokeContextRegistry().addContext(trimContext);
    }
}
