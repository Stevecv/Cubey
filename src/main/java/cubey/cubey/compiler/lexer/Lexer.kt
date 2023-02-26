package cubey.cubey.compiler.lexer

import cubey.cubey.compiler.AbstractSyntaxTree
import cubey.cubey.general.Recipe

class Lexer(val recipe: Recipe) {
    val code = StringBuilder(recipe.code);
    var pointer = 0;

    val trees = ArrayList<AbstractSyntaxTree>();


    private fun LexTopLevel() {
        val name    = code.indexOf(" ");
        val definer = code.removeRange(0, name).subSequence(1, -1).toString();


    }
}
