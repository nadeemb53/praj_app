package org.prajwalan.app.prajwalan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by pragati on 15-Dec-15.
 */


class Files {
    public String fieldName;
    public File uploadFile;
}

public class MultipartUtility {

    private  String requestURL;
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private StringBuffer writer;
    private PrintWriter pwriter;
    private ArrayList<Files> files;
    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     * @param requestURL
     * @param charset
     * @throws IOException
     */
    public MultipartUtility(String requestURL, String charset)
            throws IOException {
        this.charset = charset;
        this.requestURL = requestURL;
        // creates a unique boundary based on time stamp
        boundary = "===" + System.currentTimeMillis() + "===";
        writer = new StringBuffer();
        files = new ArrayList<Files>();
    }

    /**
     * Adds a header field to the request.
     * @param name - name of the header field
     * @param value - value of the header field
     */
    public void addHeaderField(String name, String value) {
        writer.append(name + ": " + value).append(LINE_FEED);

    }

    /**
     * Adds a form field to the request
     * @param name field name
     * @param value field value
     */
    public void addFormField(String name, String value) {
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=" + charset).append(
                LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED);
    }

    /**
     * Adds a upload file section to the request
     * @param fieldName name attribute in <input type="file" name="..." />
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    public void addFilePart(String fieldName, File uploadFile) {

        Files a = new Files();
        a.fieldName = fieldName;
        a.uploadFile = uploadFile;
        files.add(a);

    }

    /**
     * Completes the request and receives response from the server.
     * @return a list of Strings as response in case the server returned
     * status OK, otherwise an exception is thrown.
     * @throws IOException
     */
    public String send() throws IOException {

        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true); // indicates POST method
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
        httpConn.setRequestProperty("Test", "Bonjour");
        outputStream = httpConn.getOutputStream();
        pwriter = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                true);

        pwriter.append(writer.toString());
        pwriter.flush();
        addFiles();

        pwriter.append(LINE_FEED).flush();
        pwriter.append("--" + boundary + "--").append(LINE_FEED);
        pwriter.close();

        StringBuffer response = new StringBuffer();
        // checks server's status code first
        int status = httpConn.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            httpConn.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }
        return response.toString();
    }

    private void addFiles() throws IOException {

        Iterator<Files> allFiles = files.iterator();
        while(allFiles.hasNext()) {
            Files a = allFiles.next();
            File uploadFile = a.uploadFile;
            String fieldName = a.fieldName;

            String fileName = uploadFile.getName();
            pwriter.append("--" + boundary).append(LINE_FEED);
            pwriter.append(
                    "Content-Disposition: form-data; name=\"" + fieldName
                            + "\"; filename=\"" + fileName + "\"")
                    .append(LINE_FEED);
            pwriter.append(
                    "Content-Type: "
                            + URLConnection.guessContentTypeFromName(fileName))
                    .append(LINE_FEED);
            pwriter.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
            pwriter.append(LINE_FEED);
            pwriter.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED);
            pwriter.flush();
        }

    }
}
