package com.wilman.easysoccer.ui.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wilman.easysoccer.models.Picture
import com.wilman.easysoccer.databinding.ItemImageProductBinding

class ImagesDetailAdapter(
    private val selectImage: (position: Int) -> Unit
) : RecyclerView.Adapter<ImagesDetailAdapter.ImageViewHolder>() {

    private var imagesProducts: ArrayList<Picture> = arrayListOf()

    fun setListImage(picturesList: List<Picture>) {
        imagesProducts = arrayListOf()
        imagesProducts.clear()
        imagesProducts.addAll(picturesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageProductBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val product = imagesProducts[position]
        holder.bind(product, holder.itemView.context)
        holder.itemView.setOnClickListener { selectImage(position) }
    }

    override fun getItemCount() = imagesProducts.size

    open class ImageViewHolder(private var view: ItemImageProductBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(picture: Picture, context: Context) {
            view.apply {
                val identifier =
                    context.resources.getIdentifier(picture.url, "drawable", context.packageName)
                if (identifier > 0) {
                    imvImageProduct.setImageResource(identifier)
                }
            }
        }
    }
}
