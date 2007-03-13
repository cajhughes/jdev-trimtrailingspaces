package chughes.jdev.trim.view;

import chughes.jdev.trim.view.resource.TrimResourceKeys;
import chughes.jdev.trim.view.resource.TrimResourceUtil;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import oracle.ide.Ide;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.util.GraphicsUtils;

/*
 * @author Chris Hughes
 *
 * This utility class contains static helper methods, such that the addin code
 * is not obscured with implementation detail.
 */
public final class TrimMenuItem
{
    public static final JMenuItem getTrimMenuItem(final Controller controller,
                                                  final int commandId)
    {
        JMenuItem menuItem = null;
        menuItem = Ide.getMenubar().createMenuItem(TrimIdeAction.get(controller, commandId));
        return menuItem;
    }
}
