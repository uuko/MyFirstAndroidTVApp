package com.cindy.myfirstandroidtvapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.PageRow
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter


class IconHeaderItemPresenter : RowHeaderPresenter() {

    override fun onCreateViewHolder(viewGroup: ViewGroup): RowHeaderPresenter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).run {
            inflate(R.layout.icon_header_item, null)
        }

        return RowHeaderPresenter.ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, o: Any) {
        val headerItem = (o as PageRow).headerItem
        val rootView = viewHolder.view

        rootView.findViewById<ImageView>(R.id.header_icon).apply {
            rootView.resources.getDrawable(R.drawable.ic_banner_foreground, null).also { icon ->
                setImageDrawable(icon)
            }
        }

        rootView.findViewById<TextView>(R.id.header_label).apply {
            text = headerItem.name
        }
        val lp = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        Log.d("iddddddddddddd",headerItem.id.toString())
        lp.setMargins(0, (headerItem.id*10).toInt(), 0, 0)
        if (headerItem.id==2L){
            lp.setMargins(0, (headerItem.id*200).toInt(), 0, 0)
        }
        rootView.layoutParams=lp

//        rootView.setFocusable(View.FOCUSABLE)
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        // no op
    }
}