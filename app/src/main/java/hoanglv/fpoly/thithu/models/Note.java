package hoanglv.fpoly.thithu.models;

public class Note {
    private int id;
    private String noidung;
    private String ngay;

    public Note(String ngay, String noidung) {
        this.ngay = ngay;
        this.noidung = noidung;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
