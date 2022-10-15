package com.wilman.easysoccer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wilman.easysoccer.databinding.ItemsInYourAreaBinding
import com.wilman.easysoccer.models.InYourArea

class StadiumsUserAdapter(
    private val selectGoToDetail: (InYourArea) -> Unit,
    private val selectGoToReserve: (InYourArea) -> Unit,
) : RecyclerView.Adapter<StadiumsUserAdapter.SearchProductViewHolder>() {
    private var stadiumsInYouArea: ArrayList<InYourArea> = arrayListOf()

    fun setListInYouArea(listStadiumsInYourArea: List<InYourArea>) {
        clearAdapter()
        stadiumsInYouArea.addAll(listStadiumsInYourArea)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        stadiumsInYouArea = arrayListOf()
        stadiumsInYouArea.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemsInYourAreaBinding.inflate(layoutInflater, parent, false)
        return SearchProductViewHolder(binding)
    }

    override fun getItemCount() = stadiumsInYouArea.size

    override fun onBindViewHolder(holder: SearchProductViewHolder, position: Int) {
        val product = stadiumsInYouArea[position]
        //holder.itemView.setOnClickListener { selectGoToDetail(product) }
        holder.bind(product, holder.itemView.context, selectGoToDetail, selectGoToReserve)
    }

    open class SearchProductViewHolder(
        private var view: ItemsInYourAreaBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            stadiumInYourArea: InYourArea,
            context: Context,
            selectGoToDetail: (InYourArea) -> Unit,
            selectGoToReserve: (InYourArea) -> Unit
        ) {
            view.apply {
                txvTitleStadium.text = stadiumInYourArea.nameStadium
                txtValueStadium.text = stadiumInYourArea.valueStadium
                txvTitleDirectionStadium.text = stadiumInYourArea.directionStadium
                btnMap.setOnClickListener { selectGoToDetail(stadiumInYourArea) }
                btnReserverUser.setOnClickListener { selectGoToReserve(stadiumInYourArea) }
                val identifier =
                    context.resources.getIdentifier(
                        stadiumInYourArea.image,
                        "drawable",
                        context.packageName
                    )
                if (identifier > 0) {
                    imvStadiumInYouArea.setImageResource(identifier)
                }
                lnlItemInYourArea.elevation = ELEVATION_CARD
            }
        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }
}


