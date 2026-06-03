import java.io.Serializable

class Customer(
    val id: Int,
    val name: String,
    val email: String,
    val mobile: String
): Serializable {
    override fun toString(): String {
        return "id: $id, name: $name, email: $email, mobile: $mobile)\n"
    }
}