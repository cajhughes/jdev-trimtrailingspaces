package chughes.jdev.util;

import chughes.jdev.trim.view.TrimIdeAction;
import chughes.jdev.trim.view.TrimKeyStrokeContext;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import oracle.ide.controller.Controller;
import oracle.ide.keyboard.KeyStrokeContext;
import oracle.ide.keyboard.KeyStrokes;

/*
 * @author Chris Hughes
 *
 * This utility class contains a static helper method to associate the command
 * with our keyboard accelerator.
 */
public final class KeyStrokeUtil
{
    private static final KeyStroke accelerator =
        KeyStroke.getKeyStroke(KeyEvent.VK_T,
                               KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK);

    public static KeyStrokeContext getKeyStrokeContext(final Controller controller, final int commandId)
    {
        TrimKeyStrokeContext context = new TrimKeyStrokeContext();
        context.add(TrimIdeAction.get(controller, commandId), new KeyStrokes(accelerator));
        return context;
    }
}
