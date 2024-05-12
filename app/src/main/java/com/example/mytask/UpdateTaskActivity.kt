package com.example.mytask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytask.databinding.ActivityUpdateTaskBinding // Import the correct binding


import com.example.tasksqlite.TaskDatabaseHelper

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TaskDatabaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)
        taskId = intent.getIntExtra("task_id", -1)
        if (taskId == -1) {
            finish()
            return
        }

        val task= db.getTaskByID(taskId)
        binding.updateTitleEditTest.setText(task.title)
        binding.updateContentEditTest.setText(task.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle= binding.updateTitleEditTest.text.toString()
            val newContent = binding.updateContentEditTest.text.toString()
            val  updateTask= Task(taskId, newTitle, newContent)

            db.updateTask(updateTask)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
