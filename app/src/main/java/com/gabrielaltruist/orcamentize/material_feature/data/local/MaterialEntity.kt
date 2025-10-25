import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabrielaltruist.orcamentize.material_feature.domain.model.EMeasure
import com.gabrielaltruist.orcamentize.material_feature.domain.model.Material
import com.gabrielaltruist.orcamentize.material_feature.domain.model.Measure

@Entity(tableName = "materials")
data class MaterialEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val label: String,
    val description: String,
    val costPrice: Double,
    val salePrice: Double,
    val eMeasure: EMeasure,

    val measureValue1: Double?,
    val measureValue2: Double?,
    val measureValue3: Double?
) {
    fun toDomainModel(): Material {
        val measure = when (eMeasure) {
            EMeasure.Quantity -> Measure.Quantity(measureValue1!!.toInt())
            EMeasure.Linear -> Measure.Linear(measureValue1!!)
            EMeasure.M2 -> Measure.M2(measureValue1!!, measureValue2!!)
            EMeasure.M3 -> Measure.M3(measureValue1!!, measureValue2!!, measureValue3!!)
        }

        return Material(
            id = this.id,
            name = this.name,
            label = this.label,
            description = this.description,
            costPrice = this.costPrice,
            salePrice = this.salePrice,
            measure = measure,
            eMeasure = eMeasure
        )
    }
}

