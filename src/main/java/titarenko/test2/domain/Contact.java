package titarenko.test2.domain;

/**
 * Created by MyMac on 27.09.16.
 */
public class Contact {

    private Integer id;

    private String name;

    public Contact(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
