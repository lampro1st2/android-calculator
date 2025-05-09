// app/src/main/java/com/example/bai8/MainActivity.java
package com.example.bai8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Thêm Toast để thông báo lỗi

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText num1EditText, num2EditText;
    private TextView kqTextView;
    private CalculationLogic calculator; // Khai báo đối tượng CalculationLogic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        kqTextView = findViewById(R.id.kq);
        Button tongButton = findViewById(R.id.btnTong);
        Button hieuButton = findViewById(R.id.btnHieu);
        Button tichButton = findViewById(R.id.btnTich);
        Button thuongButton = findViewById(R.id.btnThuong);
        Button uclnButton = findViewById(R.id.btnUCLN);
        Button exitButton = findViewById(R.id.btnExit);

        num1EditText = findViewById(R.id.num1);
        num2EditText = findViewById(R.id.num2);

        calculator = new CalculationLogic(); // Khởi tạo đối tượng

        tongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation(Operation.ADD);
            }
        });

        hieuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation(Operation.SUBTRACT);
            }
        });

        tichButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation(Operation.MULTIPLY);
            }
        });

        thuongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation(Operation.DIVIDE);
            }
        });

        uclnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation(Operation.UCLN);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Enum để định nghĩa các phép toán
    private enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, UCLN
    }

    private void performCalculation(Operation operation) {
        String strNum1 = num1EditText.getText().toString();
        String strNum2 = num2EditText.getText().toString();

        if (strNum1.isEmpty() || strNum2.isEmpty()) {
            kqTextView.setText("Vui lòng nhập đủ hai số");
            Toast.makeText(this, "Vui lòng nhập đủ hai số", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int soa = Integer.parseInt(strNum1);
            int sob = Integer.parseInt(strNum2);
            String resultText = "Kết quả là ";

            switch (operation) {
                case ADD:
                    resultText += calculator.add(soa, sob);
                    break;
                case SUBTRACT:
                    resultText += calculator.subtract(soa, sob);
                    break;
                case MULTIPLY:
                    resultText += calculator.multiply(soa, sob);
                    break;
                case DIVIDE:
                    // Xử lý IllegalArgumentException từ hàm divide nếu có
                    try {
                        resultText += calculator.divide(soa, sob);
                    } catch (IllegalArgumentException e) {
                        resultText = e.getMessage(); // Hiển thị thông báo lỗi từ logic
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case UCLN:
                    // Tương tự, có thể thêm try-catch nếu UCLN ném lỗi cho đầu vào không hợp lệ
                    try {
                        // UCLN cần số dương, bạn có thể muốn thêm kiểm tra ở đây
                        // hoặc trong CalculationLogic và ném lỗi.
                        // Ví dụ, nếu UCLN không cho phép số 0 hoặc số âm:
                        if (soa <= 0 || sob <= 0) {
                            resultText = "UCLN yêu cầu số dương";
                            Toast.makeText(this, "UCLN yêu cầu số dương", Toast.LENGTH_SHORT).show();
                        } else {
                            resultText += calculator.findUCLN(soa, sob);
                        }
                    } catch (IllegalArgumentException e) {
                        resultText = e.getMessage();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            kqTextView.setText(resultText);

        } catch (NumberFormatException e) {
            kqTextView.setText("Đầu vào không hợp lệ (không phải số)");
            Toast.makeText(this, "Đầu vào không phải là số nguyên hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}