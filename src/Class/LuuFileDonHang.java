package Class;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;  // Sửa thành java.util.List

public class LuuFileDonHang {

    private static final String file = "donhang.dat";

    public static void saveData(List<DonHang> list1) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<DonHang> readData() {
        List<DonHang> list1 = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            list1 = (List<DonHang>) ois.readObject();
        } catch (FileNotFoundException e) {
            list1 = new ArrayList<>();
            saveData(list1);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list1;
    }
}
