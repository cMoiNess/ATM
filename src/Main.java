public class Main {
    public static void main(String[] args) {
        User yanis = new User();
        User jb = new User();
        Admin admin = new Admin();
        admin.depositOtherAccount(100, yanis);
        System.out.println(yanis.getBalance());
        admin.transferOtherAccount(1020, yanis, jb);
        System.out.println(yanis.getBalance());
        System.out.println(jb.getBalance());
    }
}