import lombok.*;

import java.io.File;
import java.io.FileInputStream;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Student {
    private String name;
    private int rollNumber;

}
class DemoLombok {
    @SneakyThrows
    public static void main(String[] args) {
        File f = new File("abc.text");
        FileInputStream fis = new FileInputStream(f);
    }
}
