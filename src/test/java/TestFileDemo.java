import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.SneakyThrows;

public class TestFileDemo {
    @SneakyThrows
    public static void main(String[] args)throws IOException {
        Properties p = new Properties();
        File f=new File("selenium.properties");
        FileInputStream f1=new FileInputStream(f);
        p.load(f1);
        //System.out.println(p.get("chromedriver.path"));
        System.out.println(p.get("firefox.driver.path"));
    }
}