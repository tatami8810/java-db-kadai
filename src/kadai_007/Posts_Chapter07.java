package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Posts_Chapter07 {
    public static void main(String[] args) {
        // データベース接続情報
        String url = "jdbc:mysql://localhost:3306/challenge_java";
        String user = "root";
        String password = "8810AAaa!"; // 実際のパスワードを入力してください

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("データベース接続成功：" + connection);

            // 投稿データを追加
            String insertSQL = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES "
                    + "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13), "
                    + "(1002, '2023-02-08', 'お疲れ様です！', 12), "
                    + "(1003, '2023-02-09', '今日も頑張ります！', 18), "
                    + "(1001, '2023-02-09', '無理は禁物ですよ！', 17), "
                    + "(1002, '2023-02-10', '明日から連休ですね！', 20)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
                int rowsAdded = insertStatement.executeUpdate();
                System.out.println(rowsAdded + "件のレコードが追加されました");
            }

            // ユーザーIDが1002の投稿を検索
            String selectSQL = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL)) {
                selectStatement.setInt(1, 1002); // ユーザーID1002を指定
                ResultSet resultSet = selectStatement.executeQuery();

                System.out.println("ユーザーIDが1002のレコードを検索しました");
                int recordNumber = 1;
                while (resultSet.next()) {
                    String postedAt = resultSet.getDate("posted_at").toString();
                    String postContent = resultSet.getString("post_content");
                    int likes = resultSet.getInt("likes");

                    System.out.printf("%d件目：投稿日時=%s／投稿内容=%s／いいね数=%d%n", recordNumber, postedAt, postContent, likes);
                    recordNumber++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

