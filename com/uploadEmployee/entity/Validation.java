package uploadEmployee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "validation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tab_map_xlsx", referencedColumnName = "id")
    private TabMapXLSX tabMapXLSX;

    @OneToOne
    @JoinColumn(name = "id_field_map", referencedColumnName = "id")
    private FieldMap fieldMap;

    @OneToOne
    @JoinColumn(name = "id_validation_type", referencedColumnName = "id")
    private ValidationsType validationsType;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Validation that = (Validation) object;
        return Objects.equals(tabMapXLSX, that.tabMapXLSX) &&
                Objects.equals(fieldMap, that.fieldMap) &&
                Objects.equals(validationsType, that.validationsType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tabMapXLSX, fieldMap, validationsType);
    }
}
