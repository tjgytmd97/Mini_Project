package Login;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import ques.DAO.QuesDAO;
import ques.VO.QuesVO;



public class Main {
/*
 * isSignup2(String id, String password, String name, String phone, String email )
 * 
 * 
 * */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random r = new Random();
		boolean result ;
		boolean result2;
		
		String id = null;
		String password =null;
		String name = null;
		String phone = null;
		String email = null;
		
		int usermaxscore =0;
		
		int menu =0;
		
		boolean menu1Control = true;
		boolean menu2Control = false;
		
		QuesVO qes = null;
		QuesDAO qest = null;
		UserDAO de = null;
		
		de = new UserDAO();
	while(menu1Control) {
		try {
			do {
			System.out.println("[1]회원가입 [2]로그인 [3]종료");
			menu = scan.nextInt();
			}while(!(menu >=1 && menu <=3));
		}catch (Exception e) {
 			System.out.println("1,2,3 중 하나를 입력하세요");
 			String buffer = scan.next();
			
		}
		
		
		
		
		switch(menu) {
			case 1 : 
				System.out.println("회원가입");
				int casestop =-1;
				do {
						
					System.out.print("아이디를 입력하세요 : ");
					id = scan.next();
					result = de.isSignup1(id);
					
				if(result) {
					System.out.print("비밀번호를 입력하세요 : ");
					password = scan.next();
					
					System.out.print("이름을 입력하세요 : ");
					name = scan.next();
					
					System.out.print("전화번호를 입력하세요 : ");
					phone = scan.next();
					
					System.out.print("이메일을 입력하세요 : ");
					email = scan.next();
					
					de.isSignup2(id, password, name, phone, email);
					if(result) {
						System.out.println("회원가입 성공");
						casestop = 1;
					}
					else {
						System.out.println("회원 가입 실패");
					}
				}
				
				else {
					System.out.println("존재하는 아이디입니다.");
				}
				
				}while(!(casestop == 1));
				
				break;
			case 2 :
				System.out.println("로그인");
				System.out.print("아이디를 입력하세요 : ");
				id = scan.next();
				System.out.print("비밀번호를 입력하세요 : ");
				password = scan.next();
				
				result = de.isLogin(id, password);
				
				if(result) {
					System.out.println("로그인에 성공했습니다.");
					
					menu1Control = false;
					menu2Control = true;
				}
				else {
					System.out.println("로그인에 실패했습니다.");
				}
				
				break;
			
			case 3 : 
					System.out.println("프로그램을 종료합니다.");
					menu1Control = false;
				break;
				
		}
	}
	
	
	while(menu2Control) {
		
		try {
			do {
			System.out.println("[1]게임시작 [2]랭킹확인 [3]종료");
			menu = scan.nextInt();
			}while(!(menu >=1 && menu <=3));
		}catch (Exception e) {
 			System.out.println("1,2,3 중 하나를 입력하세요");
 			String buffer = scan.next();
		}
		
		switch(menu) {
		
		case 1 :
			int life = 5; 
			int jum = 0;
			do {
			int nann = r.nextInt(1596)+1;
			qest = new QuesDAO();
			qes = qest.selectStd(nann);
			
			String a = qes.getWord();
			String b = qes.getProblem();
			String c = qes.getAnswer();
			boolean ans = false;
			
			System.out.println("게임 시작");
			System.out.println(qes.getWord() +" "+ qes.getProblem());
			System.out.println("정답이 맞다 싶으면 1, 틀리다 생각하면 2를 눌러주세요");
			int dab = scan.nextInt();
			if(dab == 1)
			{
				
				if(b.equals(c)) {//
					jum += 1;
					System.out.println("정답입니다."+jum);
			
				}
				else{
					System.out.println("틀렸습니다.");
					System.out.println("정답은" + c + "였습니다.");
					life -=1;
					System.out.println("목숨 : "+life);
				}
			
			}else {
				if(!(b.equals(c)))
				{
					jum += 1;
					System.out.println("정답입니다."+jum);
				}
				else {
					System.out.println("틀렸습니다.");
					System.out.println("정답은" + c + "였습니다.");
					life -=1;
					System.out.println("목숨 : "+life);
				}
			}
			}while(life != 0);
			System.out.println("GAME OVER! \n 이번 게임에서 맞춘 문제는 총"+ jum + "문제 입니다.");
			result=de.updateMax(id, jum);
			if(result) {
				System.out.println("누적 맞춘 횟수가 새로 등록 되었습니다.");
			}
			
			break;
		case 2 :  // 
			System.out.println("랭킹 보기 !");
			
			de.printselectrank();
			
			break;
			
		case 3 : 
			System.out.println("프로그램을 종료합니다. "); 
			menu2Control = false;
			break;
		
		}
		
		
	}
	
		
	}

}

