package objectSetToDb;
import java.util.*;
import java.sql.*;
import java.io.*;
public class ObjectSentToBd {

	public static void main(String[] args) {
		try {
			  Scanner sc=new Scanner(System.in);
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","wahidur");
			  PreparedStatement ps=con.prepareStatement("insert into BinaryTab51 values(?,?)");
			  PreparedStatement ps1=con.prepareStatement("select * from BinaryTab51 where id=?");
			  
			  System.out.println("Enter id");
			  ps.setString(1, sc.nextLine());		  
			  //System.out.println("Enter file");
			  File f=new File("E:\\adv_Txt\\SerializationDB.ser");
			 // Serilization obj1=new Serilization();
			  		 
			  if(f.exists())
			  {			 
				 FileInputStream fis=new FileInputStream(f);			 
				 ps.setBinaryStream(2, fis,f.length());		  
				  int k=ps.executeUpdate();
				  if(k>0)
				  {
					  System.out.println("object inserted successfully ");
				  }
				  else
				  {
					  System.out.println("invalid path");
				  } 
				  
			  }else
			  {
				  System.out.println("invalid path");
			  }
			  System.out.println("Retrieving data from Db as object");
			  System.out.println("Enter id ");
			  ps1.setString(1, sc.nextLine());
			  
			  ResultSet rs=ps1.executeQuery();
			 if(rs.next())
			 {
				 Blob b=rs.getBlob(2);
				  byte[] by=b.getBytes(1, (int)b.length());
				  File f1=new File("E:\\adv_Txt\\Object.txt");
				  //ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f1));
				  FileOutputStream fos=new FileOutputStream(f1);
				  fos.write(by);
				  
				  ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f1));
				  
				  Serilization s=(Serilization)ois.readObject();
				  System.out.println(s.x+" "+s.y);
			 }
			  
			
		sc.close();	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
