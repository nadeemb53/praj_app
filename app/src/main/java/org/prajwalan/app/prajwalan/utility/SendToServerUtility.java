package org.prajwalan.app.prajwalan.utility;

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
 * Created by Moiz on 09/01/2016.
 */

public class SendToServerUtility {

    class Files {
        public String fieldName;
        public File uploadFile;
    }

    class FormField {
        public String fieldName;
        public String fieldValue;
    }

    private String charset;
    private  String requestURL;
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private ArrayList<Files> files;
    private ArrayList<FormField> fields;

    public SendToServerUtility(String requestURL, String charset) {
        this.charset = charset;
        this.requestURL = requestURL;
        boundary = "===" + System.currentTimeMillis() + "===";
        files = new ArrayList<Files>();
        fields = new ArrayList<FormField>();
    }

    public SendToServerUtility(String requestURL) {
        this.charset = "UTF-8";
        this.requestURL = requestURL;
        boundary = "===" + System.currentTimeMillis() + "===";
        files = new ArrayList<Files>();
        fields = new ArrayList<FormField>();
    }

    public void addFormField(String name, String value) {
        FormField newField = new FormField();
        newField.fieldName = name;
        newField.fieldValue = value;
        fields.add(newField);
    }

    public void addFilePart(String fieldName, File uploadFile) {
        Files a = new Files();
        a.fieldName = fieldName;
        a.uploadFile = uploadFile;
        files.add(a);
    }

    public String send() throws IOException {

        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true); // indicates POST method
        httpConn.setDoInput(true);

        if(files.size() == 0) {
            executePostRequest();
        }
        else {
            executeMultipartRequest();
        }

        String response = getResponse();
        return response;
    }


    private void executePostRequest() throws IOException {

        String dataToSend = getPostData();
        byte[] bytes = dataToSend.getBytes();

        httpConn.setFixedLengthStreamingMode(bytes.length);
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        OutputStream out = httpConn.getOutputStream();
        out.write(bytes);
        out.close();
    }

    private void executeMultipartRequest() throws IOException {

        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        OutputStream outputStream = httpConn.getOutputStream();
        PrintWriter pwriter = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);

        String dataToSend = getMultipartData();
        pwriter.append(dataToSend);
        pwriter.flush();
        addFiles(pwriter, outputStream);
        pwriter.append(LINE_FEED).flush();
        pwriter.append("--" + boundary + "--").append(LINE_FEED);
        pwriter.close();
    }

    private String getPostData() {
        StringBuffer writer = new StringBuffer();
        Iterator<FormField> allFields = fields.iterator();
        while(allFields.hasNext()) {
            FormField a = allFields.next();
            writer.append(a.fieldName).append('=').append(a.fieldValue);
            if (allFields.hasNext()) {
                writer.append('&');
            }
        }
        return writer.toString();
    }

    private String getMultipartData() {
        StringBuffer writer = new StringBuffer();
        Iterator<FormField> allFields = fields.iterator();
        while(allFields.hasNext()) {
            FormField a = allFields.next();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + a.fieldName + "\"").append(LINE_FEED);
            writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.append(a.fieldValue).append(LINE_FEED);
        }
        return writer.toString();
    }

    private void addFiles(PrintWriter pwriter,OutputStream outputStream) throws IOException {
        Iterator<Files> allFiles = files.iterator();
        while(allFiles.hasNext()) {
            Files a = allFiles.next();
            File uploadFile = a.uploadFile;
            String fieldName = a.fieldName;

            String fileName = uploadFile.getName();
            pwriter.append("--" + boundary).append(LINE_FEED);
            pwriter.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            pwriter.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
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
            pwriter.append(LINE_FEED);
            pwriter.flush();
        }
    }

    private String getResponse() throws IOException {
        StringBuffer response = new StringBuffer();
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

}
