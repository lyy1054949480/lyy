package com.example.lyy.util;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.util.*;

import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

//@Configuration
public class MyFormHttpMessageConverter implements HttpMessageConverter<MultiValueMap<String, ?>> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private List<MediaType> supportedMediaTypes = new ArrayList();
    private List<HttpMessageConverter<?>> partConverters = new ArrayList();
    private Charset charset;
    private Charset multipartCharset;

    public MyFormHttpMessageConverter() {
        this.charset = DEFAULT_CHARSET;
        this.supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        this.supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        this.partConverters.add(new ByteArrayHttpMessageConverter());
        this.partConverters.add(stringHttpMessageConverter);
        this.partConverters.add(new ResourceHttpMessageConverter());
        this.applyDefaultCharset();
    }

    public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        this.supportedMediaTypes = supportedMediaTypes;
    }

    public List<MediaType> getSupportedMediaTypes() {
        return Collections.unmodifiableList(this.supportedMediaTypes);
    }

    public void setPartConverters(List<HttpMessageConverter<?>> partConverters) {
        Assert.notEmpty(partConverters, "'partConverters' must not be empty");
        this.partConverters = partConverters;
    }

    public void addPartConverter(HttpMessageConverter<?> partConverter) {
        Assert.notNull(partConverter, "'partConverter' must not be null");
        this.partConverters.add(partConverter);
    }

    public void setCharset(Charset charset) {
        if (charset != this.charset) {
            this.charset = charset != null ? charset : DEFAULT_CHARSET;
            this.applyDefaultCharset();
        }

    }

    private void applyDefaultCharset() {
        Iterator var1 = this.partConverters.iterator();

        while(var1.hasNext()) {
            HttpMessageConverter<?> candidate = (HttpMessageConverter)var1.next();
            if (candidate instanceof AbstractHttpMessageConverter) {
                AbstractHttpMessageConverter<?> converter = (AbstractHttpMessageConverter)candidate;
                if (converter.getDefaultCharset() != null) {
                    converter.setDefaultCharset(this.charset);
                }
            }
        }

    }

    public void setMultipartCharset(Charset charset) {
        this.multipartCharset = charset;
    }

    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        if (!MultiValueMap.class.isAssignableFrom(clazz)) {
            return false;
        } else if (mediaType == null) {
            return true;
        } else {
            Iterator var3 = this.getSupportedMediaTypes().iterator();

            MediaType supportedMediaType;
            do {
                if (!var3.hasNext()) {
                    return false;
                }

                supportedMediaType = (MediaType)var3.next();
            } while(supportedMediaType.equals(MediaType.MULTIPART_FORM_DATA) || !supportedMediaType.includes(mediaType));

            return true;
        }
    }

    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        if (!MultiValueMap.class.isAssignableFrom(clazz)) {
            return false;
        } else if (mediaType != null && !MediaType.ALL.equals(mediaType)) {
            Iterator var3 = this.getSupportedMediaTypes().iterator();

            MediaType supportedMediaType;
            do {
                if (!var3.hasNext()) {
                    return false;
                }

                supportedMediaType = (MediaType)var3.next();
            } while(!supportedMediaType.isCompatibleWith(mediaType));

            return true;
        } else {
            return true;
        }
    }

    public MultiValueMap<String, String> read(Class<? extends MultiValueMap<String, ?>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        MediaType contentType = inputMessage.getHeaders().getContentType();
        Charset charset = contentType.getCharset() != null ? contentType.getCharset() : this.charset;
        String body = StreamUtils.copyToString(inputMessage.getBody(), charset);
        String[] pairs = StringUtils.tokenizeToStringArray(body, "&");
        MultiValueMap<String, String> result = new LinkedMultiValueMap(pairs.length);
        String[] var8 = pairs;
        int var9 = pairs.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            String pair = var8[var10];
            int idx = pair.indexOf(61);
            if (idx == -1) {
                result.add(URLDecoder.decode(pair, charset.name()), null);
            } else {
                String name = URLDecoder.decode(pair.substring(0, idx), charset.name());
                String value = URLDecoder.decode(pair.substring(idx + 1), charset.name());
                result.add(name, value);
            }
        }

        return result;
    }

    public void write(MultiValueMap<String, ?> map, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (!this.isMultipart(map, contentType)) {
            this.writeForm((MultiValueMap<String, String>)map, contentType, outputMessage);
        } else {
            this.writeMultipart((MultiValueMap<String, Object>)map, outputMessage);
        }

    }

    private boolean isMultipart(MultiValueMap<String, ?> map, MediaType contentType) {
        if (contentType != null) {
            return MediaType.MULTIPART_FORM_DATA.includes(contentType);
        } else {
            Iterator var3 = map.keySet().iterator();

            while(var3.hasNext()) {
                String name = (String)var3.next();
                Iterator var5 = ((List)map.get(name)).iterator();

                while(var5.hasNext()) {
                    Object value = var5.next();
                    if (value != null && !(value instanceof String)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private void writeForm(MultiValueMap<String, String> form, MediaType contentType, HttpOutputMessage outputMessage) throws IOException {
        Charset charset;
        if (contentType != null) {
            outputMessage.getHeaders().setContentType(contentType);
            charset = contentType.getCharset() != null ? contentType.getCharset() : this.charset;
        } else {
            outputMessage.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            charset = this.charset;
        }

        StringBuilder builder = new StringBuilder();
        Iterator nameIterator = form.keySet().iterator();

        while(nameIterator.hasNext()) {
            String name = (String)nameIterator.next();
            Iterator valueIterator = ((List)form.get(name)).iterator();

            while(valueIterator.hasNext()) {
                String value = (String)valueIterator.next();
                builder.append(URLEncoder.encode(name, charset.name()));
                if (value != null) {
                    builder.append('=');
                    builder.append(URLEncoder.encode(value, charset.name()));
                    if (valueIterator.hasNext()) {
                        builder.append('&');
                    }
                }
            }

            if (nameIterator.hasNext()) {
                builder.append('&');
            }
        }

        final byte[] bytes = builder.toString().getBytes(charset.name());
        outputMessage.getHeaders().setContentLength((long)bytes.length);
        if (outputMessage instanceof StreamingHttpOutputMessage) {
            StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage)outputMessage;
            streamingOutputMessage.setBody(new StreamingHttpOutputMessage.Body() {
                public void writeTo(OutputStream outputStream) throws IOException {
                    StreamUtils.copy(bytes, outputStream);
                }
            });
        } else {
            StreamUtils.copy(bytes, outputMessage.getBody());
        }

    }

    private void writeMultipart(final MultiValueMap<String, Object> parts, HttpOutputMessage outputMessage) throws IOException {
        final byte[] boundary = this.generateMultipartBoundary();
        Map<String, String> parameters = Collections.singletonMap("boundary", new String(boundary, "US-ASCII"));
        MediaType contentType = new MediaType(MediaType.MULTIPART_FORM_DATA, parameters);
        HttpHeaders headers = outputMessage.getHeaders();
        headers.setContentType(contentType);
        if (outputMessage instanceof StreamingHttpOutputMessage) {
            StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage)outputMessage;
            streamingOutputMessage.setBody(new StreamingHttpOutputMessage.Body() {
                public void writeTo(OutputStream outputStream) throws IOException {
                    MyFormHttpMessageConverter.this.writeParts(outputStream, parts, boundary);
                    MyFormHttpMessageConverter.writeEnd(outputStream, boundary);
                }
            });
        } else {
            this.writeParts(outputMessage.getBody(), parts, boundary);
            writeEnd(outputMessage.getBody(), boundary);
        }

    }

    private void writeParts(OutputStream os, MultiValueMap<String, Object> parts, byte[] boundary) throws IOException {
        Iterator var4 = parts.entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry<String, List<Object>> entry = (Map.Entry)var4.next();
            String name = (String)entry.getKey();
            Iterator var7 = ((List)entry.getValue()).iterator();

            while(var7.hasNext()) {
                Object part = var7.next();
                if (part != null) {
                    this.writeBoundary(os, boundary);
                    this.writePart(name, this.getHttpEntity(part), os);
                    writeNewLine(os);
                }
            }
        }

    }

    private void writePart(String name, HttpEntity<?> partEntity, OutputStream os) throws IOException {
        Object partBody = partEntity.getBody();
        Class<?> partType = partBody.getClass();
        HttpHeaders partHeaders = partEntity.getHeaders();
        MediaType partContentType = partHeaders.getContentType();
        Iterator var8 = this.partConverters.iterator();

        HttpMessageConverter messageConverter;
        do {
            if (!var8.hasNext()) {
                throw new HttpMessageNotWritableException("Could not write request: no suitable HttpMessageConverter found for request type [" + partType.getName() + "]");
            }

            messageConverter = (HttpMessageConverter)var8.next();
        } while(!messageConverter.canWrite(partType, partContentType));

        HttpOutputMessage multipartMessage = new MultipartHttpOutputMessage(os);
        multipartMessage.getHeaders().setContentDispositionFormData(name, this.getFilename(partBody));
        if (!partHeaders.isEmpty()) {
            multipartMessage.getHeaders().putAll(partHeaders);
        }

        messageConverter.write(partBody, partContentType, multipartMessage);
    }

    protected byte[] generateMultipartBoundary() {
        return MimeTypeUtils.generateMultipartBoundary();
    }

    protected HttpEntity<?> getHttpEntity(Object part) {
        return part instanceof HttpEntity ? (HttpEntity)part : new HttpEntity(part);
    }

    protected String getFilename(Object part) {
        if (part instanceof Resource) {
            Resource resource = (Resource)part;
            String filename = resource.getFilename();
            if (filename != null && this.multipartCharset != null) {
                filename = MimeDelegate.encode(filename, this.multipartCharset.name());
            }

            return filename;
        } else {
            return null;
        }
    }

    private void writeBoundary(OutputStream os, byte[] boundary) throws IOException {
        os.write(45);
        os.write(45);
        os.write(boundary);
        writeNewLine(os);
    }

    private static void writeEnd(OutputStream os, byte[] boundary) throws IOException {
        os.write(45);
        os.write(45);
        os.write(boundary);
        os.write(45);
        os.write(45);
        writeNewLine(os);
    }

    private static void writeNewLine(OutputStream os) throws IOException {
        os.write(13);
        os.write(10);
    }

    private static class MimeDelegate {
        private MimeDelegate() {
        }

        public static String encode(String value, String charset) {
            try {
                return MimeUtility.encodeText(value, charset, (String)null);
            } catch (UnsupportedEncodingException var3) {
                throw new IllegalStateException(var3);
            }
        }
    }

    private static class MultipartHttpOutputMessage implements HttpOutputMessage {
        private final OutputStream outputStream;
        private final HttpHeaders headers = new HttpHeaders();
        private boolean headersWritten = false;

        public MultipartHttpOutputMessage(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        public HttpHeaders getHeaders() {
            return this.headersWritten ? HttpHeaders.readOnlyHttpHeaders(this.headers) : this.headers;
        }

        public OutputStream getBody() throws IOException {
            this.writeHeaders();
            return this.outputStream;
        }

        private void writeHeaders() throws IOException {
//            if (!this.headersWritten) {
//                Iterator var1 = this.headers.entrySet().iterator();
//
//                while(var1.hasNext()) {
//                    Map.Entry<String, List<String>> entry = (Map.Entry)var1.next();
//                    byte[] headerName = this.getAsciiBytes((String)entry.getKey());
//                    Iterator var4 = ((List)entry.getValue()).iterator();
//
//                    while(var4.hasNext()) {
//                        String headerValueString = (String)var4.next();
//                        byte[] headerValue = this.getAsciiBytes(headerValueString);
//                        this.outputStream.write(headerName);
//                        this.outputStream.write(58);
//                        this.outputStream.write(32);
//                        this.outputStream.write(headerValue);
//                        MyFormHttpMessageConverter.writeNewLine(this.outputStream);
//                    }
//                }
//
//                MyFormHttpMessageConverter.writeNewLine(this.outputStream);
//                this.headersWritten = true;
//            }
            if (!this.headersWritten) {
                for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                    byte[] headerName = getAsciiBytes(entry.getKey());
                    for (String headerValueString : entry.getValue()) {
                        byte[] headerValue = getAsciiBytes(headerValueString);
                        this.outputStream.write(headerName);
                        this.outputStream.write(':');
                        this.outputStream.write(' ');
                        this.outputStream.write(headerValue);
                        writeNewLine(this.outputStream);
                    }
                }
                writeNewLine(this.outputStream);
                this.headersWritten = true;
            }
        }

        private byte[] getAsciiBytes(String name) throws UnsupportedEncodingException {
            return name.getBytes(DEFAULT_CHARSET);
        }
    }

}
