package com.cindy.myfirstandroidtvapp.CustomView

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.cindy.myfirstandroidtvapp.BuildConfig
import com.cindy.myfirstandroidtvapp.Model.Item

class CustomCardPresenter: Presenter() {

    private val TAG: String = javaClass.simpleName
    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        if(BuildConfig.DEBUG) Log.v(TAG, "===== onCreateViewHolder =====")

        mContext = parent?.context
        Log.w(TAG, "mContext is null or not???? ${mContext == null}")

        val cardView: ImageCardView = ImageCardView(mContext)
        //顯示的類型
        cardView.cardType = BaseCardView.CARD_TYPE_MAIN_ONLY

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        if(BuildConfig.DEBUG) Log.v(TAG, "===== onBindViewHolder =====")

        if(viewHolder!=null){
            val cardView: ImageCardView = viewHolder.view as ImageCardView
            val movieItem: Item = item as Item
            if(BuildConfig.DEBUG)Log.i(TAG, "movieItem: $movieItem")

            cardView.titleText = movieItem.name
            cardView.contentText = movieItem.imageUrl
            cardView.setMainImageDimensions(280, 400)
            if(mContext!=null){
                Glide.with(mContext!!)
                    .load(movieItem.imageUrl)
                    .into(cardView.mainImageView)
            }

        }

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        if(BuildConfig.DEBUG) Log.v(TAG, "===== onUnbindViewHolder =====")
    }
}