package com.intellect.ello.android

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.intellect.ello.model.ServiceEntity

class ServicesAdapter(var services: List<ServiceEntity>, var context: Context ): RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val x = LayoutInflater.from(context).inflate(R.layout.list_items, parent, false)
        val y = ViewHolder(x)
        return y
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bind(services[position], context)

    }

    override fun getItemCount(): Int {
        return services.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(service: ServiceEntity, context: Context){

            val disp = itemView.findViewById<TextView>(R.id.textId)
            disp.text = service.name

            disp.setOnClickListener {
                val intent = Intent(context, Organization::class.java)
                intent.putExtra("id", service.id)
                context.startActivity(intent)
            }

        }

    }
}