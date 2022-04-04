import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//This is demo prog form Kumar Chandrakant's article Guide to Character Encoding
//Code listed in this article doesn't shows described results, so I have edit it a litlle...

public class Main {
    //Строка символов для преобразования в двоичный код
    static String LINE = "語"; //Put symbols string there
    //Кодировка из списка Charset: Big5, US-ASCII, UTF-32, UTF-8 и т.д.
    static String ENCODING = "UTF-32"; //Put character set here. Charset: Big5, US-ASCII, UTF-32, UTF-8 etc

    public static void main(String[] args) {
        try {
            System.out.println(LINE + " in charset " + ENCODING+ " converted to binary code:");
            System.out.println(convertToBinary(LINE, ENCODING));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public static String convertToBinary(String input, String encoding)
            throws UnsupportedEncodingException {
        byte[] encoded_input = Charset.forName(encoding)
                .encode(input)
                .array();

        return IntStream.range(0, encoded_input.length)
                .map(i -> encoded_input[i])
                .mapToObj(e -> Integer.toBinaryString(e < 0? e + 256: e))
                .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
                .collect(Collectors.joining(" "));
    }

}
