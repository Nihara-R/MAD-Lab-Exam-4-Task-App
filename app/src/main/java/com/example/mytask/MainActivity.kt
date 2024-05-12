package com.example.mytask

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytask.databinding.ActivityMainBinding
import com.example.tasksqlite.TaskAdapter
import com.example.tasksqlite.TaskDatabaseHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var db:TaskDatabaseHelper
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db= TaskDatabaseHelper(this)
        taskAdapter= TaskAdapter(db.getAllTasks(),this)

        binding.taskRecycleView.layoutManager= LinearLayoutManager(this)
        binding.taskRecycleView.adapter= taskAdapter

        binding.addButton.setOnClickListener{
            val intent= Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        }

    override fun onResume() {
        super.onResume()
        taskAdapter.refreshData(db.getAllTasks())
    }
    }
