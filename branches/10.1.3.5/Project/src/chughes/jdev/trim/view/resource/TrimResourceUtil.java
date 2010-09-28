package chughes.jdev.trim.view.resource;

import java.util.ResourceBundle;

/*
 * @author Chris Hughes
 *
 * Utility class to provide static access to resource strings.
 */
public class TrimResourceUtil
{
    private static final ResourceBundle bundle =
        ResourceBundle.getBundle(TrimResourceKeys.TRIM_RESOURCES);

    public static String getString(final String key)
    {
        return bundle.getString(key);
    }
}
