package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Login.UserVO;

public class UserDAO {
	private int usernum =0;
	private String userid = null;
	private String userpassword = null;
	private String username = null;
	private String userphone = null;
	private String useremail = null;
	private int usermaxscore =0;
	
	private String url="jdbc:oracle:thin:@localhost:1521:xe"; 
	private String user ="javaminiproject";
	private String pass = "javaminiproject";
	
	private Connection con = null;
	private PreparedStatement pst = null;
	
	private boolean result =false;
	private ResultSet rs = null;
	private UserVO vo= null;
	
	public void connect() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
			}catch(Exception e) {
				e.printStackTrace();
		}
	}
	
	public void close(ResultSet rs) {
		if(rs != null) {
		try {
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}
		}
	}
	
	public void close(PreparedStatement pst) {
		if(pst != null) {
		try {
			pst.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			}
		}
	}
	
	public void close(Connection con) {
		if(con != null) {
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			}
		}
	}
	
	public boolean isSignup1(String id) // 사용자에게 아이디만 입력받는다. 시리즈가 2개있다.
	{
		boolean issignright = false;
		try {
			connect();
			String sql = "select * from USER_INFORMATION where USER_ID = ?";
			
		    pst = con.prepareStatement(sql);
		    pst.setString(1, id);
			
			//sql 실행처리
			//excuteUpdate => insert, update, delete
			ResultSet rs = pst.executeQuery();
			if(!(rs.next())) { // 동일한 id가 없으면
				
				issignright = true;
			}
			
			
			
			
		 	} catch (Exception e) {
		 			e.printStackTrace();
		 	}finally {
		 		
		 		try {
					pst.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 		
		 	}
	
	
	
		return issignright; // 값이 true값이면 데이터베이스에 동일한 id 가 없는 상태이다.
	
	}
	
	public boolean isSignup2(String id, String password, String name, String phone, String email ) { 
		// isSignup1에서 기존의 id가 있는지 확인절차를 밟고나서 
	try {
		connect();
		String sql = "insert into USER_INFORMATION values(user_seq.nextval, ?, ?, ?, ?, ?,?)";
		
	    pst = con.prepareStatement(sql);
		pst.setString(1, id);
		pst.setString(2, password);
		pst.setString(3, name);
		pst.setString(4, phone);
		pst.setString(5, email);
		pst.setInt(6, usermaxscore);
		
		int cnt = pst.executeUpdate();
		if(cnt > 0) {
			result = true;
		}
		else {
			result = false;
			
		}
		
		
		
	 	} catch (Exception e) {
	 			e.printStackTrace();
	 	}finally {
	 		
	 		close(pst);
	 		close(con);
	 	}
	
return result;
}
	
	
	public int countNum(int usermaxscore) {
	 try{
		connect();
		String sql = "select * from USER_INFORMATION where USER_MAXSCORE = ?";
		
	}finally {
		
		try {
		pst.close();
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
return usermaxscore;
}
	
	/*
	 * 2022.7.13 유선옥 작성
	 * 게임 점수 업데이트 하려고 만들었음.
	 * 
	 * */
	
	public boolean updateMax(String id, int updatescore) {
		try {  //업데이트(게임 점수 및 랭킹 갱신)
			
			connect();
			String sql = "update USER_INFORMATION set USER_MAXSCORE = (USER_MAXSCORE +? ) where USER_ID = ?";
			
			pst = con.prepareStatement(sql);
			pst.setInt(1, updatescore);
			pst.setString(2, id);
			
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
		
	}	
	
	public void printselectrank() { // USER_ID, USER_NAME, USER_MAXSCORE
		ArrayList<UserVO> al = new ArrayList<UserVO>();
		try {
			
			connect();
			
			String sql = "select USER_ID, USER_NAME, USER_MAXSCORE from USER_INFORMATION order by USER_MAXSCORE desc";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery(); 
			//while(rs.next()) {
				for(int i =1 ; rs.next() == true; i ++)
				{
				userid = rs.getString("USER_ID");
				
				username = rs.getString("USER_NAME");
				
				usermaxscore = rs.getInt("USER_MAXSCORE");
				System.out.printf("%5s %15s %15s %15s\n","등수","유저아이디", "유저이름", "유저 총 맞춘 수");
				System.out.printf("%5d %15s %15s %15d\n", i, userid, username, usermaxscore);
//				vo = new UserVO(userid,username,usermaxscore);
//				al.add(vo);
			}
		} catch (Exception e) {
 			e.printStackTrace();
 	}finally {
 		
 		try {
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	}
	
	


	
		
		
		
		
		
	
	public boolean isLogin(String id, String password) {
		boolean isloginright = false;
		try {
			connect();
			String sql = "select * from USER_INFORMATION where USER_ID = ? and USER_PASSWORD = ?";
			
		    pst = con.prepareStatement(sql);
		    pst.setString(1, id);
		    pst.setString(2, password);
			
			//sql 실행처리
			//excuteUpdate => insert, update, delete
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {

				isloginright =true;
			}

			
			
		 	} catch (Exception e) {
		 			e.printStackTrace();
		 	}finally {
		 		
		 		try {
					pst.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 		
		 	}
		return isloginright;
	}

}
