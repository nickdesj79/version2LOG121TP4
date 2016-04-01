package principale;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: LangueConfig.java
Date cr√©√©: 2013-05-03
 *******************************************************
 * Ce code est bas√© d'un exemple sur Internet, de David Geary, √  la page suivante:
 * http://www.javaworld.com/javaworld/jw-05-2003/jw-0530-designpatterns-p2.html
Historique des modifications
 *******************************************************
2013-05-03 Version initiale
Provient du laboratoire2
 *******************************************************/

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utilitaire pour obtenir un mot dans la langue de l'application
 */
public class LangueConfig {

	static private final String PREFS_BUNDLE_BASENAME = "prefs"; // NOPMD by Gabriel on 13-10-17 19:11
	static private final String BUNDLE_BASENAME = "app",
			PREFERRED_LOCALE_KEY = "locale"; // NOPMD by Gabriel on 13-10-17 19:11
	private static ResourceBundle preferences, resources;
	static private Locale locale;

	static {
		try {
			preferences = ResourceBundle.getBundle(PREFS_BUNDLE_BASENAME);
			locale = new Locale(preferences.getString(PREFERRED_LOCALE_KEY));
		} catch (java.util.MissingResourceException ex) {
			System.err
					.println("ERROR: cannot find preferences properties file "
							+ BUNDLE_BASENAME);
		}
		try {
			resources = ResourceBundle.getBundle(BUNDLE_BASENAME, locale);
		} catch (java.util.MissingResourceException ex) {
			System.err.println("ERROR: cannot find properties file for "
					+ BUNDLE_BASENAME);
		}
	};

	/**
	 * Retourne un mot (dans la langue de l'application) liÈ ‡ un champs
	 * 
	 * @param key
	 *            champs
	 * @return
	 */
	public static String getResource(String key) {
		return (resources == null) ? null : resources.getString(key);
	}
}