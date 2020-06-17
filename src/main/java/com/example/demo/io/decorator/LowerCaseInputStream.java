package com.example.demo.io.decorator;/**
 * @Author handa
 * Description:
 * Date: Created in 20:36 2020/1/7
 * Modified By:
 */

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/7 20:36
 */
public class LowerCaseInputStream extends FilterInputStream {

    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toLowerCase(c);
    }

    public int read(byte[] b, int offset, int len) throws IOException {
        int result = super.read(b, offset, len);
        for (int i = offset; i < len - result; i++) {
            b[i] = (byte) Character.toLowerCase(b[i]);
        }
        return result;
    }
}
