package chughes.jdev.trim.controller;

import chughes.jdev.trim.model.TextBufferHelper;
import chughes.jdev.trim.view.TrimMenuItem;
import chughes.jdev.trim.view.TrimMenuListener;
import chughes.jdev.trim.view.TrimUndoableEdit;
import chughes.jdev.util.IdeUtil;
import chughes.jdev.util.NodeUtil;
import chughes.jdev.util.StringBufferUtil;
import javax.swing.undo.UndoableEdit;
import oracle.ide.Addin;
import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.editor.EditorManager;
import oracle.ide.model.TextNode;
import oracle.javatools.buffer.LineMap;
import oracle.javatools.buffer.TextBuffer;

/*
 * @author Chris Hughes
 *
 * This class is the main entry point for the JDeveloper extension.
 *
 * The executeCommand and trimTextBuffer methods implement the actual
 * functionality, with the surrounding interface implementation providing
 * the entry points for JDeveloper.
 */
public class TrimController
    implements Addin, Controller
{
    public static final int TRIM_COMMAND =
        Ide.findOrCreateCmdID("chughes.jdev.TrimTrailing");

    /************************************************************************
     * Implement Addin interface
     ************************************************************************/
    public void initialize()
    {
        /*
         * Construct the JMenuItem, and associate it with our listener
         */
        TrimMenuListener listener =
            new TrimMenuListener(TrimMenuItem.getTrimMenuItem(this,
                                                              TRIM_COMMAND),
                                 TRIM_COMMAND);

        /*
         * Register our listener for ContextMenu information
         */
        EditorManager.getEditorManager().getContextMenu().addContextMenuListener(listener);
    }

    /************************************************************************
     * Implement Controller interface
     ************************************************************************/
    public boolean handleEvent(IdeAction action, Context context)
    {
        if (TRIM_COMMAND == action.getCommandId()) {
            return executeCommand(context);
        }
        return false;
    }

    public boolean update(final IdeAction action, final Context context)
    {
        return false;
    }

    /************************************************************************
     * Implement extension functionality
     ************************************************************************/
    /*
     * This method is the main entry-point for the extension functionality.
     *
     * To work in harmony with the TextBuffer interface it:
     *
     * (a) Obtains a write lock before performing any modification
     * (b) Marks the document as dirty if changes occur
     * (c) Always releases its write lock once modification is complete
     *
     */
    protected boolean executeCommand(final Context context)
    {
        boolean executed = false;
        if (context != null) {
            if (NodeUtil.isTextNode(context)) {
                TextNode node = (TextNode)context.getElement();
                try {
                    Ide.getWaitCursor().show();
                    TextBuffer buffer = node.acquireTextBuffer();
                    try {
                        buffer.writeLock();
                        if (trimTextBuffer(context, buffer)) {
                            node.markDirty(true);
                        }
                        executed = true;
                    }
                    finally {
                        buffer.writeUnlock();
                    }
                }
                finally {
                    node.releaseTextBuffer();
                    Ide.getWaitCursor().hide();
                }
            }
        }
        return executed;
    }

    /*
     * This method returns an boolean value which indicates if any lines have
     * been modified within the TextBuffer passed in.
     *
     * The logic sequence it follows being:
     *
     * (a) Obtains each line in the buffer - starting from the bottom and
     *     moving up to simplify undo/redo behaviour
     * (b) Checks each line for trailing spaces
     * (c) Removes the trailing spaces if present
     * (d) Places the change into a CompoundEdit
     *
     * The CompoundEdit, if populated, is then sent to the IDE for Undo/Redo.
     */
    protected boolean trimTextBuffer(final Context context,
                                     final TextBuffer textBuffer)
    {
        boolean updated = false;
        if (textBuffer != null) {
            LineMap lineMap = textBuffer.getLineMap();
            if (lineMap != null) {
                TrimUndoableEdit compoundEdit = new TrimUndoableEdit();
                int lineCount = lineMap.getLineCount();
                for (int i = (lineCount - 1); i >= 0; i--) {
                    TextBufferHelper helper = new TextBufferHelper(textBuffer);
                    StringBuffer line = helper.getLine(i);
                    int count =
                        StringBufferUtil.getCountTrailingWhitespace(line);
                    if (count > 0) {
                        UndoableEdit undo =
                            helper.removeTrailingChars(i, count);
                        if (undo != null) {
                            compoundEdit.addEdit(undo);
                            updated = true;
                        }
                    }
                }
                if (updated && (!compoundEdit.isEmpty())) {
                    compoundEdit.end();
                    IdeUtil.passUndo(context, this, compoundEdit);
                }
            }
        }
        return updated;
    }
}
