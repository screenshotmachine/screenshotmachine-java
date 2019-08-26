# screenshotmachine-java

This demo shows how to call online screenshot machine API using java programming language.

## Installation
First, you need to create a free/premium account at [www.screenshotmachine.com](https://www.screenshotmachine.com) website. After registration, you will see your customer key in your user profile. Also secret phrase is maintained here. Please, use secret phrase always, when your API calls are called from publicly available websites.  

Set-up your customer key and secret phrase (if needed) in the script:

```java
    String customerKey = "PUT_YOUR_CUSTOMER_KEY_HERE";
    String secretPhrase = "";//leave secret phrase empty, if not needed
```
## Website screenshot API
Set additional options to fulfill your needs: 

```java
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
```
More info about options can be found in our [Website screenshot API](https://www.screenshotmachine.com/website-screenshot-api.php).  

 Sample code
-----

```java
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
```
Generated ```apiUrl```  link can be placed in ```<img>``` tag or used in your business logic later.

If you need to store captured screenshot as an image, just call:

```java
    String apiUrl = sm.generateScreenshotApiUrl(options);

    // or save screenshot as an image
    URLConnection openConnection = new URL(apiUrl).openConnection();
    openConnection.addRequestProperty("User-Agent", "Mozilla/4.0");
    InputStream in = openConnection.getInputStream();
    String output = "out.png";
    Files.copy(in, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
    System.out.println("Screenshot saved as " + output);
```

Captured screenshot will be saved as ```out.png``` file in current directory.


## Website to PDF API

Set the PDF options: 
```java
    Map options = new HashMap();
    // mandatory parameter
    options.put("url", "https://www.google.com");
    // all next parameters are optional, see our website to PDF API guide for more details
    options.put("paper", "letter");
    options.put("orientation", "portrait");
    options.put("media", "print");
    options.put("bg", "nobg");
    options.put("delay", "2000");
    options.put("scale", "50");
```
More info about options can be found in our [Website to PDF API](https://www.screenshotmachine.com/website-to-pdf-api.php).  
#### Sample code

```java
    String customerKey = "PUT_YOUR_CUSTOMER_KEY_HERE";
    String secretPhrase = "";//leave secret phrase empty, if not needed

    ScreenshoMachine sm = new ScreenshoMachine(customerKey, secretPhrase);

    Map options = new HashMap();
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
```
Captured PDF will be saved as ```out.pdf``` file in the current directory.

# License

The MIT License (MIT)    