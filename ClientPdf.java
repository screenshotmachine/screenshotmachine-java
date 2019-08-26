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

public class ClientPdf {

	public static void main(String[] args) throws MalformedURLException, IOException, NoSuchAlgorithmException {
		String customerKey = "000000";
		String secretPhrase = "";//leave secret phrase empty, if not needed

		ScreenshoMachine sm = new ScreenshoMachine(customerKey, secretPhrase);

		Map<String, String> options = new HashMap<String, String>();
		// mandatory parameter
		options.put("url", "https://www.google.com");
		// all next parameters are optional, see our website to PDF API guide for more details
		options.put("paper", "letter");
		options.put("orientation", "portrait");
		options.put("media", "print");
		options.put("bg", "nobg");
		options.put("delay", "2000");
		options.put("scale", "50");

		String pdfApiUrl = sm.generatePdfApiUrl(options);

		//save PDF file
		URLConnection openConnection = new URL(pdfApiUrl).openConnection();
		openConnection.addRequestProperty("User-Agent", "Mozilla/4.0");
		InputStream in = openConnection.getInputStream();
		String output = "out.pdf";
		Files.copy(in, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("Pdf saved as " + output);

	}

}