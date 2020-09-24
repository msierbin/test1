package info.sierbin.nordea.demo.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.function.BiConsumer;
import org.springframework.stereotype.Component;

@Component
public class StreamPiper {

    public InputStream pipe(
        final InputStream in,
        final BiConsumer<InputStream, OutputStream> biConsumer
    ) throws IOException {
        final PipedOutputStream pipeOut = new PipedOutputStream();
        final PipedInputStream pipeIn = new PipedInputStream(pipeOut);
        new Thread(() -> {
            try {
                biConsumer.accept(in, pipeOut);
                pipeOut.close();
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return pipeIn;
    }

}
