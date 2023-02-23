package dev.suhrob.kattabozor.presentation.ui.main.first.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.suhrob.kattabozor.R
import dev.suhrob.kattabozor.core.extensions.loadImage
import dev.suhrob.kattabozor.databinding.ItemOffersBinding
import dev.suhrob.kattabozor.domain.model.offers.OfferModel

class OfferAdapter(
    private val context: Context
) : ListAdapter<OfferModel, OfferAdapter.MyViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<OfferModel>() {
        override fun areItemsTheSame(
            oldItem: OfferModel, newItem: OfferModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OfferModel, newItem: OfferModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private var itemBinding: ItemOffersBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(data: OfferModel) {

            itemBinding.apply {
                name.text = data.name
                brand.text = data.brand
                category.text = context.getString(R.string.category, data.category)
                image.layoutParams.width = data.image.width
                image.layoutParams.height = data.image.height
                image.loadImage(context, data.image.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemOffersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.onBind(data)
    }
}