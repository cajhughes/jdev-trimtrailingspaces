package chughes.jdev.util;

/*
 * @author Chris Hughes
 *
 * This class provides helper methods for the StringBuffer class.
 */
public class StringBufferUtil
{
    /*
     * Given a StringBuffer, returns the number of trailing characters which
     * are considered to be whitespace (as defined by the Character class).
     */
    public static int getCountTrailingWhitespace(final StringBuffer lineBuffer)
    {
        int count = 0;
        if (lineBuffer != null) {
            int length = lineBuffer.length();
            if (length > 0) {
                int index = (length - 1);
                char c = lineBuffer.charAt(index);
                while (index >= 0 && Character.isWhitespace(c)) {
                    count++;
                    index--;
                    if (index >= 0) {
                        c = lineBuffer.charAt(index);
                    }
                }
            }
        }
        return count;
    }
}
