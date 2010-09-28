package chughes.jdev.trim.view;

import chughes.jdev.trim.view.resource.TrimResourceKeys;
import chughes.jdev.trim.view.resource.TrimResourceUtil;
import javax.swing.undo.CompoundEdit;

/*
 * @author Chris Hughes
 *
 * This class wraps the CompoundEdit to provide presentation information
 * to the IDE.
 */
public class TrimUndoableEdit
    extends CompoundEdit
{
    /*
	 * Override to provide presentation information.
	 */
    public String getPresentationName()
    {
        return TrimResourceUtil.getString(TrimResourceKeys.TRIM_MENU_LABEL);
    }

    /*
	 * Returns a boolean that indicates if this CompoundEdit contains any
	 * UndoableEdit elements.
	 */
    public boolean isEmpty()
    {
        return (this.lastEdit() == null);
    }
}
