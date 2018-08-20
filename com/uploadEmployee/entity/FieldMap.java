package uploadEmployee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fieldmap")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FieldMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "id_tab_map_xlsx", referencedColumnName = "id")
    private TabMapXLSX id_tab_map_xlsx;


    @Column(name = "xlsxGroup")
    private String xlsxGroup;


    @Column(name = "xlsxField")
    private String xlsxField;


    @Column(name = "tableField")
    private String tableField;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FieldMap fieldMap = (FieldMap) object;
        return
                Objects.equals(id_tab_map_xlsx, fieldMap.id_tab_map_xlsx) &&
                Objects.equals(xlsxField, fieldMap.xlsxField) &&
                Objects.equals(tableField, fieldMap.tableField);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, id_tab_map_xlsx, xlsxField, tableField);
    }
}
