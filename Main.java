import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        DummyTest solver = new DummyTest();
        solver.solve(1, in, out);
        out.close();
    }

    static class DummyTest {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
        }

    }

    static class InputReader {
        public InputReader;

    }
}

