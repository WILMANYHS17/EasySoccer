package com.wilman.easysoccer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wilman.easysoccer.databinding.ItemReservsBinding
import com.wilman.easysoccer.models.Reserve

class ReservesAdminAdapter(
    private val selectGoToDetail: (Reserve) -> Unit,
) : RecyclerView.Adapter<ReservesAdminAdapter.ReserveAdminViewHolder>() {

    private var reserve: ArrayList<Reserve> = arrayListOf()

    fun setList(listReserve: List<Reserve>) {
        clearAdapter()
        reserve.addAll(listReserve)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        reserve = arrayListOf()
        reserve.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReserveAdminViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReservsBinding.inflate(layoutInflater, parent, false)
        return ReserveAdminViewHolder(binding)
    }

    override fun getItemCount() = reserve.size

    override fun onBindViewHolder(holder: ReserveAdminViewHolder, position: Int) {
        val product = reserve[position]
        holder.itemView.setOnClickListener { selectGoToDetail(product) }
        holder.bind(product, holder.itemView.context)
    }

    open class ReserveAdminViewHolder(
        private var view: ItemReservsBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun bind(reserve: Reserve, context: Context) {
            view.apply {
                txvTitleStadium.text = reserve.nameStadium
                txvReserveNumber.text = reserve.idReserve
                txvReserveName.text = reserve.nameReserve
                txvDateReserve.text = reserve.dateReserve
                txvStatusPayment.text = reserve.statusPayment
                txtPriceReserve.text = reserve.valueReserve
                lnlItemProduct.elevation = ELEVATION_CARD
            }
        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }
}
