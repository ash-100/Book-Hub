package com.example.bookhub.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhub.R
import com.example.bookhub.activity.ForgotPasswordActivity
import com.example.bookhub.activity.LoginActivity
import com.example.bookhub.activity.SignUpActivity
import com.example.bookhub.adapter.DashboardRecyclerAdapter
import com.example.bookhub.model.Book
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class DashboardFragment : Fragment() {

    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: LinearLayoutManager



    lateinit var recyclerAdapter:DashboardRecyclerAdapter
    val bookInfoList= arrayListOf<Book>(
        Book("War and Peace","Leo Tolstoy","Rs. 249",R.drawable.war_and_peace),
        Book("The Lord of the Rings","J.R.R Tolkien","Rs. 749",R.drawable.lord_of_rings),
        Book("MiddleMarch","George Elliot","Rs. 599",R.drawable.middlemarch)
    )
    val bookList = arrayListOf<HashMap<String,String>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard=view.findViewById(R.id.recyclerDashboard)
        layoutManager=LinearLayoutManager(activity)
        recyclerDashboard.layoutManager=layoutManager

        val db= FirebaseFirestore.getInstance()
        db.collection("books").addSnapshotListener{value, error ->
            if(error==null){
                if(value!=null){
                    for(change in value.documentChanges) {
                        if (change.type == DocumentChange.Type.ADDED) {

                            bookList.add(change.document.data as HashMap<String, String>)
                            recyclerAdapter =
                                DashboardRecyclerAdapter(activity as Context, bookList)
                            recyclerDashboard.adapter = recyclerAdapter
                        }
                    }


                }
            }
        }





        recyclerDashboard.addItemDecoration(DividerItemDecoration(recyclerDashboard.context,layoutManager .orientation))

//        val hi:Button=view.findViewById(R.id.hi)
//        hi.setOnClickListener {
//            startActivity(Intent(context,LoginActivity::class.java))
//        }
        return view
    }


}