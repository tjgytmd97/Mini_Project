package Login;

public class UserVO {
	private int usernum;
	private String userid;
	private String userpassword;
	private String username;
	private String userphone;
	private String useremail;
	private int usermaxscore;
	
	public UserVO(int usernum, String userid, String userpassword, String username, String userphone, String useremail,
			int usermaxscore) {
		super();
		this.usernum =usernum;
		this.userid = userid;
		this.userpassword = userpassword;
		this.username = username;
		this.userphone = userphone;
		this.useremail = useremail;
		this.usermaxscore = usermaxscore;
	}
	
	public UserVO(String userid, String username, int usermaxscore) {
		this.userid = userid;
		this.username = username;
		this.usermaxscore = usermaxscore;
	}

	public int getUserNum() {
		return usernum;
	}

	public void setUserNum(int userNum) {
		this.usernum = userNum;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public int getUsermaxscore() {
		return usermaxscore;
	}

	public void setUsermaxscore(int usermaxscore) {
		this.usermaxscore = usermaxscore;
	}
	
	
	
	
	
}
