package info.sierbin.nordea.demo.providers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Primary
@Profile("string-test")
@Service
public class StringInputProvider implements InputProvider {

    private String content = "";

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public InputStream get() throws UnsupportedEncodingException {
        return new ByteArrayInputStream(content.getBytes("UTF-8"));
    }
}
