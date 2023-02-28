package cubey.cubey.compiler

import cubey.cubey.compiler.lexer.Accessor

class AbstractSyntaxTree {

        

}



data class TLDTree(val accessor: Accessor, val definer: String, val keywords: Array<String>, val arguments:
Array<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TLDTree

        if (accessor != other.accessor) return false
        if (definer != other.definer) return false
        if (!keywords.contentEquals(other.keywords)) return false
        if (!arguments.contentEquals(other.arguments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accessor.hashCode()
        result = 31 * result + definer.hashCode()
        result = 31 * result + keywords.contentHashCode()
        result = 31 * result + arguments.contentHashCode()
        return result
    }
}