package com.bhaktisudha.androidapplicationtask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhaktisudha.androidapplicationtask.adapter.ResultAdapter
import com.bhaktisudha.androidapplicationtask.databinding.ActivityMainBinding
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModelItem
import com.bhaktisudha.androidapplicationtask.viewmodels.MainViewModel
import com.bhaktisudha.androidapplicationtask.viewmodels.MainViewModelFactory
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var  resultAdapter: ResultAdapter
    var items = ArrayList<ResultModelItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        resultAdapter = ResultAdapter(this)
        binding.recycler.adapter =resultAdapter
        val repository = (application as ResultApplication).repository
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.results.observe(this,{
            Log.d("FETCHRESULT",it.toString())
            it.forEach {
                var resultModelItem = ResultModelItem()
                resultModelItem.firstName = it.firstName.toString()
                items.add(resultModelItem)
            }
          items.size
          resultAdapter.updateList(items)

        })


        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    // Notify Adapter of the moved item!
                    recyclerView.adapter!!.notifyItemMoved(
                        viewHolder.adapterPosition,
                        target.adapterPosition
                    )
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    items?.removeAt(viewHolder.adapterPosition)
                    resultAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                }

                override fun isItemViewSwipeEnabled(): Boolean {
                    // Disable swipe (dont override this method or return true, if you want to have swipe)
                    return true
                }

                override fun getMovementFlags(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ): Int {
                    // Set movement flags to specify the movement direction
                    // final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;  <-- for all directions
                    // In this case only up and down is allowed
                   // val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                    val dragFlags =0
                    val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    return makeMovementFlags(dragFlags, swipeFlags)
                }
            }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recycler)

    }
}