package uploadEmployee.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tabmapxlsx")
public class TabMapXLSX {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameXLSXPref")
    private String nameXLSXPref;

    @Column(name = "nameTable")
    private String nameTable;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TabMapXLSX that = (TabMapXLSX) object;
        return Objects.equals(nameXLSXPref, that.nameXLSXPref) &&
                Objects.equals(nameTable, that.nameTable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nameXLSXPref, nameTable);
    }
}
