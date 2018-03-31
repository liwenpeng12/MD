package lwp.md.JavaBean;

/**
 * Created by liwenpeng on 18-3-30.
 */

public class Fruit {
    public String name;
    public int imageid;

    public Fruit(String name, int imageid) {
        this.name = name;
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public int getImageid() {
        return imageid;
    }
}
