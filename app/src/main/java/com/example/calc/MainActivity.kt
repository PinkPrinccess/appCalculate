package com.example.calc

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.calc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import android.view.View
import android.content.Intent


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: LinearLayout = binding.root
        setContentView(view)


        binding.btn0.setOnClickListener{setTextFields("0")}
        binding.btn1.setOnClickListener{setTextFields("1")}
        binding.btn2.setOnClickListener{setTextFields("2")}
        binding.btn3.setOnClickListener{setTextFields("3")}
        binding.btn4.setOnClickListener{setTextFields("4")}
        binding.btn5.setOnClickListener{setTextFields("5")}
        binding.btn6.setOnClickListener{setTextFields("6")}
        binding.btn7.setOnClickListener{setTextFields("7")}
        binding.btn8.setOnClickListener{setTextFields("8")}
        binding.btn9.setOnClickListener{setTextFields("9")}
        binding.btnMinus.setOnClickListener{setTextFields("-")}
        binding.btnSumm.setOnClickListener{setTextFields("+")}
        binding.btnMult.setOnClickListener{setTextFields("*")}
        binding.btnDivide.setOnClickListener{setTextFields("/")}
        binding.btnBrcOpen.setOnClickListener{setTextFields("(")}
        binding.btnBrcClose.setOnClickListener{setTextFields(")")}
        binding.buttonBack.setOnClickListener{
           val swap = Intent(this, MainActivity2::class.java)
            startActivity(swap)
        }


        binding.btnAC.setOnClickListener {
            binding.totalOperation.text = ""
            binding.resultOperation.text = ""
        }

        binding.btnBack.setOnClickListener{
            val str = binding.totalOperation.text.toString()
            if(str.isNotEmpty())
                binding.totalOperation.text=str.substring(0,str.length-1)

            binding.resultOperation.text = ""
        }

        binding.btnResult.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.totalOperation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result==longRes.toDouble())
                    binding.resultOperation.text = longRes.toString()
                else
                    binding.resultOperation.text = result.toString()


            }catch (e:Exception){
                Log.d("error","message: ${e.message}")
            }
        }
    }

    fun setTextFields(str: String){
        if(binding.resultOperation.text !=""){
            binding.totalOperation.text = binding.resultOperation.text
            binding.resultOperation.text = ""
        }
        binding.totalOperation.append(str)
    }
}