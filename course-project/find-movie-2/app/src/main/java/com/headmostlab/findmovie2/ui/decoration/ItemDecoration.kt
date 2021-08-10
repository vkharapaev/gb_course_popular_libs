package com.headmostlab.findmovie2.ui.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration private constructor() : RecyclerView.ItemDecoration() {

    private val rect = Rect()
    private var first: Int = 0
    private var last: Int = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val lm = parent.layoutManager as? LinearLayoutManager ?: return
        val itemCount = parent.adapter?.itemCount ?: return

        outRect.set(rect)

        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            if (lm.orientation == LinearLayoutManager.VERTICAL) {
                outRect.top = first
            } else {
                outRect.left = first
            }
        } else if (position == itemCount - 1) {
            if (lm.orientation == LinearLayoutManager.VERTICAL) {
                outRect.bottom = last
            } else {
                outRect.right = last
            }
        }
    }

    class Builder(private val dec: ItemDecoration = ItemDecoration()) {

        fun setLeft(margin: Int): Builder =
            this.also { dec.rect.left = margin }

        fun setTop(margin: Int): Builder =
            this.also { dec.rect.top = margin }

        fun setRight(margin: Int): Builder =
            this.also { dec.rect.right = margin }

        fun setBottom(margin: Int): Builder =
            this.also { dec.rect.bottom = margin }

        fun setVertical(margin: Int): Builder =
            this.also { dec.rect.top = margin; dec.rect.bottom = margin }

        fun setHorizontal(margin: Int): Builder =
            this.also { dec.rect.left = margin; dec.rect.right = margin }

        fun setFirst(margin: Int): Builder =
            this.also { dec.first = margin }

        fun setLast(margin: Int): Builder =
            this.also { dec.last = margin }

        fun setFirstLast(margin: Int): Builder =
            this.also { dec.first = margin; dec.last = margin }

        fun build(): ItemDecoration = dec
    }
}