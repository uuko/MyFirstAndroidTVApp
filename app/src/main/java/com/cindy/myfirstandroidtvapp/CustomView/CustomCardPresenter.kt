package com.cindy.myfirstandroidtvapp.CustomView

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.cindy.myfirstandroidtvapp.BuildConfig
import com.cindy.myfirstandroidtvapp.Model.Item
import com.cindy.myfirstandroidtvapp.R

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
        Log.d("selectedyyyyyyyyyyy","aaaaaa")
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        if(BuildConfig.DEBUG) Log.v(TAG, "===== onBindViewHolder =====")

        if(viewHolder!=null){


            if (item.toString().contains("string")){
                Log.d("iiiiiiiiiii",item.toString())
                val cardView: ImageCardView = viewHolder.view as ImageCardView
                val txt:TextView=cardView.findViewById(R.id.textView)
                val mainImg:ImageView=cardView.findViewById(R.id.main_image)
                mainImg.visibility=View.GONE
                val movieItem: Item = item as Item
                txt.setText(movieItem.name)
                val lp = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
                lp.setMargins(2000, 0, 0, 0)
                cardView.layoutParams=lp
            }else{
                val cardView: ImageCardView = viewHolder.view as ImageCardView
                val movieItem: Item = item as Item

                if(BuildConfig.DEBUG)Log.i(TAG, "movieItem: $movieItem")
                val mainImg:ImageView=cardView.findViewById(R.id.main_image)
                mainImg.visibility=View.VISIBLE
                val txt:TextView=cardView.findViewById(R.id.textView)
//            cardView.titleText = movieItem.name
                cardView.setMainImageDimensions(200, 200)
                if(mContext!=null){
                    Glide.with(mContext!!)
                        .load(movieItem.imageUrl)
                        .into(mainImg)


                    txt.setText(movieItem.name)



                }

            }


        }

    }

    override fun setOnClickListener(holder: ViewHolder?, listener: View.OnClickListener?) {
        super.setOnClickListener(holder, listener)

    }
    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        if(BuildConfig.DEBUG) Log.v(TAG, "===== onUnbindViewHolder =====")
    }
}