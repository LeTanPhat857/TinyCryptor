package TinyCryptor.model.symmetric;

public class MaHoaDichChuyen {
    //fields
    private int khoa;

    // constructor
    public MaHoaDichChuyen() {
    }

    public MaHoaDichChuyen(int khoa) {
        this.khoa = khoa;
    }

    // methods
    public String maHoa(String chuoi) {
        if (kiemTraChuoi(chuoi)) {
            String result = "";
            for (int i = 0; i < chuoi.length(); i++) {
                int character = chuoi.charAt(i) + khoa;
                if (character > 'Z') {
                    character = (character - 'Z') + 'A';
                }
                result += (char) character;
            }
            return result;
        } else {
            return "Chuoi khong hop le: " + chuoi;
        }
    }

    public String giaiMa(String chuoi) {
        if (kiemTraChuoi(chuoi)) {
            String result = "";
            for (int i = 0; i < chuoi.length(); i++) {
                int character = chuoi.charAt(i) - khoa;
                if (character < 'A') {
                    character = 'Z' - ('A' - character);
                }
                result += (char) character;
            }
            return result;
        } else {
            return "Chuoi khong hop le: " + chuoi;
        }
    }

    public boolean kiemTraKhoa(int khoa) {
        // khoa lon hon 0 va nho hon 26
        if (0 < khoa && khoa < 26) {
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

    public void setKhoa(int khoa) {
        if (kiemTraKhoa(khoa)) {
            this.khoa = khoa;
        } else {
            new Exception("khoa khong hop le: " + khoa).printStackTrace();
        }
    }
}
