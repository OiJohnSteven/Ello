package com.intellect.ello.android

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.intellect.ello.model.OrganizationEntity

class OrganizationAdapter(var organizationEntity: ArrayList<OrganizationEntity>, var context: Context):RecyclerView.Adapter<OrganizationAdapter.ViewHolder>() {

    class ViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(organizationEntity: OrganizationEntity, context: Context){
            val name = itemView.findViewById<TextView>(R.id.orName)
            val desc = itemView.findViewById<TextView>(R.id.orDesc)
            val status = itemView.findViewById<TextView>(R.id.orStatus)

            name.text  = organizationEntity.name
            desc.text = organizationEntity.description
            status.text = organizationEntity.status

            val orName = organizationEntity.name
            val orDes = organizationEntity.description
            val orContact = organizationEntity.contact
            val orEmail = organizationEntity.email
            val orLocation = organizationEntity.location
            val orStatus = organizationEntity.status

            val card= itemView.findViewById<CardView>(R.id.orCard)
            card.setOnClickListener {
                val intent = Intent(context, OrganizationDetails::class.java)

                intent.putExtra("orName", orName)
                intent.putExtra("orDes",orDes)
                intent.putExtra("orContact", orContact)
                intent.putExtra("orEmail", orEmail)
                intent.putExtra("orLocation", orLocation)
                intent.putExtra("orStatus",orStatus)

                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val x = LayoutInflater.from(context).inflate(R.layout.organization_layout, parent, false)
        return ViewHolder(x)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bind(organizationEntity[position], context)

    }

    override fun getItemCount(): Int {
        return organizationEntity.size
    }
}