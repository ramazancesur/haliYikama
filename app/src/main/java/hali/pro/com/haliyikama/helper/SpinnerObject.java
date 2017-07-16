package hali.pro.com.haliyikama.helper;

/**
 * Created by ramazancesur on 30/06/2017.
 */

public class SpinnerObject {
    private Long id;
    private String name;

    public SpinnerObject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
