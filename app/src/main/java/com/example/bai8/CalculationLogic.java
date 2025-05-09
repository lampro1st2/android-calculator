// app/src/main/java/com/example/bai8/CalculationLogic.java
package com.example.bai8;

public class CalculationLogic {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public float divide(int a, int b) {
        if (b == 0) {
            // Bạn có thể chọn ném ra một Exception cụ thể hơn,
            // hoặc trả về một giá trị đặc biệt (ví dụ: Float.NaN)
            // và kiểm tra điều này trong unit test.
            // Ở đây, chúng ta sẽ ném Exception để rõ ràng hơn về lỗi.
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (float) a / b;
    }

    public int findUCLN(int a, int b) {
        // Xử lý trường hợp đầu vào không hợp lệ (ví dụ: số âm, số 0)
        // Thuật toán UCLN chuẩn thường làm việc với số dương.
        if (a <= 0 || b <= 0) {
            // Có thể ném Exception hoặc trả về một giá trị chỉ lỗi, ví dụ -1 hoặc 0
            // tùy theo quy ước của bạn.
            // Ví dụ này sẽ đơn giản hóa bằng cách giả định đầu vào sẽ được xử lý
            // ở tầng UI hoặc chấp nhận kết quả không mong muốn cho UCLN của số không dương
            // tùy thuộc vào yêu cầu.
            // Để đơn giản cho ví dụ, ta sẽ tiếp tục với thuật toán gốc,
            // nhưng trong thực tế, bạn nên xử lý các trường hợp này cẩn thận.
            // Nếu bạn muốn thuật toán chặt chẽ hơn, hãy thêm kiểm tra và ném lỗi:
            // if (a <= 0 || b <= 0) {
            //     throw new IllegalArgumentException("Inputs for UCLN must be positive integers.");
            // }
        }

        // Đảm bảo a và b luôn dương cho thuật toán Euclid cơ bản
        // (Mặc dù thuật toán trừ liên tiếp cũng hoạt động với số âm nếu lấy trị tuyệt đối)
        // Tuy nhiên, UCLN thường được định nghĩa cho số nguyên dương.
        // Để đơn giản, chúng ta sẽ không xử lý sâu các trường hợp âm/zero ở đây,
        // mà tập trung vào logic chính.
        // Thuật toán của bạn là trừ liên tiếp, nó sẽ hoạt động.
        // Nhưng để an toàn hơn và tránh vòng lặp vô hạn nếu có số 0,
        // cần kiểm tra trước.
        if (a == 0 && b == 0) return 0; // Hoặc ném lỗi
        if (a == 0) return Math.abs(b);
        if (b == 0) return Math.abs(a);

        // Đảm bảo các số là dương để thuật toán trừ hoạt động như mong đợi
        // (Mặc dù thuật toán của bạn có vẻ xử lý được nếu một số lớn hơn số kia)
        // Tuy nhiên, UCLN(a,b) = UCLN(|a|, |b|)
        int valA = Math.abs(a);
        int valB = Math.abs(b);

        while (valA != valB) {
            if (valB > valA) {
                valB = valB - valA;
            } else {
                valA = valA - valB;
            }
        }
        return valB; // Hoặc valA, vì lúc này chúng bằng nhau
    }
}