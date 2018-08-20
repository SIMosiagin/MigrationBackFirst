package uploadEmployee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "validationmanualdata")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValidationManualData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_validation", referencedColumnName = "id")
    private Validation validation;

    @Column(name = "sqlText")
    private String sqlText;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ValidationManualData that = (ValidationManualData) object;
        return Objects.equals(validation, that.validation) &&
                Objects.equals(sqlText, that.sqlText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(validation, sqlText);
    }
}
