package chughes.jdev.util;

import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoableEdit;
import oracle.ide.Context;
import oracle.ide.ceditor.CodeEditor;

public final class IdeUtil
{
    /*
     * Helper method for passing an UndoableEdit on to the IDE.
     */
    public static void passUndo(final Context      context,
                                final Object       source,
                                final UndoableEdit undo)
    {
        if (context != null) {
            CodeEditor codeEditor = EditorUtil.getCodeEditor(context);
            if (codeEditor != null) {
                UndoableEditEvent event = new UndoableEditEvent(source, undo);
                codeEditor.undoableEditHappened(event);
            }
        }
    }
}
