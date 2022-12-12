package ques.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ques.VO.QuesVO;
public class QuesDAO {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "javaminiproject";
		String pass = "javaminiproject";
		
		private Connection con = null;
		private PreparedStatement pst = null;
		private ResultSet rs = null;
		
		private int word_num = 0;
		private String word = null;
		private String problem = null;
		private String answer = null;
		
		private int stnum = 0;
		private QuesVO vo = null;
		private boolean result = false;
		
		public void connect() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, user, pass);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		public void close(ResultSet rs) {
			if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		}
		
		public void close(PreparedStatement pst) {
		if(pst != null){
		{
			try {
				pst.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
		}
		
		public void close(Connection con)
		{
			if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			}
		}

		public QuesVO selectStd(int r) {  //문제출력
			try {
				//1. jdbc 드라이버 동적 로딩
				connect();
				//sql작성
				String sql = "select * from ENGLISH_WORD where word_num = ?";
				//바인드변수 채우기
				pst = con.prepareStatement(sql);
				pst.setInt(1, r);//main에서 받은 랜덤변수를 넣어서 위 ?에 삽입 및 그 해당된항목 불러옴
				boolean isList = false;
				rs = pst.executeQuery();
				while(rs.next()) {
					word_num = rs.getInt("word_num");
					word = rs.getString("word");
					problem = rs.getString("problem");
					answer = rs.getString("answer");
					 vo = new QuesVO(word_num, word, problem, answer);
					 isList = true;
				}
				if(isList == false)
				{
					vo = new QuesVO();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pst);
				close(con);
			}
			return vo;
		}

/*
		public boolean updateStd(int stnum, String name, int age, String phone, String email) {
			try {  //업데이트(게임 점수 및 랭킹 갱신)
				connect();
				String sql = null;
				sql = " update student ";
				sql += " set ";
				sql += " name = ?, ";
				sql += " age = ?, ";
				sql += " phone = ?, ";
				sql += " email = ?";
				sql += " where stnum = ?";
				pst = con.prepareStatement(sql);
				
				pst.setString(1, name);
				pst.setInt(2, age);
				pst.setString(3, phone);
				pst.setString(4, email);
				pst.setInt(5, stnum);
				
				
				int cnt = pst.executeUpdate();
				if(cnt > 0)
				{
					result = true;
				}
				else
				{
					result = false;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
					close(pst);
					close(con);
				
			}
			return result;
		}*/	
		
	}


