package com.make.deve.androidtestdev1.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.make.deve.androidtestdev1.R
import com.make.deve.androidtestdev1.databinding.FragmentListBinding
import com.make.deve.androidtestdev1.databinding.ItemDataBinding
import com.make.deve.androidtestdev1.local.database.PhotoDBEntity
import kotlinx.android.synthetic.main.item_data.*
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class ListFragment: Fragment() {
    lateinit var binding: FragmentListBinding
    val vm by navGraphViewModels<ListViewModel>(R.id.nav_fragment_list)

    private val adapter = adapterOf<PhotoDBEntity> {
        register(
            viewHolder = ::ViewHolder,
            layoutResource = R.layout.item_data,
            onBindViewHolder = { vh, pos, item ->
                vh.bind(item, pos)
            })
    }

    inner class ViewHolder(view: View) : RecyclerViewHolder<PhotoDBEntity>(view) {
        val binding: ItemDataBinding = ItemDataBinding.bind(view)
        var item = PhotoDBEntity(name="",description = "",path = "")

        init {
            binding.card.setOnClickListener {

            }
        }

        fun bind(item: PhotoDBEntity, position: Int) {
            this.item = item
            binding.run {
                name.text = item.name

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.items.adapter = adapter

        binding.swipe.setOnRefreshListener {
            vm.getAllItems()
            binding.swipe.isRefreshing = false
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        vm.getAllItems()
    }
}