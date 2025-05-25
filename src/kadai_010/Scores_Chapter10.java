package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Scores_Chapter10 {
    public static void main(String[] args) {
        // データベース接続情報
        String url = "jdbc:mysql://localhost:3306/challenge_java";
        String user = "root";
        String password = "8810AAaa!"; // 実際のMySQLパスワードを入力してください

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("データベース接続成功：" + connection);

            // レコード更新メッセージを表示
            System.out.println("レコード更新を実行します");

            // 武者小路勇気さんの点数データを更新
            String updateSQL = "UPDATE scores SET score_math = ?, score_english = ? WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {
                updateStatement.setInt(1, 95); // 数学の点数
                updateStatement.setInt(2, 80); // 英語の点数
                updateStatement.setInt(3, 5);  // 生徒ID (武者小路勇気さん)
                int rowsUpdated = updateStatement.executeUpdate();
                System.out.println(rowsUpdated + "件のレコードが更新されました");
            }

            // 点数順に並べ替えたデータを取得
            String selectSQL = "SELECT id, name, score_math, score_english FROM scores "
                    + "ORDER BY score_math DESC, score_english DESC";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL)) {
                ResultSet resultSet = selectStatement.executeQuery();
                System.out.println("数学・英語の点数が高い順に並べ替えました");

                int recordNumber = 1;
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int mathScore = resultSet.getInt("score_math");
                    int englishScore = resultSet.getInt("score_english");

                    System.out.printf("%d件目：生徒ID=%d／氏名=%s／数学=%d／英語=%d%n",
                                      recordNumber, id, name, mathScore, englishScore);
                    recordNumber++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
