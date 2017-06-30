package hali.pro.com.haliyikama.helper;

/**
 * Created by ramazancesur on 02/05/2017.
 */

public class EnumUtil {
    public enum EmployeeType {
        PATRON, ISCİ, ARAC
    }

    public enum SiparisDurum {
        TESLIM_EDILECEK, TESLIME_HAZIR, TESLIM
    }

    public enum SendingDataType {
        POST, PUT, DELETE
    }

    public enum UnitType {
        ADET(1, "ADET"),
        KG(2, "KG"),
        ML(3, "ML");

        private int id;
        private String name;


        UnitType(int i, String adet) {
            this.id = id;
            this.name = name;
        }

        public static UnitType parse(Integer id) {
            for (UnitType entityState : UnitType.values()) {
                if (entityState.getId().equals(id)) {
                    return entityState;
                }
            }
            return null;
        }

        public Integer getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
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

    public enum AddresTipi {
        EV, IS, GENEL
    }

    public enum ContactTipi {
        EV, CEP, IS, GENEL
    }

    public enum TelOrAddres {
        TELEFON, ADDRES
    }
}