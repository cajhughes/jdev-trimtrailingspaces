package chughes.jdev.trim.view;

import chughes.jdev.util.NodeUtil;
import javax.swing.JMenuItem;
import oracle.ide.Context;
import oracle.ide.controller.ContextMenu;
import oracle.ide.controller.ContextMenuListener;
import oracle.ide.editor.EditorManager;

/*
 * @author Chris Hughes
 *
 * Implement the ContextMenuListener interface to include the additional
 * menu item within the context menu
 */
public final class TrimMenuListener
    implements ContextMenuListener
{
    private JMenuItem menuItem;
    public int commandId;

    public TrimMenuListener(final JMenuItem menuItem, final int commandId)
    {
        this.menuItem = menuItem;
        this.commandId = commandId;
    }

    public boolean handleDefaultAction(final Context context)
    {
        /*
         * We do not wish to be the default action, so return false
         */
        return false;
    }

    public void menuWillHide(final ContextMenu popup)
    {
        /*
         * Nothing to do at menu destruction time
         */
    }

    public void menuWillShow(final ContextMenu popup)
    {
        Context context = popup.getContext();
        if (context != null) {
            if (popup == EditorManager.getEditorManager().getContextMenu()) {
                if (NodeUtil.isTextNode(context)) {
                    popup.add(menuItem);
                }
            }
        }
    }
}
