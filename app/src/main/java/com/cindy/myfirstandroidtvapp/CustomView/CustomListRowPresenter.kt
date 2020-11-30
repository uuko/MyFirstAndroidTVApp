package com.cindy.myfirstandroidtvapp.CustomView

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.leanback.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.cindy.myfirstandroidtvapp.CustomView.CustomListRowPresenter.NotifyDataType.DECREASE
import com.cindy.myfirstandroidtvapp.CustomView.CustomListRowPresenter.NotifyDataType.INCREASE
import com.cindy.myfirstandroidtvapp.FocusType

class CustomListRowPresenter(var mContext: Context?,
                             var mFocusType: FocusType? = FocusType.NORMAL,
                             var isBanner: Boolean = false,
                             focusHightlightMode: Int = FocusHighlight.ZOOM_FACTOR_MEDIUM): ListRowPresenter(focusHightlightMode) {

    private val TAG: String = javaClass.simpleName
    private var selected_One:Int? =0
    private var vHorizontalGridView: HorizontalGridView? = null
    private var isHorizontalGridViewInitFinish: Boolean = false
    private var mNotifyDataType: NotifyDataType = NotifyDataType.INCREASE
    private var mListRow: ListRow? = null
    override fun isUsingDefaultListSelectEffect() = false


    override fun onBindViewHolder(
        viewHolder: Presenter.ViewHolder?,
        item: Any?,
        payloads: MutableList<Any>?
    ) {

        val view: View = viewHolder!!.view
        vHorizontalGridView = view.findViewById(androidx.leanback.R.id.row_content)

        shadowEnabled = false
        when(mFocusType){
            FocusType.START -> {
                Log.v(TAG, "===== FocusType.START =====")
                vHorizontalGridView?.run{
                    Log.w(TAG, "vHorizontalGridView is not null")
                    windowAlignment = BaseGridView.WINDOW_ALIGN_BOTH_EDGE
                    windowAlignmentOffsetPercent = 0f
                    windowAlignmentOffset = 0
                    itemAlignmentOffsetPercent = 0f
                }
                vHorizontalGridView?.run {
                    addOnChildViewHolderSelectedListener(object: OnChildViewHolderSelectedListener(){
                        override fun onChildViewHolderSelected(
                            parent: RecyclerView?,
                            child: RecyclerView.ViewHolder?,
                            position: Int,
                            subposition: Int
                        ) {


                            Log.d("11111111111111111", "position: $position")
                            Log.d("11111111111111111", "subposition: $subposition")
                            Log.d("11111111111111111", "parent size??? ${parent!!.adapter!!.itemCount}")

//                            if(position==parent.adapter!!.itemCount-1){
//                                mNotifyDataType = NotifyDataType.DECREASE
//                                notifyData()
//                            }else if(position==0){
//                                mNotifyDataType = NotifyDataType.INCREASE
//                                notifyData()
//                            }

                        }
                    })
                }
            }
        }

        if(isBanner){
            isHorizontalGridViewInitFinish = false
            mNotifyDataType = NotifyDataType.INCREASE
            mListRow = if(item is ListRow) item else null
            vHorizontalGridView?.run{
                addOnLayoutCompletedListener(object : BaseGridView.OnLayoutCompletedListener{
                    override fun onLayoutCompleted(state: RecyclerView.State) {
                        Log.v(TAG, "onLayoutCompleted")
                        if(!isHorizontalGridViewInitFinish){
                            isHorizontalGridViewInitFinish = true
                            mNotifyDataType = NotifyDataType.INCREASE
                            notifyData()
                        }
                    }

                })
                addOnChildViewHolderSelectedListener(object: OnChildViewHolderSelectedListener(){
                    override fun onChildViewHolderSelected(
                        parent: RecyclerView?,
                        child: RecyclerView.ViewHolder?,
                        position: Int,
                        subposition: Int
                    ) {

                        Log.w(TAG, "===== onChildViewHolderSelected =====")
                        Log.d(TAG, "position: $position")
                        Log.d(TAG, "subposition: $subposition")
                        Log.d(TAG, "parent is null or not??? ${parent==null}")
                        Log.d(TAG, "parent size??? ${parent!!.adapter!!.itemCount}")

                        if(position==parent.adapter!!.itemCount-1){
                            mNotifyDataType = NotifyDataType.DECREASE
                            notifyData()
                        }else if(position==0){
                            mNotifyDataType = NotifyDataType.INCREASE
                            notifyData()
                        }

                    }
                })
            }
        }

        super.onBindViewHolder(viewHolder, item, payloads)
    }

    fun notifyData() {
        Log.v(TAG, "===== notifyData =====")
        Log.e(TAG, "vHorizontalGridView is null or not?? ${vHorizontalGridView == null}")
        Log.e(TAG, "item is null or not?? ${mListRow == null}")
        if (mListRow != null && vHorizontalGridView!=null) {
            var adapter: ArrayObjectAdapter = mListRow!!.adapter as ArrayObjectAdapter
            Log.e(TAG, "adapter is null or not?? ${adapter == null}")
            Log.e(TAG, "adapter size?? ${adapter.size()}")
            Handler().postDelayed(Runnable {

                var count: Int = adapter.size()
                var fromPosition: Int = 0
                var toPosition: Int = 0
                when (mNotifyDataType) {
                    NotifyDataType.INCREASE -> {
                        fromPosition = count - 1
                        toPosition = 0
                    }
                    NotifyDataType.DECREASE -> {
                        fromPosition = 0
                        toPosition = count - 1
                    }
                }

                Log.e(TAG, "count: $count")
                Log.e(TAG, "fromPosition: $fromPosition")
                Log.e(TAG, "toPosition: $toPosition")
                var imageUrl: String? =
                    adapter.get(fromPosition) as String
                adapter.remove(imageUrl)
                adapter.add(toPosition, imageUrl)
                for (i in 0 until adapter.size()) {
                    Log.i(TAG, "imageUrl: ${(adapter.get(i) as String)}")
                }

            }, 100)
        }
    }

    /**
     * @see INCREASE Means swipe right.
     * @see DECREASE Means swipe left.
     */
    enum class NotifyDataType {
        INCREASE, DECREASE
    }

    override fun onRowViewSelected(holder: RowPresenter.ViewHolder?, selected: Boolean) {
        super.onRowViewSelected(holder, selected)
        if (holder?.row?.id.toString()!="null"){
            Log.d("selected",holder?.row?.id.toString())
            selected_One=holder?.row?.id?.toInt()
        }

    }

    fun getNowSelected():Int?{
        return selected_One
    }

}

