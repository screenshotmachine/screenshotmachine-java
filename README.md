# screenshotmachine-java

This demo shows how to call online screenshot machine API using java programming language.

## Installation
First, you need to create a free/premium account at [www.screenshotmachine.com](https://www.screenshotmachine.com) website. After registration, you will see your customer key in your user profile. Also secret phrase is maintained here. Please, use secret phrase always, when your API calls are called from publicly available websites.  

Set-up your customer key and secret phrase (if needed) in the script:

```java
    String customerKey = "PUT_YOUR_CUSTOMER_KEY_HERE";
    String secretPhrase = "";//leave secret phrase empty, if not needed
```

Set other options to fulfill your needs: 

```java
    Map<String, String> options = new HashMap<String, String>();
    // mandatory parameter
    options.put("url", "https://www.google.com");
    // all next parameters are optional, see our API guide for more details
    options.put("dimension", "1366x768"); // or "1366xfull" for full length screenshot
    options.put("device", "desktop");
    options.put("format", "png");
    options.put("cacheLimit", "0");
    options.put("delay", "200");
    options.put("zoom", "100");
```
More info about options can be found in our [API doc](https://www.screenshotmachine.com/api.php).  

 Sample code
-----

```java
    String customerKey = "PUT_YOUR_CUSTOMER_KEY_HERE";
    String secretPhrase = "";//leave secret phrase empty, if not needed

    ScreenshoMachine sm = new ScreenshoMachine(customerKey, secretPhrase);

    Map<String, String> options = new HashMap<String, String>();
    // mandatory parameter
    options.put("url", "https://www.google.com");
    // all next parameters are optional, see our API guide for more details
    options.put("dimension", "1366x768"); // or "1366xfull" for full length screenshot
    options.put("device", "desktop");
    options.put("format", "png");
    options.put("cacheLimit", "0");
    options.put("delay", "200");
    options.put("zoom", "100");
    
    String apiUrl = sm.generateApiUrl(options);
    // put link to your html code
    System.out.println(apiUrl);    
```
Generated ```apiUrl```  link can be placed in ```<img>``` tag or used in your business logic later.

If you need to store captured screenshot as an image, just call:

```java
    String apiUrl = sm.generateApiUrl(options);

    // or save screenshot as an image
    InputStream in = new URL(apiUrl).openStream();
    String output = "out.png";
    Files.copy(in, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
    System.out.println("Screenshot saved as " + output);
```

Captured screenshot will be saved as ```output.png``` file in current directory.

# License

The MIT License (MIT)    