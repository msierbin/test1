package info.sierbin.nordea.demo.providers;

import java.io.InputStream;
import org.springframework.stereotype.Service;

@Service
public class StandardInputProvider implements InputProvider {

    @Override
    public InputStream get() {
        return System.in;
    }
}
