package com.wilman.easysoccer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wilman.easysoccer.databinding.ItemStadiumBinding
import com.wilman.easysoccer.models.Stadium

class StadiumsAdapter(
    private val selectGoToDetail: (Stadium) -> Unit,
) : RecyclerView.Adapter<StadiumsAdapter.SearchProductViewHolder>() {
    private var stadiums: ArrayList<Stadium> = arrayListOf()

    fun setList(listStadiums: List<Stadium>) {
        clearAdapter()
        stadiums.addAll(listStadiums)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        stadiums = arrayListOf()
        stadiums.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStadiumBinding.inflate(layoutInflater, parent, false)
        return SearchProductViewHolder(binding)
    }

    override fun getItemCount() = stadiums.size

    override fun onBindViewHolder(holder: SearchProductViewHolder, position: Int) {
        val product = stadiums[position]
        holder.itemView.setOnClickListener { selectGoToDetail(product) }
        holder.bind(product, holder.itemView.context)
    }

    open class SearchProductViewHolder(
        private var view: ItemStadiumBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(stadium: Stadium, context: Context) {
            view.apply {
                txvNumberStadium.text = stadium.numberStadium
                txtStatus.text = stadium.status
                val identifier =
                    context.resources.getIdentifier(stadium.image, "drawable", context.packageName)
                if (identifier > 0) {
                    imvStadium.setImageResource(identifier)
                }
                lnlItemProduct.elevation = ELEVATION_CARD
            }
        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }
}
