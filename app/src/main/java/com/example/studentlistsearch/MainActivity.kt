package com.example.studentlistsearch

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlistsearch.adapters.StudentAdapter
import com.example.studentlistsearch.models.Student

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private val students = listOf(
        Student("Nguyen Van Hoang", "20121012"),
        Student("Tran Thi Binh", "20131013"),
        Student("Le Van Chinh", "20141014"),
        Student("Pham Thi Dung", "20151015"),
        Student("Hoang Van Em", "20161016"),
        Student("Do Thi Phuong Giang", "20171017"),
        Student("Bui Van Giao", "20181018"),
        Student("Ngo Thi Hoa", "20191019"),
        Student("Vu Van Hung", "20201020"),
        Student("Dang Thi Khanh", "20211021"),
        Student("Dinh Van Kim", "20221022"),
        Student("Ly Thi Ling", "20231023"),
        Student("Cao Van Manh", "20241024"),
        Student("Chu Thi Ngoc", "20251025"),
        Student("Duong Van Ong", "20261026"),
        Student("Kieu Thi Phuong", "20271027"),
        Student("Luu Van Quan", "20281028"),
        Student("Mac Thi Soai", "20291029")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(students)
        recyclerView.adapter = studentAdapter

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = if (newText.isNullOrEmpty() || newText.length <= 2) {
                    students
                } else {
                    students.filter {
                        it.name.contains(newText, ignoreCase = true) || it.id.contains(newText)
                    }
                }
                studentAdapter.updateList(filteredList)
                return true
            }
        })
    }
}