package ques.VO;

public class QuesVO {
	 
		private int word_num;
		private String word;
		private String problem;
		private String answer;
		private String diff;
		
		
		
		public QuesVO(int word_num, String word, String problem, String answer) {
			this.word_num = word_num;
			this.word = word;
			this.problem = problem;
			this.answer = answer;
			
		}
		
		
		
		public QuesVO() {
			
		}



		public int getWord_num() {
			return word_num;
		}



		public void setWord_num(int word_num) {
			this.word_num = word_num;
		}



		public String getWord() {
			return word;
		}



		public void setWord(String word) {
			this.word = word;
		}



		public String getProblem() {
			return problem;
		}



		public void setProblem(String problem) {
			this.problem = problem;
		}



		public String getAnswer() {
			return answer;
		}



		public void setAnswer(String answer) {
			this.answer = answer;
		}



		public String getDiff() {
			return diff;
		}



		public void setDiff(String diff) {
			this.diff = diff;
		}
		
		
}
 
