package example;

public class PeopleImpl implements People {

    private String privSex = "男";

    public String race = "汉族";

    @Override
    public void sayHi(String name) {
        System.out.println("hello," + name);
    }

    private void privSayHi() {
        System.out.println("privSayHi~");
    }

    public static void getSex() {
        System.out.println("18岁");
    }

}
