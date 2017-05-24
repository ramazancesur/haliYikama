package hali.pro.com.haliyikama.Helper;

/**
 * Created by ramazancesur on 02/05/2017.
 */

public class EnumUtil {
    public enum EmployeeType {
        PATRON, ISCİ, ARAC
    }

    public enum EntityState {
        PASSIVE(0, "Pasif"),
        ACTIVE(1, "Aktif");
        private final Integer id;
        private final String name;

        private EntityState(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public static EntityState parse(Integer id) {
            for (EntityState entityState : EntityState.values()) {
                if (entityState.getId().equals(id)) {
                    return entityState;
                }
            }
            return null;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
