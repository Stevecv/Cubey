package cubey.cubey.compiler.lexer

import cubey.cubey.compiler.AbstractSyntaxTree
import cubey.cubey.general.Recipe
import cubey.cubey.general.contains

class Lexer(val recipe: Recipe) {
    val code = StringBuilder(recipe.code);
    var pointer = 0;

    val trees = ArrayList<AbstractSyntaxTree>();


    private fun LexTopLevel() {
        var accessor: Accessor;
        var definer: String;

        val accessorOrDefiner = NextKeyword()

        //  Getting accessor and definer

        if (contains<Accessor>(accessorOrDefiner)) {
            accessor = Accessor.valueOf(accessorOrDefiner)
            definer = NextKeyword()
        } else {
            definer = accessorOrDefiner
            accessor = Accessor.public
        }

        //  Getting keywords





        //val definer = code.removeRange(0, name).subSequence(1, -1).toString();



        val keywords = ArrayList<String>();

    }



    private fun NextKeyword(): String {

    }
}
