import java.io.*;

/**
 *
 * @author huhangtao
 */
public class MyClassLoader extends ClassLoader {

    public static final String RESOURCE_PATH = "/Hello.xlass";
    public static final String RESOURCE_CLASS = "Hello";
    public static final String RESOURCE_METHOD = "hello";

    public static void main(String[] args) throws Exception {
        // find class
        Class<?> cls = new MyClassLoader().findClass(RESOURCE_CLASS);

        // new instance
        Object o = cls.newInstance();

        // invoke hello method
        cls.getMethod(RESOURCE_METHOD).invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) {
        final String path = this.getClass().getResource(RESOURCE_PATH).getPath();
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = readFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        decode(fileBytes);
        return defineClass(name, fileBytes, 0, fileBytes.length);
    }


    private void decode(byte[] fileBytes) {
        for (int i = 0; i < fileBytes.length; i++) {
            fileBytes[i] = (byte) (255 - fileBytes[i]);
        }
    }

    private byte[] readFile(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n;
        while ((n = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

}
