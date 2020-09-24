package info.sierbin.nordea.demo.providers;

import java.io.OutputStream;
import org.springframework.stereotype.Service;

@Service
public class StandardOutputProvider implements OutputProvider {
    @Override
    public OutputStream get() {
        return System.out;
    }
}
