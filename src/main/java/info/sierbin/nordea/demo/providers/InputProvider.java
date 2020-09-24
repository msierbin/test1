package info.sierbin.nordea.demo.providers;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public interface InputProvider {
    InputStream get() throws UnsupportedEncodingException;
}
