package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Employees_Chapter04 {
    public static void main(String[] args) {
        // MySQL接続用のURL、ユーザー名、パスワード
        String url = "jdbc:mysql://localhost:3306/challenge_java";
        String user = "root";
        String password = "8810AAaa!"; // 実際のパスワードを入力してください

        Connection connection = null;
        Statement statement = null;

        try {
            // データベース接続
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("データベース接続成功：" + connection);

            // テーブル作成用SQL
            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees ("
                    + "id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(60) NOT NULL, "
                    + "email VARCHAR(255) NOT NULL, "
                    + "age INT(11), "
                    + "address VARCHAR(255))";

            // SQL実行
            statement = connection.createStatement();
            int result = statement.executeUpdate(createTableSQL);
            System.out.println("社員テーブルを作成しました: 更新レコード数=" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // リソース解放
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


