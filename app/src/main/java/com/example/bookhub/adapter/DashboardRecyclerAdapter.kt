package com.example.bookhub.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookhub.R
import com.example.bookhub.activity.PdfViewer
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<HashMap<String, String>>):RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book=itemList[position]
        holder.txtBookName.text=book["Name"]
        holder.txtBookAuthor.text=book["Author"]
        //holder.imgBook.setImageResource(book["Image Path"])
        //holder.imgBook.setImageURI(Uri.parse(book["Image Path"]))
        val imageUrl =book["Image Path"].toString()
        val bookUrl=book["Book Link"].toString()

        Glide.with(holder.itemView)
                .load(imageUrl)
                .into(holder.imgBook)

        holder.itemView.setOnClickListener {
//            val intent = Intent(context,PdfViewer::class.java)
//            intent.putExtra("bookUrl",book["Book Link"])
//            startActivity(context,intent, Bundle.EMPTY)
            val intent=Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(bookUrl),"application/pdf")
            startActivity(context,Intent.createChooser(intent,"Open pdf"), Bundle.EMPTY)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class DashboardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtBookName:TextView=view.findViewById(R.id.txtBookName)
        val txtBookAuthor:TextView=view.findViewById(R.id.txtBookAuthor)
        val imgBook:ImageView=view.findViewById(R.id.imgBook)
    }

}