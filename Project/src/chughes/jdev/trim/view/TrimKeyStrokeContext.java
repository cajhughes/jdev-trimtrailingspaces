package chughes.jdev.trim.view;

import chughes.jdev.trim.view.resource.TrimResourceKeys;
import chughes.jdev.trim.view.resource.TrimResourceUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oracle.ide.controller.IdeAction;
import oracle.ide.keyboard.KeyStrokeContext;
import oracle.ide.keyboard.KeyStrokeMap;
import oracle.ide.keyboard.KeyStrokes;

/*
 * @author Chris Hughes
 *
 * This utility class implements the KeyStrokeContext interface, so that
 * we can define an accelerator for any command.
 */
public class TrimKeyStrokeContext implements KeyStrokeContext
{
    private final Set<IdeAction> actions = new HashSet<IdeAction>();
    private final KeyStrokeMap keyStrokeMap = new KeyStrokeMap();

    public void add(final IdeAction action, KeyStrokes keyStrokes)
    {
        actions.add(action);
        keyStrokeMap.put(keyStrokes, action.getCommandId());
    }

    public String getAcceleratorFile()
    {
        return null;
    }

    public Set getAllActions(boolean global)
    {
        if (global) {
            return actions;
        }
        else {
            return null;
        }
    }

    public List getAllPresets()
    {
        return null;
    }

    public String getName()
    {
        return TrimResourceUtil.getString(TrimResourceKeys.TRIM_ADDIN_NAME);
    }

    public KeyStrokeMap getPresetKeyStrokeMap(Object object, boolean global)
    {
        if (global) {
            return keyStrokeMap;
        }
        else {
            return null;
        }
    }
}
