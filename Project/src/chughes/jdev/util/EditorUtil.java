package chughes.jdev.util;

import oracle.ide.Context;
import oracle.ide.ceditor.CodeEditor;
import oracle.ide.ceditor.find.FindableEditor;
import oracle.ide.view.View;
import oracle.javatools.editor.BasicEditorPane;

/*
 * @author Chris Hughes
 *
 * This class provides helper methods for interacting with JDeveloper editors.
 */
public final class EditorUtil
{
    /*
     * Determines the focussed pane, if an editor has focus within the IDE.
     */
    public static BasicEditorPane getBasicEditorPane(final Context context)
    {
        BasicEditorPane pane = null;
        if (context != null) {
            View view = context.getView();
            if (view != null && view instanceof FindableEditor) {
                FindableEditor findable = (FindableEditor)view;
                pane = findable.getFocusedEditorPane();
            }
        }
        return pane;
    }

    /*
     * Determines the CodeEditor instance associated with the current editor
     * within the IDE.
     */
    public static CodeEditor getCodeEditor(final Context context)
    {
        CodeEditor codeEditor = null;
        if (context != null) {
            BasicEditorPane pane = getBasicEditorPane(context);
            if (pane != null) {
                codeEditor = CodeEditor.getCodeEditor(pane);
            }
        }
        return codeEditor;
    }
}
