package Class;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;  // Sửa thành java.util.List

public class LuuFileKhachHang {

    private static final String file = "khachhang.dat";

    public static void saveData(List<KhachHang> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<KhachHang> readData() {
        List<KhachHang> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            list = (List<KhachHang>) ois.readObject();
        } catch (FileNotFoundException e) {
            list = new ArrayList<>();
            saveData(list);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }
}
