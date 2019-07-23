import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

public class ScreenshoMachine {

	private String customerKey;
	private String secretPhrase;
	private String apiBaseUrl = "http://api.screenshotmachine.com/?";

	public ScreenshoMachine(String customerKey, String secretPhrase) {
		this.customerKey = customerKey;
		this.secretPhrase = secretPhrase;
	}

	public String generateApiUrl(Map<String, String> options) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		StringBuilder apiUrl = new StringBuilder(apiBaseUrl);
		apiUrl.append("key=").append(customerKey);
		if (secretPhrase != null && secretPhrase.trim().length() > 0) {
			apiUrl.append("&hash=").append(calculateHash(options.get("url") + secretPhrase));
		}
		for (String key : options.keySet()) {
			apiUrl.append("&").append(key).append("=").append(URLEncoder.encode(options.get(key), StandardCharsets.UTF_8.toString()));
		}
		return apiUrl.toString();
	}

	private String calculateHash(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(text.getBytes());
		return DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
	}

}