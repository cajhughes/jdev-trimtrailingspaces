package com.cajhughes.jdev.trim;

import com.cajhughes.jdev.trim.model.TextBufferHelper;
import com.cajhughes.jdev.trim.view.resource.TrimResourceUtil;
import com.cajhughes.jdev.util.NodeUtil;
import com.cajhughes.jdev.util.StringBufferUtil;
import javax.swing.undo.UndoableEdit;
import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.controller.Command;
import oracle.ide.model.Node;
import oracle.ide.model.TextNode;
import oracle.javatools.buffer.LineMap;
import oracle.javatools.buffer.TextBuffer;

public final class TrimCommand extends Command {
    private static final Node[] affectedNodes = {};
    private static final String EXTENSION_ID = "com.cajhughes.jdev.TrimTrailing";
    private static final String EXTENSION_NAME = TrimResourceUtil.getString("EXTENSION_NAME");

    private UndoableEdit undo = null;

    public TrimCommand() {
        super(actionId(), NORMAL);
    }

    public static int actionId() {
        Integer cmdId = Ide.findOrCreateCmdID(EXTENSION_ID);
        if (cmdId == null) {
            throw new IllegalStateException("Action, " + EXTENSION_ID + ", not found.");
        }
        else {
            return cmdId.intValue();
        }
    }

    public int doit() throws Exception {
        Context context = getContext();
        if (context != null) {
            if (NodeUtil.isEditableTextNode(context)) {
                TextNode node = (TextNode)context.getNode();
                try {
                    Ide.getWaitCursor().show();
                    TextBuffer buffer = node.acquireTextBuffer();
                    try {
                        buffer.writeLock();
                        if (trimTextBuffer(buffer)) {
                            node.markDirty(true);
                        }
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
        return OK;
    }

    public Node[] getAffectedNodes() {
        return affectedNodes;
    }

    public String getName() {
        return EXTENSION_NAME;
    }

    public int getType() {
        return NORMAL;
    }

    public int undo() {
        if (undo != null) {
            Context context = getContext();
            if (context != null) {
                if (NodeUtil.isEditableTextNode(context)) {
                    TextNode node = (TextNode)context.getNode();
                    try {
                        Ide.getWaitCursor().show();
                        TextBuffer buffer = node.acquireTextBuffer();
                        try {
                            buffer.writeLock();
                            undo.undo();
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
        }
        return OK;
    }

    private boolean trimTextBuffer(final TextBuffer textBuffer) {
        boolean updated = false;
        if (textBuffer != null) {
            TextBufferHelper helper = new TextBufferHelper(textBuffer);
            LineMap lineMap = textBuffer.getLineMap();
            if (lineMap != null) {
                textBuffer.beginEdit();
                int lineCount = lineMap.getLineCount();
                for (int i = (lineCount - 1); i >= 0; i--) {
                    StringBuffer line = helper.getLine(i);
                    int count = StringBufferUtil.getCountTrailingWhitespace(line);
                    if (count > 0) {
                        helper.removeTrailingChars(i, count);
                        updated = true;
                    }
                }
                undo = textBuffer.endEdit();
            }
        }
        return updated;
    }
}
