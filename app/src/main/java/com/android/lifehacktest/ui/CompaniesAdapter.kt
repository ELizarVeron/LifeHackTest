package com.android.lifehacktest.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.lifehacktest.databinding.CompanyItemBinding

import com.android.lifehacktest.domain.models.Company
import com.squareup.picasso.Picasso

class CompaniesAdapter(
    private val context: Context,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<CompaniesAdapter.CompaniesViewHolder>() {

    var itemList: List<Company> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class CompaniesViewHolder(
        private val companyItemBinding: CompanyItemBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(companyItemBinding.root),
        View.OnClickListener {

        fun cancelLoad() {
            // Picasso.get().cancelRequest(companyItemBinding)
        }

        fun bind(item: Company) {
            val id = itemList[adapterPosition].id
            companyItemBinding.constraintLayout.setOnClickListener(this)
            companyItemBinding.textViewTitle.text = item.name


            /* Picasso.get()
                 .load("https:" + item.apiFeaturedImage)
                 .fit()
                 .placeholder(R.mipmap.ic_noimage)
                 .error(R.mipmap.ic_noimage)
                 .into(productItemBinding.imageView)*/
        }


        @SuppressLint("SuspiciousIndentation")
        override fun onClick(p0: View?) {
            val id = itemList[adapterPosition].id
            onItemClick(id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompaniesViewHolder {
        val binding = CompanyItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CompaniesViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: CompaniesViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemList.size ?: 0
}