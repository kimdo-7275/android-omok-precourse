package nextstep.omok

import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    internal val stoneList: MutableList<MutableList<Int>> = MutableList(15) {
        MutableList(15) { 0 }
    }
    internal var isBlackTurn = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val board = findViewById<TableLayout>(R.id.board)
        board
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<ImageView>()
            .forEach { view ->
                view.setOnClickListener {
                    if (view.getTag() != null) {
                        return@setOnClickListener
                    }
                    var parentView = view.parent
                    while (parentView != null && parentView !is TableRow) {
                        parentView = parentView.parent
                    }
                    if (parentView !is TableRow) {
                        return@setOnClickListener
                    }
                    val x = (parentView).indexOfChild(view)
                    val y = board.indexOfChild(parentView)
                    if (isBlackTurn) {
                        view.setImageResource(R.drawable.black_stone)
                        view.setTag(R.drawable.black_stone)
                        stoneList[x][y] = 1
                    } else {
                        view.setImageResource(R.drawable.white_stone)
                        view.setTag(R.drawable.white_stone)
                        stoneList[x][y] = 2
                    }
//                    checkForWin(x, y)
                    isBlackTurn = !isBlackTurn
                }
            }
    }

}