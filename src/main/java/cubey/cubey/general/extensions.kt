package cubey.cubey.general


/**
 * Returns `true` if enum T contains an entry with the specified name.
 */
inline fun <reified T : Enum<T>> contains(name: String): Boolean {
    return enumValues<T>().any { it.name == name}
}