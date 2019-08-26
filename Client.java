import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Client {

	public static void main(String[] args) throws MalformedURLException, IOException, NoSuchAlgorithmException {
		String customerKey = "PUT_YOUR_CUSTOMER_KEY_HERE";
		String secretPhrase = "";//leave secret phrase empty, if not needed

		ScreenshoMachine sm = new ScreenshoMachine(customerKey, secretPhrase);

		Map<String, String> options = new HashMap<String, String>();
		// mandatory parameter
		options.put("url", "https://www.google.com");
		// all next parameters are optional, see our website screenshot API guide for more details
		options.put("dimension", "1366x768"); // or "1366xfull" for full length screenshot
		options.put("device", "desktop");
		options.put("format", "png");
		options.put("cacheLimit", "0");
		options.put("delay", "200");
		options.put("zoom", "100");

		String apiUrl = sm.generateScreenshotApiUrl(options);
		// put link to your html code
		System.out.println(apiUrl);

		// or save screenshot as an image
        URLConnection openConnection = new URL(apiUrl).openConnection();
        openConnection.addRequestProperty("User-Agent", "Mozilla/4.0");
        InputStream in = openConnection.getInputStream();
		String output = "out.png";
		Files.copy(in, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("Screenshot saved as " + output);
	}

}