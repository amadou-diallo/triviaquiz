
package Business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author amadou
 */
public class Quiz {
    
    private String qf, errmsg;
    private int qnum, qcount;
    private boolean over;
    private ArrayList<String> questions;
    private ArrayList<String> answers;


public Quiz(String path) {
    this.qf = path;
    this.qnum=0;
    this.qcount = 0;
    this.over = false;
    this.errmsg = "";
    this.questions = new ArrayList<>();
    this.answers = new ArrayList<>();
    
    try {
      BufferedReader in = new BufferedReader(new FileReader(this.qf));
      String s = in.readLine();
      while(s != null) {
          questions.add(s);
          answers.add(in.readLine());
          this.qcount++;
          s = in.readLine();
      }
       
      in.close();
    } catch(IOException e) {
       this.errmsg = "Error opening file: " + this.qf + " - " + e.getMessage();
       this.qcount = 0;
    }
    if (this.qcount < 0) {
        this.over = false;
        this.qnum = 1;
    } else {
        this.over = true;
        this.qnum = 0;
    }
    
}//end of constructor


   public String getErrorMsg() {
       return this.errmsg;
    
}
  public int getQCount() {
      return this.qcount;
  }
  
  public String getQuestion() {
      if (this.over || this.qnum > this.qcount) {
          return "That's All folks";
      }
     return this.questions.get(this.qnum - 1);
  }
  
  public int getQNumber() {
      return this.qnum;
  }
  
  public String getAnswer() {
      String a;
      if(this.over) {
         a = "out of answers";
      } else {
         
          a = this.answers.get(this.qnum - 1);
      }
      if (this.qnum == this.qcount) {
          this.over = true;
      } else {
         
        this.qnum++;
      } 
      return a;
  
  }
  public boolean isOver() {
      return this.over;
  }
  
}     
    

