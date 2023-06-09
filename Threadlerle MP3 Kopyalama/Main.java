import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    private static ArrayList<Integer> content=new ArrayList<Integer>();
    
public static void readFile(){
    try {
        FileInputStream Mars1=new FileInputStream("IzmirMarsi.mp3");

        int read;
        while((read=Mars1.read())!=-1)
        {
            content.add(read);
        }
    } catch (FileNotFoundException e) {
        //TODO: handle exception
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
}

    public static void copy(String fileName){
        
        try (FileOutputStream Mars2 = new FileOutputStream(fileName)) {
            for(int d: content)
            {
                Mars2.write(d);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        
        readFile();

        Thread thread1=new Thread(new Runnable() {

            @Override
            public void run() {
                copy("MarsKopya1.mp3");
                
            }
            
        });

        readFile();

        Thread thread2=new Thread(new Runnable() {

            @Override
            public void run() {
                copy("MarsKopya2.mp3");
                
            }
            
        });

        readFile();

        Thread thread3=new Thread(new Runnable() {

            @Override
            public void run() {
                copy("MarsKopya3.mp3");
                
            }
            
        });

        long startTime=System.currentTimeMillis();

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
   
        
        
        long endTime=System.currentTimeMillis();

        System.out.println("Gecen zaman: "+(endTime-startTime));

        //3dosya kopyaladik ve tam olarak 60577 milisaniye surdu. Bu demek oluyorki ciddi bir problem zaman problemimiz var işte bunu threadlerle çözeceğiz. Bu bize threadlerin önemini vurgulayacak.

        
    }
}

