package TinyCryptor.model.symmetric;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MaHoaThayThe {
    // fields
    private Map<Character, Character> mapMaHoa;
    private Map<Character, Character> mapGiaiMa;

    // constructor
    public MaHoaThayThe() {
        this.mapMaHoa = new HashMap<Character, Character>();
        this.mapGiaiMa = new HashMap<Character, Character>();
    }

    public MaHoaThayThe(String khoa) {
        this.mapMaHoa = new HashMap<Character, Character>();
        this.mapGiaiMa = new HashMap<Character, Character>();

        if (kiemTraKhoa(khoa)) {
            for (char key = 'A', j = 0; key < 'Z'; key++, j++) {
                char kiTuCuaKhoa = khoa.charAt(j);
                this.mapMaHoa.put(key, kiTuCuaKhoa);
                this.mapGiaiMa.put(kiTuCuaKhoa, key);
            }
        }
    }

    // methods
    public String maHoa(String chuoi) {
        return xuLy(mapMaHoa, chuoi);
    }

    public String giaiMa(String chuoi) {
        return xuLy(mapGiaiMa, chuoi);
    }

    private String xuLy(Map map, String chuoi) {
        if (kiemTraChuoi(chuoi)) {
            String result = "";
            for (int i = 0; i < chuoi.length(); i++) {
                result += map.get(chuoi.charAt(i));

            }
            return result;
        } else {
            return "Chuoi khong hop le: " + chuoi;
        }
    }

    public boolean kiemTraKhoa(String khoa) {
        int len = khoa.length();
        if (len == 26) {
            HashSet<Character> chars = new HashSet<Character>();
            for (int i = 0; i < len; i++) {
                Character character = khoa.charAt(i);
                // Khoa la chuoi ki tu in hoa va cac ki tu la duy nhat
                if (!Character.isUpperCase(character) || !chars.add(character)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean kiemTraChuoi(String chuoi) {
        // chuoi gom cac ki tu in hoa
        if (chuoi.matches("[A-Z]+")) {
            return true;
        }
        return false;
    }

    public void setKhoa(String khoa) {
        if (kiemTraKhoa(khoa)) {
            for (char key = 'A', value = 0; key < 'Z'; key++, value++) {
                char kiTuCuaKhoa = khoa.charAt(value);
                this.mapMaHoa.put(key, kiTuCuaKhoa);
                this.mapGiaiMa.put(kiTuCuaKhoa, key);
            }
        } else {
            new Exception("khoa khong hop le: " + khoa).printStackTrace();
        }
    }
}
