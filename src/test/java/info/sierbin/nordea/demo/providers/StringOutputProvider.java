package info.sierbin.nordea.demo.providers;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Primary
@Profile("string-test")
@Service
public class StringOutputProvider implements OutputProvider {

    private ByteArrayOutputStream out;

    @Override
    public OutputStream get() {
        this.out = new ByteArrayOutputStream();
        return new PrintStream(out);
    }

    public String getOut() throws UnsupportedEncodingException {
        return new String(out.toByteArray(), "UTF-8");
    }
}
