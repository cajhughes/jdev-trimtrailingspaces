package chughes.jdev.trim.view;

import chughes.jdev.trim.view.resource.TrimResourceKeys;
import chughes.jdev.trim.view.resource.TrimResourceUtil;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.util.GraphicsUtils;

/*
 * @author Chris Hughes
 *
 * This utility class contains a static helper method which constructs an
 * IdeAction which is referenced from both the menu & keyboard listeners.
 */
public final class TrimIdeAction
{
    private static IdeAction trimAction = null;

    public static final IdeAction get(final Controller controller, final int commandId)
    {
        if (trimAction == null) {
            String menuLabel =
                TrimResourceUtil.getString(TrimResourceKeys.TRIM_MENU_LABEL);
            Icon icon =
                new ImageIcon(
                    GraphicsUtils.loadFromResource(
                        TrimResourceUtil.getString(TrimResourceKeys.TRIM_MENU_ICON),
                        controller.getClass()));
            trimAction = IdeAction.get(commandId,
                                       null,
                                       menuLabel,
                                       "Code Editor",
                                       null,
                                       icon,
                                       null,
                                       true);
            trimAction.addController(controller);
        }
        return trimAction;
    }
}
