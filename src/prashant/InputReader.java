package prashant;

/**
 * Created by prashant on 2/7/17.
 * Copied from net.egork.chelper.util in chelper jar.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;

public class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if(this.numChars == -1) {
            throw new InputMismatchException();
        } else {
            if(this.curChar >= this.numChars) {
                this.curChar = 0;

                try {
                    this.numChars = this.stream.read(this.buf);
                } catch (IOException var2) {
                    throw new InputMismatchException();
                }

                if(this.numChars <= 0) {
                    return -1;
                }
            }

            return this.buf[this.curChar++];
        }
    }

    public int peek() {
        if(this.numChars == -1) {
            return -1;
        } else {
            if(this.curChar >= this.numChars) {
                this.curChar = 0;

                try {
                    this.numChars = this.stream.read(this.buf);
                } catch (IOException var2) {
                    return -1;
                }

                if(this.numChars <= 0) {
                    return -1;
                }
            }

            return this.buf[this.curChar];
        }
    }

    public int readInt() {
        int c;
        for(c = this.read(); isSpaceChar(c); c = this.read()) {
            ;
        }

        byte sgn = 1;
        if(c == 45) {
            sgn = -1;
            c = this.read();
        }

        int res = 0;

        while(c >= 48 && c <= 57) {
            res *= 10;
            res += c - 48;
            c = this.read();
            if(isSpaceChar(c)) {
                return res * sgn;
            }
        }

        throw new InputMismatchException();
    }

    public long readLong() {
        int c;
        for(c = this.read(); isSpaceChar(c); c = this.read()) {
            ;
        }

        byte sgn = 1;
        if(c == 45) {
            sgn = -1;
            c = this.read();
        }

        long res = 0L;

        while(c >= 48 && c <= 57) {
            res *= 10L;
            res += (long)(c - 48);
            c = this.read();
            if(isSpaceChar(c)) {
                return res * (long)sgn;
            }
        }

        throw new InputMismatchException();
    }

    public String readString() {
        int length = this.readInt();
        if(length < 0) {
            return null;
        } else {
            byte[] bytes = new byte[length];

            for(int e = 0; e < length; ++e) {
                bytes[e] = (byte)this.read();
            }

            try {
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException var4) {
                return new String(bytes);
            }
        }
    }

    public String readToken() {
        int c;
        while(isSpaceChar(c = this.read())) {
            ;
        }

        StringBuilder result = new StringBuilder();
        result.appendCodePoint(c);

        while(!isSpaceChar(c = this.read())) {
            result.appendCodePoint(c);
        }

        return result.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == 32 || c == 10 || c == 13 || c == 9 || c == -1;
    }

    public char readCharacter() {
        int c;
        for(c = this.read(); isSpaceChar(c); c = this.read()) {
            ;
        }

        return (char)c;
    }

    public double readDouble() {
        int c;
        for(c = this.read(); isSpaceChar(c); c = this.read()) {
            ;
        }

        byte sgn = 1;
        if(c == 45) {
            sgn = -1;
            c = this.read();
        }

        double res = 0.0D;

        while(true) {
            if(!isSpaceChar(c) && c != 46) {
                if(c != 101 && c != 69) {
                    if(c >= 48 && c <= 57) {
                        res *= 10.0D;
                        res += (double)(c - 48);
                        c = this.read();
                        continue;
                    }

                    throw new InputMismatchException();
                }

                return res * Math.pow(10.0D, (double)this.readInt());
            }

            if(c == 46) {
                c = this.read();

                for(double m = 1.0D; !isSpaceChar(c); c = this.read()) {
                    if(c == 101 || c == 69) {
                        return res * Math.pow(10.0D, (double)this.readInt());
                    }

                    if(c < 48 || c > 57) {
                        throw new InputMismatchException();
                    }

                    m /= 10.0D;
                    res += (double)(c - 48) * m;
                }
            }

            return res * (double)sgn;
        }
    }

    public boolean isExhausted() {
        int value;
        while(isSpaceChar(value = this.peek()) && value != -1) {
            this.read();
        }

        return value == -1;
    }

    public boolean readBoolean() {
        return this.readInt() == 1;
    }

    public <E extends Enum<E>> E readEnum(Class<E> c) {
        String name = this.readString();
        if(name == null) {
            return null;
        } else {
            Enum[] var3 = (Enum[])c.getEnumConstants();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Enum e = var3[var5];
                if(e.name().equals(name)) {
                    return (E) e;
                }
            }

            throw new EnumConstantNotPresentException(c, name);
        }
    }

    public Object readTopCoder() {
        String type = this.readToken();
        if(type.equals("-1")) {
            return null;
        } else if("int".equals(type)) {
            return Integer.valueOf(this.readInt());
        } else if("long".equals(type)) {
            return Long.valueOf(this.readLong());
        } else if("double".equals(type)) {
            return Double.valueOf(this.readDouble());
        } else if("String".equals(type)) {
            return this.readString();
        } else {
            int length;
            int i;
            if("int[]".equals(type)) {
                length = this.readInt();
                int[] var7 = new int[length];

                for(i = 0; i < length; ++i) {
                    var7[i] = this.readInt();
                }

                return var7;
            } else if("long[]".equals(type)) {
                length = this.readInt();
                long[] var6 = new long[length];

                for(i = 0; i < length; ++i) {
                    var6[i] = this.readLong();
                }

                return var6;
            } else if("double[]".equals(type)) {
                length = this.readInt();
                double[] var5 = new double[length];

                for(i = 0; i < length; ++i) {
                    var5[i] = this.readDouble();
                }

                return var5;
            } else if(!"String[]".equals(type)) {
                throw new InputMismatchException();
            } else {
                length = this.readInt();
                String[] result = new String[length];

                for(i = 0; i < length; ++i) {
                    result[i] = this.readString();
                }

                return result;
            }
        }
    }
}
